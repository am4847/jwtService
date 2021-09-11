package com.dong.jwtService.Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if(req.getMethod().equals("POST")) {
			System.out.println("post요청됨");
			String headerAuth = req.getHeader("Authorization");
			System.out.println(headerAuth);
			if(headerAuth.equals("dong")) {
				chain.doFilter(req, resp);
			}else {
				PrintWriter out =  resp.getWriter();
				out.print("인증안됨");
			}
		}
		
	
		
	}
 
}
