package sunghs.packet.sniff.model.entity;

import java.time.LocalDateTime;
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
import sunghs.packet.sniff.constant.PacketType;
import sunghs.packet.sniff.constant.TransmissionDirection;
import sunghs.packet.sniff.util.IdxGenerator;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PacketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(length = IdxGenerator.IDX_DIGIT)
    private String idx;

    @Column
    private TransmissionDirection transmissionDirection;

    @Column
    private PacketType packetType;

    @Column
    private LocalDateTime sniffTime;
}
