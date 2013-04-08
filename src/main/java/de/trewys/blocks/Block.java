/**
 * Projekt trewys Blocks
 * 
 * $RCSfile: Block.java,v $
 * 
 * @version $Revision: 1.2 $
 * @author  $Author: og $
 *          trewys GmbH
 *          www.trewys.de
 * 
 * $Log: Block.java,v $
 * Revision 1.2  2011-03-03 15:03:39  og
 * - Comments
 *
 * Revision 1.1  2010-11-02 15:14:27  tws
 * *** empty log message ***
 *
 *
 *                                      
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
