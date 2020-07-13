package sunghs.packet.sniff;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PacketSnifferApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PacketSnifferApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
