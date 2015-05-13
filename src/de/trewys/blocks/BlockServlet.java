package de.trewys.blocks;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import de.trewys.blocks.context.BlockContext;
import de.trewys.blocks.logging.BlocksLogger;
import de.trewys.blocks.writer.BlockWriter;
import de.trewys.blocks.writer.ValidationBlockWriter;


public class BlockServlet implements Servlet {

	private static BlocksLogger logger;
	
	private ServletConfig servletConfig = null;
   
	private boolean isDebug;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		this.servletConfig = servletConfig;
		
		isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
	   		getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
	    
		BlockConfig.getInstance().addBlock(
				"/blocks/frame/script.block",
				new FileForwardBlock("/html/script"));
	}

	public void service(ServletRequest servletRequest, ServletResponse servletResponse)
			throws ServletException, IOException {
	
		servletRequest.setCharacterEncoding("UTF-8");
		
		//always use utf-8
		servletResponse.setCharacterEncoding("UTF-8");
		servletResponse.setContentType("text/html; charset=UTF-8");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		
		Writer writer = servletResponse.getWriter();
		BlockWriter blockWriter = null;
		if (!isDebug)
			blockWriter = new BlockWriter(writer);
		else
			blockWriter = new ValidationBlockWriter(writer); //in debug mode use a validating writer
		
		String blockPath = getBlockPath(httpServletRequest);
		
		//Get Block
		Block block = BlockConfig.getInstance().getBlock(blockPath);
	
		
		
		if (block != null) {
			// Creating a new context (will be set to 'current context') as well 
			BlockContext context = new BlockContext(
					httpServletRequest,
					(HttpServletResponse) servletResponse,
					logger);
			
			if (logger != null) {
				logger.onRequest(context, blockPath, block);
			}
			try {
				//if there are fileload...
				handleMultipartContent(servletRequest, context);

				//create new instance
				block = block.createBlock(context);
				
				// init -> work -> render
				block.init(context);
				block.work(context);
				block.render(blockWriter);
			} finally {
				context.release();
			}
		} else {
			if (logger != null) {
				logger.onBlockMissing(blockPath);
			}
		}
		//close writer
		blockWriter.close();
	}

	protected String getBlockPath(HttpServletRequest httpServletRequest) {
		//get requested block path
		String blockPath = httpServletRequest.getServletPath();
		if (httpServletRequest.getPathInfo() != null)
			blockPath = blockPath + httpServletRequest.getPathInfo();
		return blockPath;
	}

	@SuppressWarnings("unchecked")
	private void handleMultipartContent(ServletRequest servletRequest,
			BlockContext context) {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		if (ServletFileUpload.isMultipartContent(request)) {
			ServletFileUpload servletFileUpload = new ServletFileUpload(
					new DiskFileItemFactory());
			
			try {
				List<FileItem> fileItemsList = servletFileUpload.parseRequest(request);
				
				for (FileItem fileItem : fileItemsList) {
					
					if (fileItem.isFormField()) {
						context.addParameter(fileItem.getFieldName(), fileItem.getString());
					} else {
						context.addFileUpload(fileItem);
					}
				}
				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
	}

	
	public void destroy() {
		servletConfig = null;
	}

	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	public String getServletInfo() {
		 return (this.getClass().getName());
	}

	public static BlocksLogger getLogger() {
		return logger;
	}

	public static void setLogger(BlocksLogger logger) {
		BlockServlet.logger = logger;
	}

}