package com.hui.practice.mqtt.common;

import com.hui.practice.mqtt.config.MqttConfig;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        MqttConnectOptions options = new MqttConnectOptions();
        if (this.mqttConfig.getPublisher().getUsername() != null) {
            options.setUserName(this.mqttConfig.getPublisher().getUsername());
            options.setPassword(this.mqttConfig.getPublisher().getPassword().toCharArray());
        }
        if (this.mqttConfig.getPublisher().getMaxInflight() != null) {
            options.setMaxInflight(this.mqttConfig.getPublisher().getMaxInflight().intValue());
        }
        options.setServerURIs(new String[]{this.mqttConfig.getPublisher().getUri()});
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        client.connect(options);
        return client;
    }
}
