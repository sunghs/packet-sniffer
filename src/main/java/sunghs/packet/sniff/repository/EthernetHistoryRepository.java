package sunghs.packet.sniff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunghs.packet.sniff.model.entity.EthernetHistory;

@Repository
public interface EthernetHistoryRepository extends JpaRepository<EthernetHistory, Long> {

}
