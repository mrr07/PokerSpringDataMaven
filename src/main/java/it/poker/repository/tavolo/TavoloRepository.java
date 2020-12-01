package it.poker.repository.tavolo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.model.tavolo.Tavolo;


public interface TavoloRepository  extends CrudRepository<Tavolo, Long>,QueryByExampleExecutor <Tavolo> {

	// se ho necessità particolari
	@Query("from Tavolo t join fetch t.user u where t.id = ?1")
	Tavolo findByIDWithUser(Long id);
	
	// se ho necessità particolari
	@Query("from Tavolo t join fetch t.user u where t.user.id = ?1")
	List<Tavolo> findByIDUser(Long id);
}
