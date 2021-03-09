package sunghs.packet.sniff.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import sunghs.packet.sniff.exception.ExceptionCodeManager;
import sunghs.packet.sniff.exception.QueueServiceException;
import sunghs.packet.sniff.model.PacketContext;
import sunghs.packet.sniff.model.entity.PacketHistory;
import sunghs.packet.sniff.model.marker.KafkaEntity;
import sunghs.packet.sniff.repository.PacketHistoryRepository;
import sunghs.packet.sniff.util.CommonUtils;
import sunghs.packet.sniff.util.EntityConverter;
import sunghs.packet.sniff.util.IdxGenerator;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaClient {

    private static final String OBJECT_TYPE = "obj_type";

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Gson gson;

    private final PacketHistoryRepository packetHistoryRepository;

    @Async("producer")
    public <T extends KafkaEntity> void send(final T data) {
        if (CommonUtils.isEmpty(data)) {
            log.debug("data is empty");
            return;
        }

        Message<String> message = MessageBuilder
            .withPayload(gson.toJson(data))
            .setHeader(KafkaHeaders.TOPIC, kafkaTemplate.getDefaultTopic())
            .setHeader(KafkaHeaders.PARTITION_ID, 0)
            .setHeader(KafkaHeaders.MESSAGE_KEY, IdxGenerator.generate())
            .setHeader(KafkaClient.OBJECT_TYPE, data.getClass().getTypeName())
            .build();

        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(message);
        result.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                QueueServiceException queueServiceException = new QueueServiceException(ExceptionCodeManager.KAFKA_PRODUCE_EXCEPTION, ex);
                log.error("kafka message send error", queueServiceException);
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
    public void subs(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partitionId, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey,
        @Header(KafkaClient.OBJECT_TYPE) String typeName, @Payload final String message) {
        try {
            Class<?> cz = ClassUtils.forName(typeName, ClassUtils.getDefaultClassLoader());
            KafkaEntity kafkaEntity = (KafkaEntity) gson.fromJson(message, cz);

            if (kafkaEntity instanceof PacketContext) {
                // TODO INSERT DB
                PacketHistory packetHistory = EntityConverter.toHistory(messageKey, (PacketContext) kafkaEntity);
                log.info(packetHistory.toString());
                packetHistoryRepository.save(packetHistory);
            } else {
                QueueServiceException queueServiceException = new QueueServiceException(ExceptionCodeManager.UNKNOWN_MESSAGE_TYPE);
                log.error("kafka {} partition {} key message convert error", partitionId, messageKey, queueServiceException);
            }
        } catch (SQLGrammarException grammarException) {
            QueueServiceException queueServiceException = new QueueServiceException(ExceptionCodeManager.KAFKA_DATA_PERSISTENCE_EXCEPTION, grammarException);
            log.error("kafka data insert error", queueServiceException);
        } catch (Exception ex) {
            QueueServiceException queueServiceException = new QueueServiceException(ExceptionCodeManager.KAFKA_CONSUME_EXCEPTION, ex);
            log.error("kafka consume error", queueServiceException);
            log.error("cause {}", ex.getMessage());
        }
    }
}
