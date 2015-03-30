package com.junolabs.usm.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class AuditFilter implements Filter {

    public AuditFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//PrintWriter printWriter = response.getWriter();
		//printWriter.println("Init AuditFilter...");

		// Time and log the subsequent processing
		long start = System.currentTimeMillis();
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		
		//printWriter = response.getWriter();
		//printWriter.println("End AuditFilter... -> Start: " + start + " | End: " + end);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
