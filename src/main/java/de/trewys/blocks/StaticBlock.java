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

import de.trewys.blocks.context.BlockContext;
import de.trewys.blocks.writer.BlockWriter;

public class StaticBlock implements Block {

	private String html;
		
	public StaticBlock(String html) {
		super();
		this.html = html;
	}

	public Block createBlock(BlockContext context) {
		return this;
	}

	public void init(BlockContext context) {}

	public void work(BlockContext context) {}
	
	public void render(BlockWriter writer) {
		writer.write(html);
	}


}
