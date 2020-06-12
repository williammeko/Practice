/*
package com.hui.practice.mqtt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hui.practice.mqtt.config.MqttConfig;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class MainController {
    private final static String TOPIC = "practiceTopic01";

    private MqttConfig mqttConfig;
    private IMqttClient publisher;
    private IMqttClient publisher1;
    private IMqttClient publisher2;
    private IMqttClient publisher3;
    private IMqttClient publisher4;
    private IMqttClient publisher5;
    private ObjectMapper objectMapper;

    @Autowired
    public MainController(
            MqttConfig mqttConfig,
            IMqttClient publisher,
            IMqttClient publisher1,
            IMqttClient publisher2,
            IMqttClient publisher3,
            IMqttClient publisher4,
            IMqttClient publisher5,
            ObjectMapper objectMapper
    ) {
        this.mqttConfig = mqttConfig;
        this.publisher = publisher;
        this.publisher1 = publisher1;
        this.publisher2 = publisher2;
        this.publisher3 = publisher3;
        this.publisher4 = publisher4;
        this.publisher5 = publisher5;
        this.objectMapper = objectMapper;
    }

    @PostMapping("publish")
    public Object publish(@RequestBody Object msg) throws MqttException, JsonProcessingException, UnsupportedEncodingException {
        byte[] payload = this.objectMapper.writeValueAsString(msg).getBytes("UTF-8");
        MqttMessage mqttMsg = new MqttMessage(payload);
        mqttMsg.setQos(this.mqttConfig.getPublishing().getQos());
        mqttMsg.setRetained(this.mqttConfig.getPublishing().isRetained());
        try {
            this.publisher.publish(TOPIC, mqttMsg);
        } catch (MqttException e) {
            return e.getMessage();
        }
        return new Object() {
            public boolean sent = true;
        };
    }

    @PostMapping("publish1")
    public Object publish1(@RequestBody Object msg) throws MqttException, JsonProcessingException, UnsupportedEncodingException {
        byte[] payload = this.objectMapper.writeValueAsString(msg).getBytes("UTF-8");
        MqttMessage mqttMsg = new MqttMessage(payload);
        mqttMsg.setQos(this.mqttConfig.getPublishing().getQos());
        mqttMsg.setRetained(this.mqttConfig.getPublishing().isRetained());
        try {
            this.publisher1.publish(TOPIC, mqttMsg);
        } catch (MqttException e) {
            return e.getMessage();
        }
        return new Object() {
            public boolean sent = true;
        };
    }

    @PostMapping("publish2")
    public Object publish2(@RequestBody Object msg) throws MqttException, JsonProcessingException, UnsupportedEncodingException {
        byte[] payload = this.objectMapper.writeValueAsString(msg).getBytes("UTF-8");
        MqttMessage mqttMsg = new MqttMessage(payload);
        mqttMsg.setQos(this.mqttConfig.getPublishing().getQos());
        mqttMsg.setRetained(this.mqttConfig.getPublishing().isRetained());
        try {
            this.publisher2.publish(TOPIC, mqttMsg);
        } catch (MqttException e) {
            return e.getMessage();
        }
        return new Object() {
            public boolean sent = true;
        };
    }

    @PostMapping("publish3")
    public Object publish3(@RequestBody Object msg) throws MqttException, JsonProcessingException, UnsupportedEncodingException {
        byte[] payload = this.objectMapper.writeValueAsString(msg).getBytes("UTF-8");
        MqttMessage mqttMsg = new MqttMessage(payload);
        mqttMsg.setQos(this.mqttConfig.getPublishing().getQos());
        mqttMsg.setRetained(this.mqttConfig.getPublishing().isRetained());
        try {
            this.publisher3.publish(TOPIC, mqttMsg);
        } catch (MqttException e) {
            return e.getMessage();
        }
        return new Object() {
            public boolean sent = true;
        };
    }

    @PostMapping("publish4")
    public Object publish4(@RequestBody Object msg) throws MqttException, JsonProcessingException, UnsupportedEncodingException {
        byte[] payload = this.objectMapper.writeValueAsString(msg).getBytes("UTF-8");
        MqttMessage mqttMsg = new MqttMessage(payload);
        mqttMsg.setQos(this.mqttConfig.getPublishing().getQos());
        mqttMsg.setRetained(this.mqttConfig.getPublishing().isRetained());
        try {
            this.publisher4.publish(TOPIC, mqttMsg);
        } catch (MqttException e) {
            return e.getMessage();
        }
        return new Object() {
            public boolean sent = true;
        };
    }

    @PostMapping("publish5")
    public Object publish5(@RequestBody Object msg) throws MqttException, JsonProcessingException, UnsupportedEncodingException {
        byte[] payload = this.objectMapper.writeValueAsString(msg).getBytes("UTF-8");
        MqttMessage mqttMsg = new MqttMessage(payload);
        mqttMsg.setQos(this.mqttConfig.getPublishing().getQos());
        mqttMsg.setRetained(this.mqttConfig.getPublishing().isRetained());
        try {
            this.publisher5.publish(TOPIC, mqttMsg);
        } catch (MqttException e) {
            return e.getMessage();
        }
        return new Object() {
            public boolean sent = true;
        };
    }
}
*/
