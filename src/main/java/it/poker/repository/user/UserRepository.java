package it.poker.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.model.user.User;


public interface UserRepository extends CrudRepository<User, Long>,QueryByExampleExecutor <User> {
	
	List<User> findByNome(String name);
	
	@Query("from User u join fetch u.ruoli r left join fetch u.tavoli ta left join fetch u.tavolo  where u.username = ?1 and u.password = ?2")
	User findByUsernameAndPasswordWithTavolo(String username, String password);
	
	@Query("select distinct u from User u left join fetch u.tavolo t left join fetch u.tavoli ta left join fetch u.ruoli r where u.id = ?1")
	User findByID(Long id);
	
	@Query("select distinct u from User u left join fetch u.tavolo t left join fetch u.tavoli ta left join fetch u.ruoli r")
	List<User> findAllWithTavoliAndRuoli();

}
