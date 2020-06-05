package com.hui.practice.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Application {
    private static String TOPIC = "practiceTopic01";

    public static void main(String[] args) throws MqttException, InterruptedException {
        System.out.println("started.");

        String subscriberId = UUID.randomUUID().toString();
//        IMqttClient subscriber = new MqttClient("tcp://mqtt.eclipse.org:1883", subscriberId);
//        IMqttClient subscriber = new MqttClient("tcp://192.168.21.175:1883", subscriberId);
        IMqttClient subscriber = new MqttClient("tcp://192.168.1.155:1883", subscriberId);
//        IMqttClient subscriber = new MqttClient("tcp://localhost:1883", subscriberId);
        MqttConnectOptions options = new MqttConnectOptions();
//        options.setUserName("huangya");
//        options.setPassword("eectrl".toCharArray());
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        subscriber.connect(options);

        CountDownLatch receivedSignal = new CountDownLatch(10);
        int[] counter = {0};
        subscriber.subscribe(TOPIC, (topic, msg) -> {
            byte[] payload = msg.getPayload();
            String data = new String(payload, "UTF-8");
            System.out.println(++counter[0] + ": " + data);
            receivedSignal.countDown();
        });
        receivedSignal.await(1, TimeUnit.MINUTES);
    }
}
