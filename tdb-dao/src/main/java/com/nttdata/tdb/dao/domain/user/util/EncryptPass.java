package com.nttdata.tdb.dao.domain.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;

public class EncryptPass {

	private static final Logger LOG = Logger.getLogger(EncryptPass.class.getName());

	public static final String DEFAULT_PASSWORD = "25f9e794323b453885f5181f1b624d0b";
	
	public static String encryptPass(String password) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(Utf8.encode(password));
			password = new String(Hex.encode(digest));
		} catch (NoSuchAlgorithmException e) {
			LOG.log(Level.ERROR, "Erro to encrypty User password, it will be reset to default password: 123456789");
			password = DEFAULT_PASSWORD;
		}
		
		return password;
	}
}
