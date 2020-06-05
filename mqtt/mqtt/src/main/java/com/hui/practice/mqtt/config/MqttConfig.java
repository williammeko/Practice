package com.hui.practice.mqtt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {
    private Publisher publisher;

    public Publisher getPublisher() {
        return publisher;
    }

    public MqttConfig setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public static class Publisher {
        private String uri;
        private String username;
        private String password;

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
    }
}
