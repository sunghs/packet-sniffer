package sunghs.packet.sniff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sunghs.packet.sniff.model.entity.PacketHistory;

public interface PacketHistoryRepository extends JpaRepository<PacketHistory, Long> {

}
