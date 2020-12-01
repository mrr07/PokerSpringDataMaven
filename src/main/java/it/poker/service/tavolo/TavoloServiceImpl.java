package it.poker.service.tavolo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.poker.model.tavolo.Tavolo;
import it.poker.repository.tavolo.TavoloRepository;

@Component
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	private TavoloRepository tavoloRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Tavolo> listAllTavoli() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Override
	public Tavolo findById(Long id) {
		return tavoloRepository.findByIDWithUser(id);
	}

	@Override
	public void update(Tavolo tavolo) {
		tavoloRepository.save(tavolo);

	}

	@Override
	public void addNew(Tavolo tavolo) {
		tavoloRepository.save(tavolo);

	}

	@Override
	public void delete(Tavolo tavolo) {
		tavoloRepository.delete(tavolo);

	}

	@Override
	public List<Tavolo> findByExample(Tavolo tavolo) {
		
		//bisogna passare al tavolo anche l'oggetto user per far funzionare la query
		
		
		String query = "select t from Tavolo t where t.id = t.id";

		if (StringUtils.isNotEmpty(tavolo.getDenominazione()))
			query += " and t.denominazione like '%" + tavolo.getDenominazione() + "%' ";
		if (tavolo.getCifraMinima() != null)
			query += " and t.cifraMinima = " + tavolo.getCifraMinima();
//		if (tavolo.getEsperienzaMinima() != null)
//			query += " and t.esperienzaMinima = " + tavolo.getEsperienzaMinima();
		if (tavolo.getDataCreazione() != null)
			try {
				query += " and t.dataCreazione = '" + (new SimpleDateFormat("yyyy-MM-dd")).parse(tavolo.getDataCreazione().toString())+ "'";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		if(tavolo.getUser() != null)
			query += " and t.user = " + tavolo.getUser().getId();

		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

	@Override
	public List<Tavolo> findByIDUser(Long id) {
		return (List<Tavolo>) tavoloRepository.findByIDUser(id);
	}

}
