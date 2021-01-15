package sunghs.packet.sniff.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
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

@RequiredArgsConstructor
@Service
@Slf4j
public class QueueService<T extends KafkaEntity> {

    private final Gson serializer = new GsonBuilder().create();

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Async("producer")
    public void send(final T message) {
        String serialized = serializer.toJson(message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.sendDefault(serialized);
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
    public void subs(final @Headers MessageHeaders headers, final @Payload String message) {
        log.debug("subs headers : {}, message : {}", headers.toString(), message);
        // TODO insert into
    }
}
