package sunghs.packet.sniff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunghs.packet.sniff.model.entity.PacketDataInfo;

@Repository
public interface PacketDataInfoRepository extends JpaRepository<PacketDataInfo, Long> {

}
