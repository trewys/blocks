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
