/**
 * Projekt: blocks
 * 
 * $RCSfile: VelocityInit.java,v $
 * 
 * @version $Revision: 1.1 $
 * @author  $Author: og $
 *          trewys GmbH
 *          www.trewys.de
 * 
 * $Log: VelocityInit.java,v $
 * Revision 1.1  2012-02-28 16:19:23  og
 * *** empty log message ***
 *
 *                                      
 */
package de.trewys.blocks.velocity;

import org.apache.velocity.app.Velocity;

/**
 * @author og
 *
 */
public class VelocityInit {

	public static void initVelocity() {

		Velocity.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM, VelocityInit.class);

		Velocity.init();
	}
}
