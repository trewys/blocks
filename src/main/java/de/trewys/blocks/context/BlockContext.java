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
package de.trewys.blocks.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import de.trewys.blocks.Block;
import de.trewys.blocks.BlockConfig;
import de.trewys.blocks.writer.BlockWriter;

public class BlockContext {
	
	private static ThreadLocal<BlockContext> instance = new ThreadLocal<BlockContext>();

    public static BlockContext getCurrentInstance() {
        return instance.get();
    }

    protected static void setCurrentInstance(BlockContext context) {
        instance.set(context);
    }
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    
    private Map<String, String> parameters;
    private boolean useRequestParamters = false;
    
    private Collection<FileItem> upload;
    
    public BlockContext(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
    	this(servletRequest, servletResponse, new HashMap<String, String>());
    	useRequestParamters = true;
    }
    
    private BlockContext(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Map<String, String> parameters) {
    	System.out.println("CTX C");
    	setCurrentInstance(this);
    	this.request = servletRequest;
    	this.response = servletResponse;
    	this.parameters = parameters;
    }
    
    public HttpServletResponse getResponse() {
    	return response;
    }
    
    public HttpServletRequest getRequest() {
    	return request;
    }
    
    /**
     * 
     * @param param
     * @return
     * 
     * @deprecated use getParameter instead
     */
    public String getParameterString(String param) {
    	Object value = getParameter(param);
    	if (value == null)
    		return null;
    	else
    		return value.toString();
    }
    
    public String getParameter(String param) {
    	System.out.print("PG: " + param + " = ");
    	
    	if (parameters.containsKey(param)) {
    		System.out.println(parameters.get(param));
    		return parameters.get(param);
    	}
    	
    	if (useRequestParamters) {
	    	System.out.println(request.getParameter(param));

	    	return request.getParameter(param);
    	}
    	
    	System.out.println("null");
    	return null;
    }

    public Object getFormParameter(String param) {
    	if (request.getParameterMap().containsKey(param)) {
			String[] values = request.getParameterValues(param);
			
			if (values.length == 1)
				return values[0];
			else
				return values;
		} else {
			return null;
		}
    }
    public boolean hasParameter(String param) {

    	return parameters.containsKey(param) ||  (useRequestParamters &&
    			request.getParameterMap().containsKey(param));
    }
    

	public void addParameter(String param, String value) {
		System.out.println("PS: " + param + " = " + value);
    	parameters.put(param, value);
    }

	public Object getRequestAttribute(String code) {
		return request.getAttribute(code);
	}
	
	public Object getSessionAttribute(String name) {
		return getSession().getAttribute(name);
	}
	
	public void addFileUpload(FileItem fileItem) {
		if (upload == null)
			upload = new ArrayList<FileItem>();
		
		upload.add(fileItem);
	}
	
	public boolean hasFileUpload() {
		return upload != null;
	}
	
	public Collection<FileItem> getFileUpload() {
		return upload;
	}
	
	public HttpSession getSession() {
		return request.getSession();
	}
	
	public String getContextPath() {
		return request.getContextPath();
	}
	
	public void redirect(String path) {
		try {
			response.sendRedirect(getContextPath() + path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getRequestPath() {
		String path = request.getServletPath();
		if (request.getPathInfo() != null)
			path = path + request.getPathInfo();
		
		return path;
	}
	
	public boolean isPost() {
		return request.getMethod() == "POST";
	}
	
	public Throwable getCurrentException() {
		return (Throwable) request.getAttribute("javax.servlet.error.exception");
	}
	
	public String getCurrentExceptionUri(){
		return (String) request.getAttribute("javax.servlet.error.request_uri");
		
	}
	
	public String getCookieValue(String name) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (name.equals(cookie.getName()))
	                return cookie.getValue();
	        }
	    }
	    return null;
	}
	
	public void addCookie(String name, String value) {
	    Cookie cookie = new Cookie(name, value);
	    cookie.setPath("/");
	    cookie.setMaxAge(2592000); //= 30 Days
	    response.addCookie(cookie);
	}
	
	public void addCookie(String name, String value, int maxAge) {
	    Cookie cookie = new Cookie(name, value);
	    cookie.setPath("/");
	    cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}

	public void removeCookie(String name) {
	    addCookie(name, null, 0);
	}
	
	public void release() {
    	System.out.println("CTX Release");
    	
    	request = null;
    	response = null;
    	setCurrentInstance(null);
    }
   
   public void handleSubBlock(String path, Map<String, String> parameters, BlockWriter writer) {
	   BlockContext subBlockContext = new BlockContext(request, response, parameters);
	   
	   Block block = BlockConfig.getInstance().getBlock(path).createBlock(subBlockContext);
	   block.init(subBlockContext);
	   block.work(subBlockContext);
	   block.render(writer);
	   subBlockContext.release();
	   	   
	   setCurrentInstance(this);
   }
}
