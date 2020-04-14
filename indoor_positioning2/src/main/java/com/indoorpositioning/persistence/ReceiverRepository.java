package com.indoorpositioning.persistence;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Integer>{


	@Query(value="SELECT * FROM receiver r ORDER BY r.id_rcv ASC", nativeQuery = true)
	public List<Receiver> getReceivers();
	
	@Transactional
	//To avoid cache inconsistencies
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE receiver r SET r.x_pos=:x_pos,r.y_pos=:y_pos WHERE r.id_rcv=:id_rcv", nativeQuery = true)
	public void updateReceiver(@Param("id_rcv") Integer id_rcv,@Param("x_pos") Integer x_pos,@Param("y_pos") Integer y_pos
			);

}
