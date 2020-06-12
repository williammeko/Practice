package com.hui.practice.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Application {
    private static String TOPIC = "practiceTopic01";

    public static void main(String[] args) throws MqttException, InterruptedException, InvalidKeyException, NoSuchAlgorithmException {
        System.out.println("started.");

        mqttSubscribe();
//        aliyunMqttSubscribe();
    }

    private static void mqttSubscribe() throws MqttException, InterruptedException {
        String uri = "tcp://192.168.1.155:1883";
        String clientId = "GJ1038-sub";
        String username = "huangya";
        String password = "eectrl";
        String topic = "topic01";
        int qos = 0;

/*
        IMqttClient subscriber = new MqttClient("tcp://mqtt.eclipse.org:1883", subscriberId);
        IMqttClient subscriber = new MqttClient("tcp://192.168.21.175:1883", subscriberId);
        IMqttClient subscriber = new MqttClient("tcp://192.168.1.155:1883", subscriberId);
        options.setUserName("huangya");
        options.setPassword("eectrl".toCharArray());
*/

        IMqttClient client = new MqttClient(uri, clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        if (username != null) {
            options.setUserName(username);
            options.setPassword(password.toCharArray());
        }
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        beginSubscribing(client, options, topic, qos);
    }

    private static void aliyunMqttSubscribe() throws MqttException, InterruptedException, NoSuchAlgorithmException, InvalidKeyException {
        String instanceId = "mqtt-cn-nif1oue6k01";
        String accessKeyId = "LTAI4G69iguC541BCM1qeNnM";
        String accessKeySecret = "5pyimVdDZOfMUhbh1ViraQavTfoimx";
        String uri = "tcp://mqtt-cn-nif1oue6k01.mqtt.aliyuncs.com:1883";
        String groupId = "GID-001";
        String deviceId = "GJ1038-sub";
        String topic = "topic01";
        int qos = 2;
        Integer maxInflight = 10;

        String clientId = groupId + "@@@" + deviceId;
        IMqttClient client = new MqttClient(uri, clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("Signature|" + accessKeyId + "|" + instanceId);
        options.setPassword(
                generateMqttSignature(clientId, accessKeySecret)
                        .toCharArray()
        );
        if (maxInflight != null) {
            options.setMaxInflight(maxInflight.intValue());
        }
        options.setServerURIs(new String[]{uri});
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        beginSubscribing(client, options, topic, qos);
    }

    private static void beginSubscribing(IMqttClient client, MqttConnectOptions options, String topic, int qos) throws MqttException, InterruptedException {
        client.connect(options);

        CountDownLatch receivedSignal = new CountDownLatch(10);
        int[] counter = {0};
        IMqttMessageListener listener = (t, msg) -> {
            byte[] payload = msg.getPayload();
            String data = new String(payload, "UTF-8");
            System.out.println(++counter[0] + ": " + data);
            receivedSignal.countDown();
        };
        client.subscribe(topic, qos, listener);
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost: " + cause.getMessage());
                try {
                    client.connect(options);
                    System.out.println("reconnected.");
                } catch (MqttException e) {
                    e.printStackTrace();
                    System.out.println("reconnecting failed: " + e.getMessage());
                    return;
                }
                try {
                    client.subscribe(topic, qos, listener);
                    System.out.println("resubscribed.");
                } catch (MqttException e) {
                    e.printStackTrace();
                    System.out.println("resubscribing failed: " + e.getMessage());
                    return;
                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("messageArrived: (" + topic + ") " + message.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete: " + token.toString());
            }
        });
        receivedSignal.await(1, TimeUnit.MINUTES);
    }

    private static String generateMqttSignature(String clientId, String accessKeySecret) throws InvalidKeyException, NoSuchAlgorithmException {
        Charset charset = Charset.forName("UTF-8");
        String algorithm = "HmacSHA1";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(accessKeySecret.getBytes(charset), algorithm));
        byte[] bytes = mac.doFinal(clientId.getBytes(charset));
        return new String(Base64.getEncoder().encode(bytes), charset);
    }
}
