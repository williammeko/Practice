package com.hui.practice.mqtt.common;


import com.hui.practice.mqtt.config.AliyunConfig;
import org.apache.tomcat.util.codec.binary.Base64;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class AliyunUtilities {
    private AliyunConfig aliyunConfig;

    public AliyunUtilities(AliyunConfig aliyunConfig) {
        this.aliyunConfig = aliyunConfig;
    }

    public IMqttClient createMqttSender() throws MqttException, NoSuchAlgorithmException, InvalidKeyException {
        String clientId = this.aliyunConfig.getMqtt().getPublisher().getGroupId() + "@@@" + this.aliyunConfig.getMqtt().getPublisher().getDeviceId();
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
        options.setServerURIs(new String[]{this.aliyunConfig.getMqtt().getPublisher().getUri()});
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        options.set

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
