package com.nttdata.tdb.web.registry.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.tdb.dao.domain.user.util.MessagesResult;
import com.nttdata.tdb.domain.user.User;
import com.nttdata.tdb.services.user.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@Value("${msg.notBlank}")
	private String notblack;
	
	@Value("${msg.success.registry}")
	private String sucessRegistry;
	
	@Value("${msg.error.email.user}")
	private String invalidEmail;

	private static String GO_HOME = "pages/home";
	private static String GO_USER = "pages/registry/user";
	private static String GO_USER_EDIT = "pages/registry/user";
	private static String GO_USER_LIST = "pages/registry/userList";
	private static String GO_CHANGE_PASSWORD = "pages/registry/newPasswordUser";
	private static final String COMMAND_USER = "command";
	private static final String COMMAND_USER_LIST = "userList";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern;
	private Matcher matcher;

	private static final Logger LOG = Logger.getLogger(UserController.class.getName());

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userlist")
	public ModelAndView user(HttpServletRequest request) {
		LOG.log(Level.INFO, "Executing TdbCoreController - userList method");

		List<User> lista = new ArrayList<User>();
		lista = service.listUser();

		ModelAndView mav = new ModelAndView(GO_USER_LIST);
		mav.addObject(COMMAND_USER, new User());
		mav.addObject(COMMAND_USER_LIST, lista);
		return mav;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/usernew")
	public ModelAndView cadastroUsuario(HttpServletRequest request) {
		LOG.log(Level.INFO, "Executing TdbCoreController - UserNew method");
		ModelAndView mav = new ModelAndView(GO_USER);
		mav.addObject(COMMAND_USER, new User());
		return mav;
	}

	/**
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/save-user.htm")
	public ModelAndView saveUser(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user,BindingResult result) {

		LOG.log(Level.INFO, "Executing UserController - saveUser method");
		
          /* Valida E-mail*/
		if(!(user.getEmail()!=null && user.getEmail().isEmpty())){
			pattern=Pattern.compile(EMAIL_PATTERN);
			matcher=pattern.matcher(user.getEmail());
			if(!matcher.matches()){
				user.addMessage(User.ERROR,invalidEmail);
				return new ModelAndView(GO_USER).addObject(COMMAND_USER, user);
			}
		}
		
		/* Valida Mensagens com erros campos em branco*/
		if(result.hasErrors()){
			user.addMessage(MessagesResult.ERROR, notblack);
			return  new ModelAndView(GO_USER).addObject(COMMAND_USER, user);
		}else{
			// Chama o metodo save/update
			service.saveUser(user);
		}
		
		// Lista os usuarios apos a chamada do metodo save/update
		List<User> lista = new ArrayList<User>();
		lista = service.listUser();

		ModelAndView mav;
		
		/* Valida Mensagens com erros, exceptions Save/Update/Generic */
		if(user.hasErroMessages()){
			mav = new ModelAndView(GO_USER);
			mav.addObject(COMMAND_USER,user);
		}else{
			mav = new ModelAndView(GO_USER_LIST);
			user = new User();
			user.addMessage(User.SUCCESS, sucessRegistry);
			mav.addObject(COMMAND_USER,user);
		    mav.addObject(COMMAND_USER_LIST, lista);
		    LOG.log(Level.INFO,	"Finish UserController - Recarregando List of users");
		}
		
		LOG.log(Level.INFO, "Finish UserController - saveUser method");
		
		return mav;

	}

	/**
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/search-user.htm")
	public ModelAndView searchUser(HttpServletRequest request,
			@ModelAttribute("command") User result) {

		LOG.log(Level.INFO, "Executing UserController - SearchUser method");

		List<User> userList = service.findUserByUsernameOrMatriculation(result.getUsername(), result.getMatriculation());

		ModelAndView mav = new ModelAndView(GO_USER_LIST);
		mav.addObject(COMMAND_USER_LIST, userList);

		LOG.log(Level.INFO, "Finish UserController - SearchUser method");

		return mav;

	}

	@RequestMapping(value = "/edit-user.htm", params = { "id" })
	public ModelAndView editUser(HttpServletRequest request,
			@RequestParam("id") String id,
			@ModelAttribute("command") User result) {

		LOG.log(Level.INFO, "Executing UserController - EditUser method");

		User user = service.findUserById(id);

		ModelAndView mav = new ModelAndView(GO_USER_EDIT);
		mav.addObject(COMMAND_USER, user);

		LOG.log(Level.INFO, "Finish UserController - EditUser method");

		return mav;

	}

	@RequestMapping(value = "/newPasswordUser.htm")
	public ModelAndView changePassowrd(HttpServletRequest request) {
		LOG.log(Level.INFO, "Executing UserController - ChangePassowrd method");

		ModelAndView mav = new ModelAndView(GO_CHANGE_PASSWORD);
		mav.addObject(COMMAND_USER, new User());

		LOG.log(Level.INFO, "Finish UserController -ChangePassowrd  method");
		return mav;

	}

	@RequestMapping(value = "/save-newpass-user.htm",params={"id"})
	public ModelAndView saveNewpass(HttpServletRequest request, @RequestParam("id") String id,User user) {
		LOG.log(Level.INFO, "Executing UserController - saveNewpass method");

		// Chama o metodo save/update
		service.updatePassword(user);
		ModelAndView mav ;
		if(user.hasErroMessages()){
			mav=new ModelAndView(GO_CHANGE_PASSWORD);
			mav.addObject(COMMAND_USER, user);
		}else{ 
			
		user.addMessage(User.SUCCESS, sucessRegistry);
		mav = new ModelAndView(GO_HOME);
		mav.addObject(COMMAND_USER,user);
		LOG.log(Level.INFO, "Finish UserController -saveNewpass  method");
		}
		return mav;

	}

}
