package com.nttdata.tdb.web.core.command;

/**
 * @author jean.lorenzini
 *
 */
public class AuthUserCommand {

    public static String username;
    public static String crm;
    
    /**
     * @return the username
     */
    public static String getUsername() {
        return username;
    }
    
    /**
     * @param username the username to set
     */
    public static void setUsername(String username) {
        AuthUserCommand.username = username;
    }
    
    /**
     * @return the crm
     */
    public static String getCrm() {
        return crm;
    }
    
    /**
     * @param crm the crm to set
     */
    public static void setCrm(String crm) {
        AuthUserCommand.crm = crm;
    }

}
