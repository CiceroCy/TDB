package com.nttdata.tdb.web.core.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jean.lorenzini
 *
 */
public class MessagesResult {

	public static String ERROR = "alert-error";
	public static String SUCCESS = "alert-success";
	public static String WARNING = "warning";
	public static String INFO = "info";

	private Map<String, String> messages;

	/**
	 * @param map
	 */
	public void addMessages(Map<String, String> map) {
		this.getMessages().putAll(map);
	}

	/**
	 * @param key
	 * @param code
	 */
	public void addMessage(String key, String code) {
		this.getMessages().put(key, code);
	}

	/**
	 * @return Map<String, String>
	 */
	public Map<String, String> getMessages() {
		if (messages == null)
			messages = new HashMap<String, String>();;
		return messages;
	}

	public Map<String, String> getShowMessages(){
        if(messages != null){
    	    Map<String, String> msg = new HashMap<String, String>(messages);
            cleanMessages();
            return msg;
        }else{
            return this.getMessages();
        }
	}

	public void cleanMessages(){
	    this.messages = new HashMap<String, String>();
	}

	/**
	 * @return boolean
	 */
	public boolean hasMessages(){
		return !this.getMessages().isEmpty();
	}
}
