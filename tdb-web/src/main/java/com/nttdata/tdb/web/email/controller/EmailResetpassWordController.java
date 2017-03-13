package com.nttdata.tdb.web.email.controller;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.tdb.dao.domain.user.util.MessagesResult;
import com.nttdata.tdb.domain.user.User;
import com.nttdata.tdb.services.user.UserService;
import com.nttdata.tdb.web.registry.controller.UserController;


@Controller
public class EmailResetpassWordController {
	
	@Value("${msg.error.email.usernotexist}")
	private String userNotExist;

	@Value("${msg.succes.emailsend}")
	private String emailSendsucess;
	
	@Value("${msg.error.emailsend}")
	private String emailSenderror;
	
	@Value("${email.host}")
	private String host;
	
	@Value("${email.port}")
	private int portSmtp;
	
	@Value("${email.username}")
	private String username;
	
	@Value("${email.password}")
	private String password;
	
	@Value("${email.nome}")
	private String nome;
	
	@Value("${email.subject}")
	private String subject;
	
	@Value("${velocity.template}")
	private String templatefile;
	
	@Value("${velocity.template.path}")
	private String path;
	
	@Autowired
	private UserService service;
	

	private VelocityEngine velocityEngine;
	
	
	private static String GO_RESET = "pages/registry/resetsenha";
	private static String GO_HOME = "pages/home";
	private static final String COMMAND_EMAIL = "command";
	private String[] caracteres = { "a", "1", "b", "2", "4", "5", "6", "7", "8",
             "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
             "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
             "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
             "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
             "V", "W", "X", "Y", "Z" };
	
    
	
	
	private static final Logger LOG = Logger.getLogger(UserController.class.getName());
	
	@RequestMapping(value="/reset")
	public ModelAndView resetSenha(HttpServletRequest request){
		
		LOG.log(Level.INFO, "Executing EmailResetpassWordController - resetSenha method");
		 ModelAndView mav=new ModelAndView(GO_RESET);
		 mav.addObject(COMMAND_EMAIL,new User());
		 return mav;
		
	}
	
	@RequestMapping(value="/verifica-user")
	public ModelAndView searchUser(HttpServletRequest request,@ModelAttribute("command") User result){
		 LOG.log(Level.INFO, "Executing EmailResetpassWordController - searchUser method");
	
		String userMatriculation=result.getId();
		
		User user=service.findUserByMatriculation(userMatriculation);
		 
		 ModelAndView mav;
		 
		if(user != null){
			if((!user.getMatriculation().equals(result.getId()))||(!user.getEmail().equals(result.getEmail()))){
			User usernew=new User();
			usernew.addMessage(usernew.ERROR, userNotExist);
			mav=new ModelAndView(GO_RESET);
			return mav.addObject(COMMAND_EMAIL,usernew);
			}
		} else {
			User usernew=new User();
			usernew.addMessage(usernew.ERROR, userNotExist);
			mav=new ModelAndView(GO_RESET);
			return mav.addObject(COMMAND_EMAIL,usernew);
		
		}
		  //Gera Senha aleatoria.
		  String senha=passWordRandom();
		 
		//Reseta a senha   
		service.updatePasswordReset(user, senha);
		
		  if(!user.hasErroMessages()){
		 //Envia Email
		sendEmailMensageVelocity(user.getEmail(),user.getUsername(),senha);
		
		 mav=new ModelAndView(GO_RESET);
		 User usernew=new User();
		 usernew.addMessage(MessagesResult.SUCCESS,emailSendsucess+": "+user.getEmail());
		 mav.addObject(COMMAND_EMAIL,usernew);
		 LOG.log(Level.INFO, "Finish EmailResetpassWordController - searchUser method");
		 return mav;
		 }else{
			mav=new ModelAndView(GO_RESET);
			user.addMessage(MessagesResult.ERROR, emailSenderror);
		  return mav;
		 }	
		  
	}
	
	
	//Gera password aleatorio
	  private String passWordRandom(){
		int qtdmaximacarater=6;
		StringBuilder newPass=new StringBuilder();
		 for(int i=0;i<qtdmaximacarater;i++){
			 int posicao=(int)(Math.random()*caracteres.length);
			 newPass.append(caracteres[posicao]);
		 }
		
		
		return newPass.toString();
	}
	
	/*private void sendEmailMensage(String destinatario,String name){
		
		String senha=passWordRandom();
		
 		SimpleEmail email=new SimpleEmail();
	try{
		email.setDebug(true); 
		email.setHostName(host); // o servidor SMTP para envio do e-mail 
		email.setSSLOnConnect(true);
		email.setSmtpPort(portSmtp);
		email.setStartTLSEnabled(true);
		email.addTo(destinatario, name); //destinatário
		email.setFrom(username, nome); // remetente 
		email.setSubject(subject); // assunto do e-mail 
		email.setMsg("Sua Senha Foi resetada:"+"\n"+"Sua senha provisoria: "+ senha + "\n" +"Por favor Altere sua senha logo após entrar no sistema"+"\n\n" ); //conteudo do e-mail
		email.setAuthenticator(new DefaultAuthenticator(username,password));
		email.send(); //envia o e-mail
		}catch(EmailException e){
			LOG.log(Level.ERROR, e);
		}
		
		
	}*/
	
	private void sendEmailMensageVelocity(String destinatario,String name,String senha){
      
	 LOG.log(Level.INFO, "Executing EmailResetpassWordController - sendEmailMensageVelocity method");
	 
 		HtmlEmail email=new HtmlEmail();
	try{
		email.setDebug(true); 
		email.setHostName(host); // o servidor SMTP para envio do e-mail 
		email.setSSLOnConnect(true);
		email.setSmtpPort(portSmtp);
		email.setStartTLSEnabled(true);
		email.addTo(destinatario, name); //destinatário
		email.setFrom(username, nome); // remetente 
		email.setSubject(subject); // assunto do e-mail 
		//Montangem do Corpo do Email
		VelocityContext context=new VelocityContext();
		context.put("senha", senha);
		Template template = null;
		StringWriter sw;
		
		try {
			setVelocityProperties();
			template=velocityEngine.getTemplate(templatefile);
			sw=new StringWriter();
			template.merge(context, sw);
			String msg=sw.toString();
			sw.close();
			email.setHtmlMsg(msg);
			email.setAuthenticator(new DefaultAuthenticator(username,password));
			email.send(); //envia o e-mail
			
		} catch (IOException e) {
			LOG.log(Level.ERROR,"ERRO-EmailResetpassWordController - sendEmailMensageVelocity method" ,e);
		
		}
   
	}catch(EmailException e){
		LOG.log(Level.ERROR,"ERRO-EmailResetpassWordController - sendEmailMensageVelocity method" ,e);
	}
	   LOG.log(Level.INFO, "Finishing EmailResetpassWordController - sendEmailMensageVelocity method");
	}

	
	//Seta as propriedades do Velocity
	private VelocityEngine setVelocityProperties() {
		 velocityEngine=new VelocityEngine();
		 velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
		 velocityEngine.setProperty("class.resource.loader.class",ClasspathResourceLoader.class.getName());
		 velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, path);
		 velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
		 velocityEngine.setProperty("input.encoding","UTF-8");
		 velocityEngine.setProperty("output.encoding","UTF-8");
		 velocityEngine.init();
		 
		 return velocityEngine;
	}
	

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	
	
}
