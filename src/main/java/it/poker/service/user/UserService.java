package it.poker.service.user;

import java.util.List;

import it.poker.model.user.User;


public interface UserService {
	
	public List<User> listAllUsers();

	public User findById(Long id);
	
	public void update(User user);

	public void addNew(User user);

	public void delete(User user);
	
	public User findByUsernameAndPassword(String username, String password);
	
	public List<User> listAllUsersWithTavoliAndRuoli();
	
	public List<User> findByExample(User user);
	
	public List<User> findByUsernameByLike(String username);
	
	public List<User> listAllUsersSpecial(String nomeRuolo, String term);

}
