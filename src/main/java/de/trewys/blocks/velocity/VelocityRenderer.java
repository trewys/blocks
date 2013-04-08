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
