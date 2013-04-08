/*
 * Copyright 2013 trewys GmbH 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
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
