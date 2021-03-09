package sunghs.packet.sniff.exception;

import lombok.Getter;

public enum ExceptionCodeManager {

    OK("1000", "성공"),
    KAFKA_PRODUCE_EXCEPTION("2000", "카프카 메시지 전송 실패"),
    KAFKA_CONSUME_EXCEPTION("3000", "카프카 메시지 수신 실패"),
    KAFKA_DATA_PERSISTENCE_EXCEPTION("3001", "카프카 데이터 저장 실패"),
    SNIFF_EXCEPTION("4000", "패킷 스니핑 실패"),
    FAIL_FIND_DEVICE("5000", "device 를 찾을 수 없음"),
    UNKNOWN_MESSAGE_TYPE("6000", "알 수 없는 메시지 Object 타입"),
    ERROR("9999", "기타 에러");

    @Getter
    private String code;

    @Getter
    private String cause;

    ExceptionCodeManager(String code, String cause) {
        this.code = code;
        this.cause = cause;
    }
}
