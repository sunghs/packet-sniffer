package sunghs.packet.sniff.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.util.Strings;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import sunghs.packet.sniff.model.marker.KafkaEntity;
import sunghs.packet.sniff.util.IdxGenerator;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaClient {

    private final Gson serializer = new GsonBuilder().create();

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Async("producer")
    public <T extends KafkaEntity> void send(final T message) {
        String messageKey = IdxGenerator.generate();
        String messageBody = serializer.toJson(message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.sendDefault(messageKey, messageBody);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                // TODO Error Exception 다듬기
                log.error("exception", ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                RecordMetadata rm = result.getRecordMetadata();
                log.debug("send success, topic - {}, offset {}", rm.topic(), rm.offset());
            }
        });
    }

    @Async("consumer")
    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public String subs(@Headers final MessageHeaders headers, @Payload final String message) {
        try {
            log.debug("header : {}, body : {}", headers.toString(), message);
            return CompletableFuture.completedFuture(message).get();
        } catch (Exception e) {
            log.error("kafka subs error", e);
            return Strings.EMPTY;
        }
    }

    public <T extends KafkaEntity> T convert(String message, Class<T> t) {
        return serializer.fromJson(message, t);
    }
}
