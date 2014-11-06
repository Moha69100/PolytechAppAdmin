package com.polytech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
public class SimpleCORSFilter implements Filter {
    
//    private List<String> allowedHeaders = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        String o = request.getHeader("api_key");
        HttpServletResponse response = (HttpServletResponse) res;
        

//        if (o == null && !request.getRequestURI().endsWith("/auth")) {
//            
//            response.setStatus(401);
//            
//        } else if (request.getRequestURI().endsWith("/auth") || ((String) o).equals(request.getSession().getAttribute("api_key"))) {

            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, content-type, api_key, Accept");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            chain.doFilter(request, response);

            
            
//        }
        
            
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
