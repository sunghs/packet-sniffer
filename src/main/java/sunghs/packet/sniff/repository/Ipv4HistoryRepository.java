package sunghs.packet.sniff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunghs.packet.sniff.model.entity.Ipv4History;

@Repository
public interface Ipv4HistoryRepository extends JpaRepository<Ipv4History, Long> {

}
