package com.hui.practice.mqtt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "queue")
public class QueueConfig {
    private Mqtt mqtt;

    public Mqtt getMqtt() {
        return mqtt;
    }

    public QueueConfig setMqtt(Mqtt mqtt) {
        this.mqtt = mqtt;
        return this;
    }

    public static class Mqtt {
        private int size;
        private long enqueuingIntervalInMs;

        public int getSize() {
            return size;
        }

        public Mqtt setSize(int size) {
            this.size = size;
            return this;
        }

        public long getEnqueuingIntervalInMs() {
            return enqueuingIntervalInMs;
        }

        public Mqtt setEnqueuingIntervalInMs(long enqueuingIntervalInMs) {
            this.enqueuingIntervalInMs = enqueuingIntervalInMs;
            return this;
        }
    }
}
