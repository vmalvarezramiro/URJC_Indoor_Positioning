package com.indoorpositioning.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BeaconRepository extends JpaRepository<Beacon, Integer>{
	@Query(value="SELECT * FROM beacon b WHERE b.id_bk NOT IN (SELECT c.id_bk FROM component c where (c.s_date is not null and c.e_date is null) AND c.id_bk is not null) ORDER BY b.id_bk ASC",nativeQuery = true)
	public List<Beacon> getAllUnusedBeacons();
	
	@Query(value="SELECT * FROM beacon b ORDER BY b.id_bk ASC", nativeQuery = true)
	public List<Beacon> getAllBeacons();
	@Transactional
	//To avoid cache inconsistencies
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE beacon b SET b.name=:name WHERE b.id_bk=:id_bk", nativeQuery = true)
	public void updateBeacon(@Param("name") String name,@Param("id_bk") Integer id_bk);
}
