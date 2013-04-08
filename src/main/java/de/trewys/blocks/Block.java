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

/**
 * A Block is an part of an HTML Page which can be loaded seperatly.
 *  
 * @author og
 */
public interface Block extends Renderer {

	/**
	 * Creates a new block instance. This instance will be discarded after the
	 * request.
	 * 
	 * @param context The current BlockContext.
	 * 
	 * @return Block instance
	 */
	public Block createBlock(BlockContext context);
	
	/**
	 * Do initializing work like loading data from db here.
	 * 
	 * @param context The current context.
	 */
	public void init(BlockContext context);
	
	/**
	 * Do stuff like saving data to db here.
	 * 
	 * @param context The current context.
	 */
	public void work(BlockContext context);
	
	/**
	 * Render the block. 
	 * 
	 * @param writer The writer to render the block.
	 */
	public void render(BlockWriter writer);
}
