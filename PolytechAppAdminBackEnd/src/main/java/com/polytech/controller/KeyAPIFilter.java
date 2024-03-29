package com.polytech.controller;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class KeyAPIFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        String o = request.getHeader("api_key");
        HttpServletResponse response = (HttpServletResponse) res;

        System.out.println("Plop : " + request.getMethod());

    /*    if (!request.getMethod().equals("OPTIONS")) {

            if ((o == null && !request.getRequestURI().endsWith("/auth")) || (o != null && !((String) o).equals(request.getSession().getAttribute("api_key")))) {
                response.setStatus(401);

            } else {
                chain.doFilter(request, response);
            }

       }/else {*/

            chain.doFilter(request, response);

      // }

    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
