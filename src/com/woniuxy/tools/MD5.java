package com.woniuxy.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class MD5 {
	public static String toMD5(String pwd) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bs = md.digest(pwd.getBytes());
		@SuppressWarnings("resource")
		Formatter format = new Formatter();
		for (byte b : bs) {
			format.format("%02x", b);
		}
		return format.toString();
	}
}
