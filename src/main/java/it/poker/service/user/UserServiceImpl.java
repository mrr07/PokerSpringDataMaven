package it.poker.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.poker.model.user.User;
import it.poker.repository.user.UserRepository;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
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

}
