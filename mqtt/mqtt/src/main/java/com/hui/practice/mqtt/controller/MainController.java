package com.hui.practice.mqtt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MainController {
    private String TOPIC = "practicTopic01";

    private Random rnd = new Random();
    private IMqttClient publisher;
    private ObjectMapper objectMapper;

    @Autowired
    public MainController(
            IMqttClient publisher,
            ObjectMapper objectMapper
    ) {
        this.publisher = publisher;
        this.objectMapper = objectMapper;
    }

    @PostMapping("publish")
    public Object publish(@RequestBody Object msg) throws MqttException, JsonProcessingException {
        byte[] payload = this.objectMapper.writeValueAsString(msg).getBytes();
        MqttMessage mqttMsg = new MqttMessage(payload);
        mqttMsg.setQos(2);
        mqttMsg.setRetained(true);
        this.publisher.publish(TOPIC, mqttMsg);
        return new Object() {
            public boolean sent = true;
        };
    }
}
