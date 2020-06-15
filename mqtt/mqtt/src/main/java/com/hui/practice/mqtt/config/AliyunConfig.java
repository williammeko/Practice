package com.hui.practice.mqtt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aliyun")
public class AliyunConfig {
    private Mqtt mqtt;

    public Mqtt getMqtt() {
        return mqtt;
    }

    public AliyunConfig setMqtt(Mqtt mqtt) {
        this.mqtt = mqtt;
        return this;
    }

    public static class Mqtt {
        private Publisher publisher;

        public Publisher getPublisher() {
            return publisher;
        }

        public Mqtt setPublisher(Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public static class Publisher {
            private String uri;
            private String instanceId;
            private String accessKeyId;
            private String accessKeySecret;
            private String groupId;
            private String deviceId;
            private String topic;
            private Integer maxInflight;
            private Boolean cleanSession;

            public String getUri() {
                return uri;
            }

            public Publisher setUri(String uri) {
                this.uri = uri;
                return this;
            }

            public String getInstanceId() {
                return instanceId;
            }

            public Publisher setInstanceId(String instanceId) {
                this.instanceId = instanceId;
                return this;
            }

            public String getAccessKeyId() {
                return accessKeyId;
            }

            public Publisher setAccessKeyId(String accessKeyId) {
                this.accessKeyId = accessKeyId;
                return this;
            }

            public String getAccessKeySecret() {
                return accessKeySecret;
            }

            public Publisher setAccessKeySecret(String accessKeySecret) {
                this.accessKeySecret = accessKeySecret;
                return this;
            }

            public String getGroupId() {
                return groupId;
            }

            public Publisher setGroupId(String groupId) {
                this.groupId = groupId;
                return this;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public Publisher setDeviceId(String deviceId) {
                this.deviceId = deviceId;
                return this;
            }

            public String getTopic() {
                return topic;
            }

            public Publisher setTopic(String topic) {
                this.topic = topic;
                return this;
            }

            public Integer getMaxInflight() {
                return maxInflight;
            }

            public Publisher setMaxInflight(Integer maxInflight) {
                this.maxInflight = maxInflight;
                return this;
            }

            public Boolean getCleanSession() {
                return cleanSession;
            }

            public Publisher setCleanSession(Boolean cleanSession) {
                this.cleanSession = cleanSession;
                return this;
            }
        }
    }
}
