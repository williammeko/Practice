package com.hui.practice.mqtt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hui.practice.mqtt.common.MqttUtilities;
import com.hui.practice.mqtt.config.MqttConfig;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    private MqttConfig mqttConfig;
    private ObjectMapper objectMapper;
    private MqttUtilities mqttUtilities;

    public SendController(
            MqttConfig mqttConfig,
            ObjectMapper objectMapper,
            MqttUtilities mqttUtilities
    ) {
        this.mqttConfig = mqttConfig;
        this.objectMapper = objectMapper;
        this.mqttUtilities = mqttUtilities;
    }

    @PostMapping("send/topic/{topic}")
    public Object sendToTopic(@PathVariable("topic") String topic, @RequestBody Object msg) {
        try {
            IMqttClient sender = this.mqttUtilities.getSender(topic);
            byte[] payload = this.objectMapper.writeValueAsString(msg).getBytes("UTF-8");
            MqttMessage mqttMsg = new MqttMessage(payload);
            mqttMsg.setQos(this.mqttConfig.getPublishing().getQos());
            if (this.mqttConfig.getPublishing().getRetained() != null) {
                mqttMsg.setRetained(this.mqttConfig.getPublishing().getRetained().booleanValue());
            }
            sender.publish(topic, mqttMsg);
            return this.buildResponse(true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildResponse(false, e.getMessage());
        }
    }

    private Object buildResponse(boolean success, String message) {
        return new Object() {
            public boolean sent = success;
            public String msg = message;
        };
    }
}
