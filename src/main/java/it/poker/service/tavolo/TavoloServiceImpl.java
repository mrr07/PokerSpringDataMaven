package it.poker.service.tavolo;

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

	@SuppressWarnings("deprecation")
	@Override
	public List<Tavolo> findByExample(Tavolo tavolo) {
		
		//bisogna passare al tavolo anche l'oggetto user per far funzionare la query
		
		
		Date data = tavolo.getDataCreazione();
		String dataDaInserire = null;
		
		if(data != null) {
			int giorno = data.getDate();
			int mese = data.getMonth() + 1;
			int anno = data.getYear() + 1900;
			
			dataDaInserire = "'"+anno;
			if (mese >= 1 && mese <= 9) {
				dataDaInserire = dataDaInserire + "-0" + mese;
			} else {
				dataDaInserire = dataDaInserire + "-" + mese;
			}
			if(giorno >= 1 && giorno <= 9) {
				dataDaInserire = dataDaInserire + "-0" + giorno + "'";
			} else {
				dataDaInserire = dataDaInserire + "-" + giorno + "'";
			}
		}
		
		
		
		
		
		String query = "select t from Tavolo t where t.id = t.id";

		if (StringUtils.isNotEmpty(tavolo.getDenominazione()))
			query += " and t.denominazione like '%" + tavolo.getDenominazione() + "%' ";
		if (tavolo.getCifraMinima() != null)
			query += " and t.cifraMinima = " + tavolo.getCifraMinima();
		/*
		 * if (tavolo.getEsperienzaMinima() != null)
	     * query += " and t.esperienzaMinima = " + tavolo.getEsperienzaMinima();
		 */
		if (dataDaInserire != null)
				query += " and t.dataCreazione = " + dataDaInserire;
		if(tavolo.getUser() != null)
			query += " and t.user = " + tavolo.getUser().getId();

		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

	@Override
	public List<Tavolo> findByIDUser(Long id) {
		return (List<Tavolo>) tavoloRepository.findByIDUser(id);
	}

	@Override
	public Tavolo findByIDWithUsers(Long id) {
		return tavoloRepository.findByIDWithUsers(id);
	}

	@Override
	public List<Tavolo> findByIDUserWithGiocatori(Long id) {
		return tavoloRepository.findByIDUserWithGiocatori(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Tavolo> findByExample2(Tavolo tavolo) {
		

		//bisogna passare al tavolo anche l'oggetto user per far funzionare la query
		
		
		Date data = tavolo.getDataCreazione();
		String dataDaInserire = null;
		
		if(data != null) {
			int giorno = data.getDate();
			int mese = data.getMonth() + 1;
			int anno = data.getYear() + 1900;
			
			dataDaInserire = "'"+anno;
			if (mese >= 1 && mese <= 9) {
				dataDaInserire = dataDaInserire + "-0" + mese;
			} else {
				dataDaInserire = dataDaInserire + "-" + mese;
			}
			if(giorno >= 1 && giorno <= 9) {
				dataDaInserire = dataDaInserire + "-0" + giorno + "'";
			} else {
				dataDaInserire = dataDaInserire + "-" + giorno + "'";
			}
		}
		
		
		
		
		
		String query = "select t from Tavolo t left join fetch t.users u where t.id = t.id";

		if (StringUtils.isNotEmpty(tavolo.getDenominazione()))
			query += " and t.denominazione like '%" + tavolo.getDenominazione() + "%' ";
		if (tavolo.getCifraMinima() != null)
			query += " and t.cifraMinima = " + tavolo.getCifraMinima();
		if (dataDaInserire != null)
				query += " and t.dataCreazione = " + dataDaInserire;
		if(tavolo.getUser() != null)
			query += " and t.user = " + tavolo.getUser().getId();
		if(!tavolo.getUsers().isEmpty())
			query += " and u.id = " + tavolo.getUsers().iterator().next().getId();

		return entityManager.createQuery(query, Tavolo.class).getResultList();
		
		
	}

}
