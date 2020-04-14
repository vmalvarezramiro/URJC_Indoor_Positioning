package com.indoorpositioning.persistence;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
	
@Repository
public interface HistoryRepository extends JpaRepository<History,Integer>{
	//Get the n receivers id_rcv with the best rssi for the last read of the beacon with the id_bk
		
	@Query(value="SELECT * FROM history h WHERE (h.id_bk=:id_bk AND h.id_rcv=:id_rcv) AND (h.date BETWEEN :dateLimitStr AND :lastHistoryDateStr) ORDER BY h.date DESC",nativeQuery = true)
	public List<History> getLastHistoriesOfBkInRcv(@Param("id_bk") Integer id_bk,@Param("id_rcv") Integer id_rcv,@Param("dateLimitStr") String dateLimitStr,@Param("lastHistoryDateStr") String lastHistoryDateStr, Pageable pagRcvHistories);
	//Even if it will only return a list with one history, it must return a list because Pageable its being used
	@Query(value="SELECT * FROM history h WHERE h.id_bk=:id_bk ORDER BY h.date DESC",nativeQuery = true)
	public List<History> getLastHistoryOfBk(@Param("id_bk") Integer id_bk,Pageable pagLastNHistory);
}
