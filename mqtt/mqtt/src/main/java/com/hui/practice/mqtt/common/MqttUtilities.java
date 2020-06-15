package com.hui.practice.mqtt.common;

import com.hui.practice.mqtt.config.MqttConfig;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class MqttUtilities {
    private MqttConfig mqttConfig;
    private AliyunUtilities aliyunUtilities;
    private Map<String, IMqttClient> sendersKeyByTopic = new ConcurrentHashMap<>();

    public MqttUtilities(
            MqttConfig mqttConfig,
            AliyunUtilities aliyunUtilities
    ) {
        this.mqttConfig = mqttConfig;
        this.aliyunUtilities = aliyunUtilities;
    }

    public IMqttClient getSender(String topic) throws MqttException, InvalidKeyException, NoSuchAlgorithmException {
        if (StringUtils.isBlank(topic)) {
            throw new IllegalArgumentException("empty topic");
        }
        IMqttClient sender = this.sendersKeyByTopic.get(topic);
        if (sender != null) {
            return sender;
        }
        sender = this.createSenderForProvider();
        this.sendersKeyByTopic.put(topic, sender);
        return sender;
    }

    private IMqttClient createSenderForProvider() throws NoSuchAlgorithmException, InvalidKeyException, MqttException {
        switch (this.mqttConfig.getProvider()) {
            case "aliyun":
                return this.aliyunUtilities.createMqttSender();
            default:
                return this.createSender();
        }
    }

    private IMqttClient createSender() throws MqttException {
        String clientId = "GID-001@@@GJ1038";
        IMqttClient client = new MqttClient(this.mqttConfig.getPublisher().getUri(), clientId);

        System.out.println("uri: " + this.mqttConfig.getPublisher().getUri());
        System.out.println("clientId: " + clientId);
        System.out.println("cleanSession: " + this.mqttConfig.getPublisher().getCleanSession());

        MqttConnectOptions options = new MqttConnectOptions();
        if (this.mqttConfig.getPublisher().getUsername() != null) {
            options.setUserName(this.mqttConfig.getPublisher().getUsername());
            options.setPassword(this.mqttConfig.getPublisher().getPassword().toCharArray());
        }
        if (this.mqttConfig.getPublisher().getMaxInflight() != null) {
            options.setMaxInflight(this.mqttConfig.getPublisher().getMaxInflight().intValue());
        }
        if (this.mqttConfig.getPublisher().getCleanSession() != null) {
            options.setCleanSession(this.mqttConfig.getPublisher().getCleanSession().booleanValue());
        }
        options.setServerURIs(new String[]{this.mqttConfig.getPublisher().getUri()});
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost: " + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("messageArrived: (" + topic + ") " + message.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                MqttMessage msg;
                String topics = Arrays.stream(token.getTopics()).collect(Collectors.joining("|"));
                try {
                    msg = token.getMessage();
                    System.out.println("deliveryComplete: qos" + msg.getQos() + ", topics=" + topics + ", bytes=" + msg.getPayload().length + ", retained=" + msg.isRetained());
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });

        client.connect(options);
        return client;
    }
}
