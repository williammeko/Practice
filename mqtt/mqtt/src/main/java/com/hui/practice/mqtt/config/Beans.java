package com.hui.practice.mqtt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class Beans {
    private MqttConfig mqttConfig;

    @Autowired
    public Beans(MqttConfig mqttConfig) {
        this.mqttConfig = mqttConfig;
    }

    @Bean
    public IMqttClient publisher() throws MqttException {
        String publisherId = "publisherId";
        IMqttClient publisher = new MqttClient(this.mqttConfig.getPublisher().getUri(), publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        if (this.mqttConfig.getPublisher().getUsername() != null) {
            options.setUserName(this.mqttConfig.getPublisher().getUsername());
            options.setPassword(this.mqttConfig.getPublisher().getPassword().toCharArray());
        }
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
        return publisher;
    }

    @Bean
    public IMqttClient publisher1() throws MqttException {
        String publisherId = "publisherId1";
        IMqttClient publisher = new MqttClient(this.mqttConfig.getPublisher().getUri(), publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        if (this.mqttConfig.getPublisher().getUsername() != null) {
            options.setUserName(this.mqttConfig.getPublisher().getUsername());
            options.setPassword(this.mqttConfig.getPublisher().getPassword().toCharArray());
        }
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
        return publisher;
    }

    @Bean
    public IMqttClient publisher2() throws MqttException {
        String publisherId = "publisherId2";
        IMqttClient publisher = new MqttClient(this.mqttConfig.getPublisher().getUri(), publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        if (this.mqttConfig.getPublisher().getUsername() != null) {
            options.setUserName(this.mqttConfig.getPublisher().getUsername());
            options.setPassword(this.mqttConfig.getPublisher().getPassword().toCharArray());
        }
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
        return publisher;
    }

    @Bean
    public IMqttClient publisher3() throws MqttException {
        String publisherId = "publisherId3";
        IMqttClient publisher = new MqttClient(this.mqttConfig.getPublisher().getUri(), publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        if (this.mqttConfig.getPublisher().getUsername() != null) {
            options.setUserName(this.mqttConfig.getPublisher().getUsername());
            options.setPassword(this.mqttConfig.getPublisher().getPassword().toCharArray());
        }
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
        return publisher;
    }

    @Bean
    public IMqttClient publisher4() throws MqttException {
        String publisherId = "publisherId4";
        IMqttClient publisher = new MqttClient(this.mqttConfig.getPublisher().getUri(), publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        if (this.mqttConfig.getPublisher().getUsername() != null) {
            options.setUserName(this.mqttConfig.getPublisher().getUsername());
            options.setPassword(this.mqttConfig.getPublisher().getPassword().toCharArray());
        }
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
        return publisher;
    }

    @Bean
    public IMqttClient publisher5() throws MqttException {
        String publisherId = "publisherId5";
        IMqttClient publisher = new MqttClient(this.mqttConfig.getPublisher().getUri(), publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        if (this.mqttConfig.getPublisher().getUsername() != null) {
            options.setUserName(this.mqttConfig.getPublisher().getUsername());
            options.setPassword(this.mqttConfig.getPublisher().getPassword().toCharArray());
        }
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
        return publisher;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
