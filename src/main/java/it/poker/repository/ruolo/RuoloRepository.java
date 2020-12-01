package it.poker.repository.ruolo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.model.ruolo.Ruolo;


public interface RuoloRepository  extends CrudRepository<Ruolo, Long>,QueryByExampleExecutor <Ruolo> {

	Ruolo findByNome(String nome);
}
