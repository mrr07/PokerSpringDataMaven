package it.poker.service.ruolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.poker.model.ruolo.Ruolo;
import it.poker.repository.ruolo.RuoloRepository;

@Component
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Override
	public List<Ruolo> listAllRuoli() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	@Override
	public Ruolo findById(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	public Ruolo findByNome(String nome) {
		return ruoloRepository.findByNome(nome);
	}

}
