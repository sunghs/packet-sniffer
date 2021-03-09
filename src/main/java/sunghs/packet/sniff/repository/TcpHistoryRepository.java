package sunghs.packet.sniff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunghs.packet.sniff.model.entity.TcpHistory;

@Repository
public interface TcpHistoryRepository extends JpaRepository<TcpHistory, Long> {

}
