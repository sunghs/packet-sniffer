/**
 * CREATE DATABASE
 */
CREATE SCHEMA packet;

/**
 * TABLE DDL SCRIPT
 */
CREATE TABLE packet.packet_history (
    seq BIGINT NOT NULL AUTO_INCREMENT comment 'seq',
    idx VARCHAR(24) NOT NULL comment '고유 id',
    transmission_direction VARCHAR(16) comment '패킷 방향',
    packet_type VARCHAR(16) comment '패킷 타입',
    sniff_time DATETIME comment '패킷 캡쳐 시간',
    PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='패킷 이력';

CREATE TABLE packet.ethernet_history (
    seq BIGINT NOT NULL AUTO_INCREMENT comment 'seq',
    packet_history_seq BIGINT NOT NULL comment 'packet_history 의 참조 seq',
    source_address VARCHAR(255) comment '출발지 address',
    dest_address VARCHAR(255) comment '도착지 address',
    header_length INT comment '헤더 길이',
    PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='이더넷 이력';

CREATE TABLE packet.ipv4_history (
    seq BIGINT NOT NULL AUTO_INCREMENT comment 'seq',
    packet_history_seq BIGINT NOT NULL comment 'packet_history 의 참조 seq',
    source_ip VARCHAR(255) comment '출발지 ip',
    dest_ip VARCHAR(255) comment '도착지 ip',
    protocol VARCHAR(255) comment '프로토콜 명',
    version VARCHAR(255) comment '프로토콜 버전',
    PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='IPv4 이력';

CREATE TABLE packet.tcp_history (
    seq BIGINT NOT NULL AUTO_INCREMENT comment 'seq',
    packet_history_seq BIGINT NOT NULL comment 'packet_history 의 참조 seq',
    tcp_type VARCHAR(16) comment 'TCP 캡쳐 타입',
    source_port INT comment '출발지 port',
    dest_port INT comment '도착지 port',
    seq_number BIGINT comment 'sequence 번호, 현재 패킷의 위치',
    ack_number BIGINT comment 'acknowledgment 번호, 다음에 받을 패킷 위치',
    PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='TCP 이력';

CREATE TABLE packet.packet_data_info (
    seq BIGINT NOT NULL AUTO_INCREMENT comment 'seq',
    packet_history_seq BIGINT NOT NULL comment 'packet_history 의 참조 seq',
    packet_data MEDIUMTEXT comment '패킷 데이터',
    PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='패킷 데이터 이력';

COMMIT;