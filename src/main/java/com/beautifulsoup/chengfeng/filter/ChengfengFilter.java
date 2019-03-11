package com.beautifulsoup.chengfeng.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/",filterName = "chengfengFilter")
public class ChengfengFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Chengfeng Filter init");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("Chengfeng Filter doFilter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}
