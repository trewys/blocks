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
