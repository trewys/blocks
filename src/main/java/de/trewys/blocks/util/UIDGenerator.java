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


public class UIDGenerator {

	private static UIDGenerator instance = new UIDGenerator();
	
	public static UIDGenerator getInstance() {
		return instance;
	}
	
	public String createNewId() {
		byte[] b = new byte[10];
		
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (48 + (Math.random() * 36));
			if (b[i] > 57)
				b[i] = (byte) (b[i] + 39);
		}
		
		String newId = new String(b);

		return newId;
	}
	
}
