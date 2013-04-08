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
package de.trewys.blocks;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;

import javax.servlet.http.HttpServletResponse;

import de.trewys.blocks.context.BlockContext;
import de.trewys.blocks.util.FileType;
import de.trewys.blocks.util.IOUtil;
import de.trewys.blocks.writer.BlockWriter;

public class FileForwardBlock implements Block {

	
	private String path;

	private String filePath;
	
	public FileForwardBlock(String path) {
		super();
		this.path = path;
		
	}

	private void readFile(BlockWriter writer) {
		HttpServletResponse response = BlockContext.getCurrentInstance().getResponse();
		DataInputStream input = new DataInputStream(this.getClass().getResourceAsStream(path + "/" + filePath));
		BufferedOutputStream output = null;
		try {
			String suffix = null;
			
			//extrace filetype
			if (filePath.lastIndexOf(".") > -1)
				suffix = filePath.substring(
						filePath.lastIndexOf(".") + 1).toLowerCase();
			
			response.reset();
			response.setContentType(FileType.getInstance(suffix).getMimeType());
//			response.setContentLength(input.available());
			response.setHeader("Content-disposition", "inline; filename=\"" + filePath + "\"");
			
			output = new BufferedOutputStream(response.getOutputStream());
			
			//Thread.currentThread().sleep(10000);
			
			// Write file contents to response.
			for (int data; (data = input.read()) != -1;) {
				output.write(data);
			}

			// Finalize task.
			output.flush();
			
			response.flushBuffer();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtil.close(input);
			IOUtil.close(output);
		}
	}

	public Block createBlock(BlockContext context) {
		return this;
	}

	public void init(BlockContext context) {}

	public void work(BlockContext context) {
		filePath = context.getParameter("path");
		System.out.println(filePath);
		
	}
	
	public void render(BlockWriter writer) {
		readFile(writer);
	}

}
