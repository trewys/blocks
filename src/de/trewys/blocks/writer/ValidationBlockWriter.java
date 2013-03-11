package de.trewys.blocks.writer;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ValidationBlockWriter extends BlockWriter {

	private List<String> path = new ArrayList<String>();
	
	public ValidationBlockWriter(Writer writer) {
		super(writer);
	}

	@Override
	public void writeElementStart(String name) {
		super.writeElementStart(name);
		
		path.add(name);
	}

	@Override
	public void writeElementEnd(String name) {
		String current = path.get(path.size() - 1);
		if (current.equals(name)) {
			super.writeElementEnd(name);
			path.remove(path.size() - 1);
		} else
			throw new RuntimeException("Html is not valid");
	}

	
	@Override
	public void writeElementEndEmpty() {
		super.writeElementEndEmpty();
		path.remove(path.size() - 1);
	}

	@Override
	public void close() {
		super.close();
		
		if (path.size() > 0)
			throw new RuntimeException("Html is not valid (there are unclosed tags)");
	}
	
	
}
