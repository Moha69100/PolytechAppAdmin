package com.polytech.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class APIKeyFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        String o = request.getHeader("api_key");
        HttpServletResponse response = (HttpServletResponse) res;
        

        if ((o == null && !request.getRequestURI().endsWith("/auth")) || (o != null && !((String) o).equals(request.getSession().getAttribute("api_key")))) {
            
            response.setStatus(401);
            
        }
        
        chain.doFilter(request, response);
            
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
