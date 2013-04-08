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
package de.trewys.blocks.writer;

import java.io.IOException;
import java.io.Writer;

public class BlockWriter {

	private Writer writer;

	private boolean tagOpen = false;

	public BlockWriter(Writer writer) {
		super();
		this.writer = writer;
	}

	public void write(String html) {
		try {
			closeOpenTag();

			writer.write(html);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void writeElementStart(String name) {
		try {
			closeOpenTag();

			writer.write('<');
			writer.write(name);

			tagOpen = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void writeElementEnd(String name) {
		try {

			closeOpenTag();

			writer.write("</");
			writer.write(name);
			writer.write(">");

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void writeElementEndEmpty() {
		try {
			if (tagOpen) {
				writer.write("/>");
				tagOpen = false;
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void writeAttribute(String name, Object value) {
		try {

			if (value instanceof Boolean) {
				if (((Boolean) value).booleanValue()) {
					writer.write(' ');
					writer.write(name);
					writer.write("=\"");
					writer.write(name);
					writer.write('"');
				}
			} else {
				writer.write(' ');
				writer.write(name);
				writer.write("=\"");
				writeEscaped((value == null) ? "" : value.toString(), false);
				writer.write('"');
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void writeText(String text) {
		writeText(text, false);
	}

	public void writeText(String text, boolean escapeNewlines) {
		try {
			closeOpenTag();

			if (text != null)
				writeEscaped(text, escapeNewlines);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeEscaped(String text, boolean escapeNewlines)
			throws IOException {
		char next;
		for (int i = 0; i < text.length(); i++) {
			next = text.charAt(i);
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
	}

	private void closeOpenTag() throws IOException {
		if (tagOpen) {
			writer.write('>');

			tagOpen = false;
		}
	}
	
	public void close() {
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * @return
	 */
	public Writer getInnerWriter() {
		return writer;
	}
}
