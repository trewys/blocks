/**
 * Projekt: blocks
 * 
 * $RCSfile: BlockVelocityContext.java,v $
 * 
 * @version $Revision: 1.1 $
 * @author  $Author: og $
 *          trewys GmbH
 *          www.trewys.de
 * 
 * $Log: BlockVelocityContext.java,v $
 * Revision 1.1  2012-03-05 08:17:19  og
 * *** empty log message ***
 *
 *                                      
 */
package de.trewys.blocks.velocity;

import org.apache.velocity.VelocityContext;

import de.trewys.blocks.context.BlockContext;

/**
 * @author og
 *
 */
public class BlockVelocityContext extends VelocityContext {

	public static BlockVelocityContext createInstance(BlockContext context) {
		BlockVelocityContext velocityContext = new BlockVelocityContext();
		velocityContext.put("context", context);
		velocityContext.put("util", VelocityUtil.getInstance());
		return velocityContext;
	}
	
	protected BlockVelocityContext() {}
}
