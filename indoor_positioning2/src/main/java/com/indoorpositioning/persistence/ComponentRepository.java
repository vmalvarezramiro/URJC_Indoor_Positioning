package com.indoorpositioning.persistence;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ComponentRepository extends JpaRepository<Component,Integer>{
	@Query(value="SELECT * FROM component c ORDER BY c.name ASC",nativeQuery = true)
	public List<Component> getAllComponents();
	
	@Query(value="SELECT * FROM component c WHERE (c.s_date is not null and c.e_date is null) AND c.id_bk is not null ORDER BY c.name ASC",nativeQuery = true)
	public List<Component> getAllActiveComponents();
	
	@Query(value="SELECT * FROM component c WHERE (c.s_date is null and c.e_date is null) AND c.id_bk is null ORDER BY c.name ASC",nativeQuery = true)
	public List<Component> getAllInactiveComponents();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE component c SET c.e_date=:date WHERE c.id_cmp=:id_cmp", nativeQuery = true)
	public void unpairComponent(@Param("id_cmp") Integer id_cmp,@Param("date") Date date);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE component c SET c.id_bk=:id_bk,c.s_date=:date WHERE c.id_cmp=:id_cmp", nativeQuery = true)
	public void pairComponent(@Param("id_bk") Integer id_bk,@Param("id_cmp") Integer id_cmp,@Param("date") Date date);

	
	
}
