package it.poker.service.user;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.poker.model.ruolo.Ruolo;
import it.poker.model.tavolo.Tavolo;
import it.poker.model.user.User;
import it.poker.repository.user.UserRepository;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> listAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userRepository.findByID(id);
	}

	@Override
	@Transactional
	public void update(User user) {
		userRepository.save(user);
		
	}

	@Override
	@Transactional
	public void addNew(User user) {
		userRepository.save(user);
		
	}

	@Override
	@Transactional
	public void delete(User user) {
		userRepository.delete(user);
		
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPasswordWithTavolo(username, password);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listAllUsersWithTavoliAndRuoli() {
		return userRepository.findAllWithTavoliAndRuoli();
	}

	@Override
	public List<User> findByExample(User user) {
		
		Date data = user.getDataRegistrazione();
		String dataDaInserire = null;

		if (data != null) {
			int giorno = data.getDate();
			int mese = data.getMonth() + 1;
			int anno = data.getYear() + 1900;

			dataDaInserire = "'" + anno;
			if (mese >= 1 && mese <= 9) {
				dataDaInserire = dataDaInserire + "-0" + mese;
			} else {
				dataDaInserire = dataDaInserire + "-" + mese;
			}
			if (giorno >= 1 && giorno <= 9) {
				dataDaInserire = dataDaInserire + "-0" + giorno + "'";
			} else {
				dataDaInserire = dataDaInserire + "-" + giorno + "'";
			}
		}

		String query = "select distinct u from User u left join fetch u.ruoli r where u.id = u.id";

		if (StringUtils.isNotEmpty(user.getNome()))
			query += " and u.nome like '%" + user.getNome() + "%' ";
		if (StringUtils.isNotEmpty(user.getNome()))
			query += " and u.cognome like '%" + user.getCognome() + "%' ";
		if (StringUtils.isNotEmpty(user.getNome()))
			query += " and u.username like '%" + user.getUsername() + "%' ";
		if (user.getCredito() != null)
			query += " and u.credito = " + user.getCredito();
		if (user.getEsperienza() != null)
			query += " and u.esperienza = " + user.getEsperienza();
		if (dataDaInserire != null)
			query += " and u.dataRegistrazione = " + dataDaInserire;
		if (user.getRuoli() != null) {
			Set<Ruolo> listaRuoliUser = user.getRuoli();
			
			for(Ruolo ruolo : listaRuoliUser) {
				query += " and r= " + ruolo.getId();
			}
		}
		if (user.getStato() != null)
			query += " and u.stato = " + "'" + user.getStato() + "'";
		

		return entityManager.createQuery(query, User.class).getResultList();
		
	}

}
