package com.hui.practice.mqtt.common;


import com.hui.practice.mqtt.config.AliyunConfig;
import org.apache.tomcat.util.codec.binary.Base64;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class AliyunUtilities {
    private AliyunConfig aliyunConfig;

    public AliyunUtilities(AliyunConfig aliyunConfig) {
        this.aliyunConfig = aliyunConfig;
    }

    public IMqttClient createMqttSender() throws MqttException, NoSuchAlgorithmException, InvalidKeyException {
        String clientId = this.aliyunConfig.getMqtt().getPublisher().getGroupId() + "@@@" + this.aliyunConfig.getMqtt().getPublisher().getDeviceId();

        System.out.println("uri: " + this.aliyunConfig.getMqtt().getPublisher().getUri());
        System.out.println("clientId: " + clientId);
        System.out.println("cleanSession: " + this.aliyunConfig.getMqtt().getPublisher().getCleanSession());

        IMqttClient client = new MqttClient(this.aliyunConfig.getMqtt().getPublisher().getUri(), clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("Signature|" + this.aliyunConfig.getMqtt().getPublisher().getAccessKeyId() + "|" + this.aliyunConfig.getMqtt().getPublisher().getInstanceId());
        options.setPassword(
                this.generateMqttSignature(clientId, this.aliyunConfig.getMqtt().getPublisher().getAccessKeySecret())
                        .toCharArray()
        );
        if (this.aliyunConfig.getMqtt().getPublisher().getMaxInflight() != null) {
            options.setMaxInflight(this.aliyunConfig.getMqtt().getPublisher().getMaxInflight().intValue());
        }
        if (this.aliyunConfig.getMqtt().getPublisher().getCleanSession() != null) {
            options.setCleanSession(this.aliyunConfig.getMqtt().getPublisher().getCleanSession().booleanValue());
        }
        options.setServerURIs(new String[]{this.aliyunConfig.getMqtt().getPublisher().getUri()});
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

    private String generateMqttSignature(String clientId, String accessKeySecret) throws InvalidKeyException, NoSuchAlgorithmException {
        Charset charset = Charset.forName("UTF-8");
        String algorithm = "HmacSHA1";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(accessKeySecret.getBytes(charset), algorithm));
        byte[] bytes = mac.doFinal(clientId.getBytes(charset));
        return new String(Base64.encodeBase64(bytes), charset);
    }
}
