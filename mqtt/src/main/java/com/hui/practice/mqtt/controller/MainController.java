package com.hui.practice.mqtt.controller;

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

    @Autowired
    public MainController(IMqttClient publisher) {
        this.publisher = publisher;
    }

    @PostMapping("publish")
    public Object publish(@RequestBody Object msg) throws MqttException {
        double temp = 80 + rnd.nextDouble() * 20.0;
        byte[] payload = String.format("T:%04.2f", temp)
                .getBytes();
        MqttMessage mqttMsg = new MqttMessage(payload);
        mqttMsg.setQos(0);
        mqttMsg.setRetained(true);
        this.publisher.publish(TOPIC, mqttMsg);
        return new Object() {
            public boolean sent;
        };
    }
}
