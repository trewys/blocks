package de.trewys.blocks;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import de.trewys.blocks.context.BlockContext;
import de.trewys.blocks.writer.BlockWriter;

public class FileReaderBlock implements Block {

	private String filePath;
		
	private String html;
	
	public FileReaderBlock(String filePath, boolean cache) {
		super();
		this.filePath = filePath;
		
		if (cache) {
			StringWriter writer = new StringWriter();
			
			readFile(new BlockWriter(new StringWriter()));
			
			html = writer.toString();
		}
	}

	private void readFile(BlockWriter writer) {
		
		DataInputStream in = new DataInputStream(this.getClass().getResourceAsStream(filePath));
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				writer.write(strLine);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Block createBlock(BlockContext context) {
		return this;
	}

	public void init(BlockContext context) {}

	public void work(BlockContext context) {}
	
	public void render(BlockWriter writer) {
		if (html != null)
			writer.write(html);
		else readFile(writer);
			
	}

}
