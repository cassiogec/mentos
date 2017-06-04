/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor.rest;
import br.com.servidor.rest.CORSHeader;
import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Daniel
 */
public class ResponseCorsFilter implements Filter,ClientResponseFilter, ContainerResponseFilter {

    private static CORSHeader heads[];

    static {
        heads = new CORSHeader[5];
        heads[0] = new CORSHeader("Access-Control-Allow-Origin",      "*");
        heads[1] = new CORSHeader("Access-Control-Allow-Headers",     "CompAutorizado, Authorization, X-Requested-With, Content-Length, Content-Type, Origin, Accept, tokenAuth, *");
        heads[2] = new CORSHeader("Access-Control-Allow-Methods",     "GET, POST, PUT, DELETE, OPTIONS, HEAD, *");
        heads[3] = new CORSHeader("Access-Control-Expose-Headers",    "ExceptionMessage, ExceptionStack, content-type, cache, set-cookie");
        heads[4] = new CORSHeader("Access-Control-Allow-Credentials", "false");
    }

    public static void addHeaders(HttpServletResponse response) {
        for (CORSHeader head : heads) {
            response.addHeader(head.getName(), head.getValue());
        }
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
        for (CORSHeader head : heads) {
            responseContext.getHeaders().add(head.getName(), head.getValue());
        }
    }

    /*
    * Habilitar CORS para funcionar com o Jquery *crosDomain:true
    */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        for (CORSHeader head : heads) {
            responseContext.getHeaders().add(head.getName(), head.getValue());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        for (CORSHeader head : heads) {
            resp.addHeader(head.getName(), head.getValue());
        }

        resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        if (req.getMethod().equals("OPTIONS")) {
            resp.setStatus(200);
            return;
        }
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {
    }
}