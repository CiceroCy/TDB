package com.nttdata.tdb.services.user;

import java.util.List;

import com.nttdata.tdb.domain.user.User;



public interface UserService {
	
	public List<User> findUserByUsernameOrMatriculation(String username, String matriculation);
	
	public User findUserByMatriculation(String userMatriculation);
	
	public User findUserById(String userId);
	
    public void saveUser(User user);
    
	public List<User> listUser();

	public void updatePassword(User user);

	public void updatePasswordReset(User user, String senha);
	
	
}
