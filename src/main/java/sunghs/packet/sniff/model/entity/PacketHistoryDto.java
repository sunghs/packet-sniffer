package sunghs.packet.sniff.model.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import sunghs.packet.sniff.constant.PacketType;
import sunghs.packet.sniff.constant.SniffType;
import sunghs.packet.sniff.constant.TransmissionDirection;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PacketHistoryDto {

    private Long seq;

    private String idx;

    private TransmissionDirection transmissionDirection;

    private PacketType packetType;

    private String sourceIp;

    private String sourceAddress;

    private Integer sourcePort;

    private String destIp;

    private String destAddress;

    private Integer destPort;

    private SniffType tcpType;

    private LocalDateTime sniffTime;
}
