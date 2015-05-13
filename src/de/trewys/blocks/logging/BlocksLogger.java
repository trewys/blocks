package de.trewys.blocks.logging;

import de.trewys.blocks.Block;
import de.trewys.blocks.context.BlockContext;

public interface BlocksLogger {

	void onContextCreated(BlockContext context);

	void onRequest(BlockContext context, String blockPath, Block block);
	
	void onParameterGet(BlockContext blockContext, String param, String value);

	void onBlockMissing(String blockPath);
	
	void onContextReleased(BlockContext blockContext);



}
