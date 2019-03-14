package com.beautifulsoup.chengfeng.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/**",filterName = "chengfengFilter")
public class ChengfengFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Chengfeng Filter init");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        String authorization = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)){
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.getWriter().print("用户必须含有token才能访问");
            httpServletResponse.getWriter().flush();
        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}
