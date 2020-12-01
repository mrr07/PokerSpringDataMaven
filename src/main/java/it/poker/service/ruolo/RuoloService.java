package it.poker.service.ruolo;

import java.util.List;

import it.poker.model.ruolo.Ruolo;

public interface RuoloService {
	
	public List<Ruolo> listAllRuoli();

	public Ruolo findById(Long id);
	
	public Ruolo findByNome(String nome);

}
