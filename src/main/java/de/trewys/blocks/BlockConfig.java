package de.trewys.blocks;

import java.util.HashMap;
import java.util.Map;

public class BlockConfig {

	private static final BlockConfig instance = new BlockConfig();
	
	public static BlockConfig getInstance() {
		return instance;
	}
	
	private Map<String, Block> blocks = new HashMap<String, Block>();
	
	
	
	private BlockConfig() {}
	
	public void addBlock(String path, Block block) {
		
		if (!path.contains("."))
			path = path + ".block";
		blocks.put(path, block);
	}
	
	public Block getBlock(String path) {
		return blocks.get(path);
	}
}
