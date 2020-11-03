package com.yimeng.seed.organ.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String origin = request.getHeader("Origin");
		if (request.getMethod().equals("OPTIONS")) {
			if (origin==null || origin.isEmpty()) {
				origin = "*";
			}
			response.setHeader("Access-Control-Allow-Origin", origin);
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Request-From");
			response.setHeader("Access-Control-Max-Age", "3600");
		} else {
			if (origin!=null && !origin.isEmpty()) {
				response.setHeader("Access-Control-Allow-Origin", origin);
				response.setHeader("Access-Control-Allow-Credentials", "true");
			}
			//
			chain.doFilter(req, res);
		}
	}


	@Override
	public void init(FilterConfig filterConfig) {}

	@Override
	public void destroy() {}

}
