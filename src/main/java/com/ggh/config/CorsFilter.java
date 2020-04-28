package com.ggh.config;

import com.ggh.common.json.Body;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域过滤器
 */
@Component
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String origin = request.getHeader("Origin");    // 获得客户端domain
        if(origin == null) {
            origin = request.getHeader("Referer");
        }
        response.setHeader("Access-Control-Allow-Origin", origin);            // 允许指定域访问跨域资源
        response.setHeader("Access-Control-Allow-Credentials", "true");       // 允许客户端携带跨域cookie，此时origin值不能为“*”，只能为指定单一域名
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        
        // 如果是预检请求，直接返回 
		if(request.getMethod().equals("OPTIONS")) {	
			System.out.println("=======================浏览器发来了OPTIONS预检请求==========");
			response.getWriter().print(Body.newInstance());
			return;	
		}

        //System.out.println("*********************************过滤器被使用**************************2233");
        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}


}
