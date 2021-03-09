package sunghs.packet.sniff.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sunghs.packet.sniff.constant.TableConstant;


@Entity
@Getter
@Builder
@Table(name = TableConstant.IPV4_HISTORY)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ipv4History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long seq;

    @Column(nullable = false)
    private Long packetHistorySeq;

    @Column
    private String sourceIp;

    @Column
    private String destIp;

    @Column
    private String protocol;

    @Column
    private String version;
}
