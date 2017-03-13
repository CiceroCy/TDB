package com.nttdata.tdb.web.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.tdb.services.user.UserService;
import com.nttdata.tdb.web.core.command.TdbCoreCommand;


/**
 * @author Jean.Lorenzini
 *
 */

@Controller
@SessionAttributes({ "command" })
public class TdbCoreController {

	private static String GO_HOME = "pages/home";
	private static String GO_LOGIN = "pages/login";
	private static String GO_REL_INFRA = "pages/report/infra";
	private static String GO_REL_APP = "pages/report/application";
	private static final String COMMAND = "command";

	@Autowired
	private UserService service;

	private static final Logger LOG = Logger.getLogger(TdbCoreController.class.getName());
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/home.htm")
	public ModelAndView initHome(HttpServletRequest request) {
		LOG.log(Level.INFO, "Executing TdbCoreController - initHome method");

		String destiny = GO_HOME;

		
		ModelAndView mav = new ModelAndView(destiny);
     
        LOG.log(Level.INFO, "Finish TdbCoreController - initHome method");
		return mav;
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {
		LOG.log(Level.INFO, "Executing TdbCoreController - login method");
		ModelAndView mav = new ModelAndView(GO_LOGIN);
		return mav;
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		LOG.log(Level.INFO, "Executing TdbCoreController - loginerror method");
		model.addAttribute("error", "true");
		return GO_LOGIN;
	}

	@RequestMapping(value = "/loginlocked", method = RequestMethod.GET)
	public String loginerrorLoginLocked(ModelMap model) {
		LOG.log(Level.INFO, "Executing TdbCoreController - loginerrorLoginLocked method");
		model.addAttribute("loginlocked", "true");
		return GO_LOGIN;
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reportinfra")
	public ModelAndView relInfra(HttpServletRequest request) {
		LOG.log(Level.INFO, "Executing TdbCoreController - relInfra method");
		ModelAndView mav = new ModelAndView(GO_REL_INFRA);
		return mav;
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reportapp")
	public ModelAndView relApp(HttpServletRequest request) {
		LOG.log(Level.INFO, "Executing TdbCoreController - relApp method");
		ModelAndView mav = new ModelAndView(GO_REL_APP);
		return mav;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) {
		LOG.log(Level.INFO, "Executing TdbCoreController - dashboard method");
		ModelAndView mav = new ModelAndView(GO_HOME);
		return mav;
	}
}

class Tester {
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
