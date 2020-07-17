package sunghs.packet.sniff.model;

import lombok.Data;

@Data
public class NetworkInfo {

    private String ip;

    private String port;

    private String mac;
}
