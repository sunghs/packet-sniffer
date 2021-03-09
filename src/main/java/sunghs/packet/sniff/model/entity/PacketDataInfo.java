package sunghs.packet.sniff.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sunghs.packet.sniff.constant.SniffConstant;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PacketDataInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long seq;

    @Column(nullable = false)
    private Long packetHistorySeq;

    @Column(length = SniffConstant.SNAPSHOT_BYTE_LENGTH)
    private String packetData;
}
