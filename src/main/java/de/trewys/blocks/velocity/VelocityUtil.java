/**
 * Projekt: blocks
 * 
 * $RCSfile: VelocityUtil.java,v $
 * 
 * @version $Revision: 1.1 $
 * @author  $Author: og $
 *          trewys GmbH
 *          www.trewys.de
 * 
 * $Log: VelocityUtil.java,v $
 * Revision 1.1  2012-03-05 08:17:19  og
 * *** empty log message ***
 *
 *                                      
 */
package de.trewys.blocks.velocity;

import java.io.StringWriter;

/**
 * @author og
 *
 */
public class VelocityUtil {

	private static VelocityUtil instance = new VelocityUtil();
	
	public static VelocityUtil getInstance() {
		return instance;
	}
	
	public String escape(String value, boolean escapeNewlines) {
		StringWriter writer = new StringWriter();
		char next;
		for (int i = 0; i < value.length(); i++) {
			next = value.charAt(i);
			switch (next) {
			case '<':
				writer.write("&lt;");
				break;
			case '>':
				writer.write("&gt;");
				break;
			case '"':
				writer.write("&quot;");
				break;
			case '&':
				writer.write("&amp;");
				break;
			case '\n':
				if (escapeNewlines) {
					writer.write("<br/>");
					break;
				}
			default:
				writer.write(next);
			}

		}
		return writer.toString();
	}
}
