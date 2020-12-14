package sunghs.packet.sniff;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sunghs.packet.sniff.service.PacketSniffService;
import sunghs.packet.sniff.service.RawPacketSniffService;

@RequiredArgsConstructor
@SpringBootApplication
public class PacketSnifferApplication implements CommandLineRunner {

    private final PacketSniffService packetSniffService;

    private final RawPacketSniffService rawPacketSniffService;

    public static void main(String[] args) {
        SpringApplication.run(PacketSnifferApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        packetSniffService.listen();
        //rawPacketSniffService.listen();
    }
}
