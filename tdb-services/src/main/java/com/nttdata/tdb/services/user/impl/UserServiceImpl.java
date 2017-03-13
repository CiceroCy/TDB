package com.nttdata.tdb.services.user.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.tdb.dao.user.UserDao;
import com.nttdata.tdb.domain.user.User;
import com.nttdata.tdb.services.user.UserService;

@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());

	@Override
	public List<User> findUserByUsernameOrMatriculation(String username, String matriculation) {
		
		LOG.log(Level.INFO, "Executing UserServiceImpl - findUser method");
		
		List<User> result = userDao.findUserByUsernameOrMatriculation(username, matriculation);
		
		LOG.log(Level.INFO, "Finishing UserServiceImpl - findUser method");
		
		return result;
	}
	
	@Override
	public User findUserByMatriculation(String userMatriculation) {
		
		LOG.log(Level.INFO, "Executing UserServiceImpl - findUser method");
		
		User result = userDao.findUserByMatriculation(userMatriculation);
		
		LOG.log(Level.INFO, "Finishing UserServiceImpl - findUser method");
		
		return result;
	}
	
	@Override
	public User findUserById(String userId) {
		LOG.log(Level.INFO, "Executing UserServiceImpl - findUser method");
		
		User result = userDao.findUserById(userId);
		
		LOG.log(Level.INFO, "Finishing UserServiceImpl - findUser method");
		
		return result;
	}

	@Override
	public void saveUser(User user) {
		
		LOG.log(Level.INFO, "Executing UserServiceImpl - saveUser method");
		  
		userDao.saveUser(user);
		
		LOG.log(Level.INFO, "Finish UserServiceImpl - saveUser method");
		
	}

	

	@Override
	public List<User> listUser() {
		
		LOG.log(Level.INFO, "Executing UserServiceImpl - listUser method");
		
	      List<User> result= userDao.listUser();
		
		
		LOG.log(Level.INFO, "Finishing UserServiceImpl - listUser method");
		return result;
	}

	@Override
	public void updatePassword(User user) {
		LOG.log(Level.INFO, "Executing UserServiceImpl - updatePassword method");
		
		userDao.updatePassoword(user);
		
		LOG.log(Level.INFO, "Finishing UserServiceImpl - updatePassword method");
	}

	@Override
	public void updatePasswordReset(User user,String senha) {
		LOG.log(Level.INFO, "Executing UserServiceImpl - updatePasswordReset method");
		
		userDao.updatePassowordReset(user, senha);
		
		LOG.log(Level.INFO, "Finishing UserServiceImpl - updatePasswordReset method");
	}

	
}
