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
