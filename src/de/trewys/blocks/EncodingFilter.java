package de.trewys.blocks;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {


	}
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if ((request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		}

		chain.doFilter(request, response);

	}

}