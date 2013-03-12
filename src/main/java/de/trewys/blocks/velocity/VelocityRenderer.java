/**
 * Projekt: blocks
 * 
 * $RCSfile: VelocityRenderer.java,v $
 * 
 * @version $Revision: 1.2 $
 * @author  $Author: og $
 *          trewys GmbH
 *          www.trewys.de
 * 
 * $Log: VelocityRenderer.java,v $
 * Revision 1.2  2012-03-05 08:17:19  og
 * *** empty log message ***
 *
 * Revision 1.1  2012-02-28 16:19:23  og
 * *** empty log message ***
 *
 *                                      
 */
package de.trewys.blocks.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import de.trewys.blocks.Renderer;
import de.trewys.blocks.writer.BlockWriter;

/**
 * @author og
 * 
 */
public class VelocityRenderer implements Renderer {

	public static void render(BlockWriter writer, String templateName, VelocityContext velocityContext) {
		VelocityRenderer renderer = new VelocityRenderer(templateName, velocityContext);
		renderer.render(writer);
	}
	
	private String templateName;
	private VelocityContext velocityContext;

	
	public VelocityRenderer(String templateName, VelocityContext velocityContext) {
		super();
		this.templateName = templateName;
		this.velocityContext = velocityContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.trewys.blocks.Renderer#render(de.trewys.blocks.writer.BlockWriter)
	 */
	public void render(BlockWriter writer) {
		Template template = null;

		try {
			template = Velocity.getTemplate(templateName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		writer.write(""); //ent last opened tag
		
		velocityContext.put("writer", writer);
		template.merge(velocityContext, writer.getInnerWriter());
	}

}
