package com.nttdata.tdb.dao.user;

import java.util.List;

import com.nttdata.tdb.domain.user.User;

/**
 * @author jean.lorenzini
 */
public interface UserDao {

	public User findUserByMatriculation(String userMatriculation);
	
	public List<User> findUserByUsernameOrMatriculation(String username, String matriculation);
	
	public User findUserById(String userId);
	
	public void saveUser(User user);
	
	public List<User> listUser();
	
	public List<String> findAllUserMatriculation();

	public void updatePassoword(User user);

	public void updatePassowordReset(User user, String senha);
}
