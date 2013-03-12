package de.trewys.blocks.util;

import java.io.Closeable;
import java.io.IOException;

public class IOUtil {

	public static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
