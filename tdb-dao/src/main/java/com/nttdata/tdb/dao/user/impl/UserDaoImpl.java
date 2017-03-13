package com.nttdata.tdb.dao.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.nttdata.tdb.dao.domain.user.util.EncryptPass;
import com.nttdata.tdb.dao.user.UserDao;
import com.nttdata.tdb.domain.user.Role;
import com.nttdata.tdb.domain.user.User;

/**
 * @author jean.lorenzini
 */
@Repository
class UserDaoImpl implements UserDao{
	
	private static final String ADMIN = "admin";
	
	@Value("${msg.duplicateKey}")
	private String duplicateKey;
	
	@Value("${msg.notSave}")
	private String erroSave;

	private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());

	@Resource(name = "mongoTemplate")
	private MongoOperations mongoOperation;
	
	@Override
	public List<User> findUserByUsernameOrMatriculation(String username, String matriculation) {
		
		LOG.log(Level.INFO, "Starting UserDaoImpl - findUser method");
		
		Criteria criteria = new Criteria();
		
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(matriculation)){
			criteria.orOperator(Criteria.where("username").regex(username),Criteria.where("matriculation").regex(matriculation));
		} else if (StringUtils.isNotBlank(username) &&  StringUtils.isNotBlank(matriculation)){
			criteria = Criteria.where("username").regex(username);
		} else if (StringUtils.isNotBlank(username) &&  StringUtils.isNotBlank(matriculation)){
			criteria = Criteria.where("matriculation").regex(matriculation);
		}
		
		// query to search user
		Query searchUserQuery = new Query(criteria);
		
		// find user again.
		List<User> result = mongoOperation.find(searchUserQuery, User.class);
		
		LOG.log(Level.INFO, "Finishing UserDaoImpl - findUser method");
		
		return result;
	}
	
	@Override
	public User findUserByMatriculation(String userMatriculation) {
		
		LOG.log(Level.INFO, "Starting UserDaoImpl - findUser method");
		
		// query to search user
		Query searchUserQuery = new Query(Criteria.where("matriculation").is(userMatriculation));

		// find user again.
		User result = mongoOperation.findOne(searchUserQuery, User.class);
		
		LOG.log(Level.INFO, "Finishing UserDaoImpl - findUser method");
		
		return result;
	}
	
	@Override
	public User findUserById(String userId) {
		
		LOG.log(Level.INFO, "Starting UserDaoImpl -findUserByIdr method");
		
		// query to search user
		Query searchUserQuery = new Query(Criteria.where("id").is(userId));
		
		// find user
		User result = mongoOperation.findOne(searchUserQuery, User.class);
		
		LOG.log(Level.INFO, "Finishing UserDaoImpl - findUserById method");
		
		return result;
	}
	
	
	@Override
	public List<String> findAllUserMatriculation(){
		
		Query query = new Query(Criteria.where("active").is(true));
		query.fields().include("matriculation");
		
		List<User> matriculationList = mongoOperation.find(query, User.class);
		
		List<String> result = new ArrayList<String>();
		
		for(User usr : matriculationList){
			if(usr.getMatriculation() != null && !ADMIN.equals(usr.getMatriculation())){
				result.add(usr.getMatriculation());
			}
		}
		
		return result;
	}
	
	@Override
	public List<User> listUser() {
		
		LOG.log(Level.INFO, "Starting UserDaoImpl - listUsers method");
		
		List<User> result = mongoOperation.findAll(User.class);
		
		LOG.log(Level.INFO, "Finishing UserDaoImpl - listUsers method");
		
		return result;
	}



	@Override
	public void updatePassoword(User user) {
		
		LOG.log(Level.INFO, "Starting UserDaoImpl - updatePassoword method");
		
		// query to search user
		Query updateUserQuery = new Query(Criteria.where("id").is(user.getId()));
		
		user.setPassword(EncryptPass.encryptPass(user.getPassword()));
		
		Update update = new Update();
		update.set("password", user.getPassword());
		
        try{
		mongoOperation.findAndModify(updateUserQuery, update, User.class);
        }catch (Exception e) {
			LOG.log(Level.ERROR,"ERRO-UserDaoImpl - updatePassoword method" ,e);
			user.addMessage(User.ERROR,erroSave );
		}
	}

	
	@Override
	public void updatePassowordReset(User user,String senha) {
		
		LOG.log(Level.INFO, "Starting UserDaoImpl - updatePassowordReset method");
		
		// query to search user
		Query updateUserQuery = new Query(Criteria.where("id").is(user.getId()));
		
		user.setPassword(EncryptPass.encryptPass(senha));
		
		Update update = new Update();
		update.set("password", user.getPassword());
		
        try{
		mongoOperation.findAndModify(updateUserQuery, update, User.class);
		
        }catch (Exception e) {
			LOG.log(Level.ERROR,"ERRO-UserDaoImpl - updatePassoword method" ,e);
			user.addMessage(User.ERROR,erroSave );
		}
	}
	
	@Override
	public void saveUser(User user) {
		
       inicializaVariaveisPadraoSave(user);
		
		
		try{
		mongoOperation.save(user);
		
		}catch(DuplicateKeyException e){
			LOG.log(Level.ERROR,"ERRO-UserDaoImpl - saveUser method" ,e);
			user.addMessage(User.ERROR, duplicateKey);
		}
		catch(Exception e){
			LOG.log(Level.ERROR,"ERRO-UserDaoImpl - saveUser method" ,e);
			user.addMessage(User.ERROR,erroSave );
		}
	}

	
	private void inicializaVariaveisPadraoSave(User user) {
		if(StringUtils.isBlank(user.getId())){
			
			user.setId(null);
			user.setPassword(EncryptPass.DEFAULT_PASSWORD);
			
			if(user.getRoles() == null){
				user.setRoles(new ArrayList<Role>());
			}
			
			if(user.getRoles().isEmpty()){
				// query to search role
				Query searchUserQuery = new Query(Criteria.where("description").is("USER"));
				
				// find user
				Role userDefaultRole = mongoOperation.findOne(searchUserQuery, Role.class);
				
				user.getRoles().add(userDefaultRole);
			}
		}
	
	}

 
}
