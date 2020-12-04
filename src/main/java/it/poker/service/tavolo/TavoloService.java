package it.poker.service.tavolo;

import java.util.List;

import it.poker.model.tavolo.Tavolo;

public interface TavoloService {

	public List<Tavolo> listAllTavoli();

	public Tavolo findById(Long id);
	
	public void update(Tavolo tavolo);

	public void addNew(Tavolo tavolo);

	public void delete(Tavolo tavolo);
	
	public List<Tavolo> findByExample(Tavolo tavolo);
	
	public List<Tavolo> findByExample2(Tavolo tavolo);
	
	public List<Tavolo> findByIDUser(Long id);
	
	public Tavolo findByIDWithUsers(Long id);
	
	public List<Tavolo> findByIDUserWithGiocatori(Long id);

	
}
