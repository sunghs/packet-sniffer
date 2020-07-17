package sunghs.packet.sniff.model;

import lombok.Data;

@Data
public class TcpInfo {

    private NetworkInfo from;

    private NetworkInfo to;

    private String hex;

    private String type;

    private String desc;
}
