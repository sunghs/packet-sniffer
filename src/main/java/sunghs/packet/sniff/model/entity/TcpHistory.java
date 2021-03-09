package sunghs.packet.sniff.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sunghs.packet.sniff.constant.SniffType;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TcpHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long seq;

    @Column(nullable = false)
    private Long packetHistorySeq;

    @Column
    @Enumerated(EnumType.STRING)
    private SniffType tcpType;

    @Column
    private Integer sourcePort;

    @Column
    private Integer destPort;

    @Column
    private Long seqNumber;

    @Column
    private Long ackNumber;
}
