package com.hui.practice.mqtt.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    private MqttConfig mqttConfig;

    public Beans(MqttConfig mqttConfig) {
        this.mqttConfig = mqttConfig;
    }

    /*@Bean
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
        if (this.mqttConfig.getPublisher().getMaxInflight() != null) {
            options.setMaxInflight(this.mqttConfig.getPublisher().getMaxInflight().intValue());
        }
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
        return publisher;
    }*/

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
