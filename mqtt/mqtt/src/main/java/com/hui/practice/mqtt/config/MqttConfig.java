package com.hui.practice.mqtt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {
    private String provider;
    private Publisher publisher;
    private Publishing publishing;

    public String getProvider() {
        return provider;
    }

    public MqttConfig setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public MqttConfig setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public Publishing getPublishing() {
        return publishing;
    }

    public MqttConfig setPublishing(Publishing publishing) {
        this.publishing = publishing;
        return this;
    }

    public static class Publisher {
        private String uri;
        private String username;
        private String password;
        private Integer maxInflight;

        public String getUri() {
            return uri;
        }

        public Publisher setUri(String uri) {
            this.uri = uri;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public Publisher setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public Publisher setPassword(String password) {
            this.password = password;
            return this;
        }

        public Integer getMaxInflight() {
            return maxInflight;
        }

        public Publisher setMaxInflight(Integer maxInflight) {
            this.maxInflight = maxInflight;
            return this;
        }
    }

    public static class Publishing {
        private int qos;
        private boolean retained;

        public int getQos() {
            return qos;
        }

        public Publishing setQos(int qos) {
            this.qos = qos;
            return this;
        }

        public boolean isRetained() {
            return retained;
        }

        public Publishing setRetained(boolean retained) {
            this.retained = retained;
            return this;
        }
    }
}
