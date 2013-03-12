package de.trewys.blocks.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {

	private static HashingUtil instance = new HashingUtil();
	
	public static HashingUtil getInstance() {
		return instance;
	}

	public String getPasswordHash(String password) {


		byte[] defaultBytes = password.getBytes();

		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String s = Integer.toHexString(0xFF & messageDigest[i]);
				
				if (s.length() == 1)
					hexString.append('0');
					
				hexString.append(s);
			}
			
			return hexString + "";
		} catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException(nsae);
		}
	
	}
	
	
}
