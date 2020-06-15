package com.hui.practice.mqtt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hui.practice.mqtt.common.MqttUtilities;
import com.hui.practice.mqtt.config.MqttConfig;
import com.hui.practice.mqtt.config.QueueConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
public class QueueController {
    private final static int ERROR_SIZE = 10;

    private MqttConfig mqttConfig;
    private QueueConfig queueConfig;
    private ObjectMapper objectMapper;
    private MqttUtilities mqttUtilities;
    private Summary summary;
    private Queue<String> queue;
    private Thread enqueuingThread;
    private Thread mqttSendingThread;

    public QueueController(
            MqttConfig mqttConfig,
            QueueConfig queueConfig,
            ObjectMapper objectMapper,
            MqttUtilities mqttUtilities
    ) {
        this.mqttConfig = mqttConfig;
        this.queueConfig = queueConfig;
        this.objectMapper = objectMapper;
        this.mqttUtilities = mqttUtilities;

        this.summary = new Summary()
                .setEnqueuing(false)
                .setSendingToMqtt(false)
                .setCurrentQueueItems(0)
                .setTime(null)
                .setErrors(new ArrayList<>());
        this.queue = new LinkedBlockingQueue<>(this.queueConfig.getMqtt().getSize());
        this.enqueuingThread = new Thread(() -> {
            while (this.summary.isEnqueuing()) {
                this.enqueueOne();
                this.enqueuingSleep();
            }
        });
        this.mqttSendingThread = new Thread(() -> {
            // todo ...
        });
    }

    @PostMapping("queue/enqueuing/start")
    public Summary startEnqueuing() {
        // todo ...
    }

    private void enqueueOne() {
        // todo ...
    }

    private void enqueuingSleep() {
        try {
            Thread.sleep(this.queueConfig.getMqtt().getEnqueuingIntervalInMs());
        } catch (InterruptedException e) {
            this.addError(e);
        }
    }

    private synchronized void addError(Exception exception) {
        this.summary.errors.add(exception);
        while (this.summary.errors.size() >= ERROR_SIZE) {
            this.summary.errors.remove(0);
        }
    }

    public static class Summary {
        private boolean enqueuing;
        private boolean sendingToMqtt;
        private int currentQueueItems;
        private String time;
        private List<Exception> errors;

        public boolean isEnqueuing() {
            return enqueuing;
        }

        public Summary setEnqueuing(boolean enqueuing) {
            this.enqueuing = enqueuing;
            return this;
        }

        public boolean isSendingToMqtt() {
            return sendingToMqtt;
        }

        public Summary setSendingToMqtt(boolean sendingToMqtt) {
            this.sendingToMqtt = sendingToMqtt;
            return this;
        }

        public int getCurrentQueueItems() {
            return currentQueueItems;
        }

        public Summary setCurrentQueueItems(int currentQueueItems) {
            this.currentQueueItems = currentQueueItems;
            return this;
        }

        public String getTime() {
            return time;
        }

        public Summary setTime(String time) {
            this.time = time;
            return this;
        }

        public List<Exception> getErrors() {
            return errors;
        }

        public Summary setErrors(List<Exception> errors) {
            this.errors = errors;
            return this;
        }
    }
}
