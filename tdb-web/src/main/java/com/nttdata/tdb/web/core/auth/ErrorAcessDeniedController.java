package com.nttdata.tdb.web.core.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jean.lorenzini
 * @since 29/01/2014
 */
@Controller
public class ErrorAcessDeniedController {

	private static String GO_PAGE_403 = "fragment/403";

	/**
	 * Method responsible for Error Acess Denied the page command
	 *
	 * @param command
	 * @param result
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/init-acess-denied.htm")
	public ModelAndView acessDeniedController() {

		ModelAndView mav = new ModelAndView(GO_PAGE_403);

		return mav;
	}
}
