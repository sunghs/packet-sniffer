package sunghs.packet.sniff;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sunghs.packet.sniff.service.TcpSniffService;

@RequiredArgsConstructor
@SpringBootApplication
public class PacketSnifferApplication implements CommandLineRunner {

    private final TcpSniffService tcpSniffService;

    public static void main(String[] args) {
        SpringApplication.run(PacketSnifferApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        tcpSniffService.sniff();
    }
}
