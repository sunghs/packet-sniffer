package sunghs.packet.sniff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunghs.packet.sniff.model.entity.PacketHistory;

@Repository
public interface PacketHistoryRepository extends JpaRepository<PacketHistory, Long> {

}
