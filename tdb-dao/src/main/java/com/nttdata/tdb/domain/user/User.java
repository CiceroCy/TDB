package com.nttdata.tdb.domain.user;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.tdb.dao.domain.user.util.MessagesResult;

@Document(collection = "users")
public class User  extends MessagesResult{

	private static final Logger LOG = Logger.getLogger(User.class.getName());
	
	@Id
	private String id;
    
	@NotNull
	private String username;

	private String password;
	
	@NotNull
	@Indexed(unique = true) 	
	private String matriculation;
	
	@NotNull
	private String email;
	
	@NotNull
	private String area;

	private Boolean active;
	
	@DBRef
	private List<Role> roles;
	
	/**
	 * Default constructor
	 */
	public User() { }
	
	/**
	 * @param username
	 * @param password
	 * @param matriculation
	 * @param email
	 * @param area
	 * @param active
	 * @param roles
	 */
	public User(String username, String password, String matriculation, String email, String area, Boolean active, List<Role> roles) {
		this.username = username;
		this.matriculation = matriculation;
		this.email = email;
		this.area = area;
		this.active = active;
		this.roles = roles;
	}
	
	/**
	 * @param username
	 */
	public User(String username) {
		this.username = username;
		this.active=true;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 * @throws Exception 
	 */
	public void setPassword(String password) {
		this.password=password;
	}

	/**
	 * @return the active
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive(){
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the role
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param role the role to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * @return the matriculation
	 */
	public String getMatriculation() {
		return matriculation;
	}

	/**
	 * @param matriculation the matriculation to set
	 */
	public void setMatriculation(String matriculation) {
		this.matriculation = matriculation;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	

	

}
