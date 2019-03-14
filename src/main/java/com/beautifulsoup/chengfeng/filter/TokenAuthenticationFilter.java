package com.beautifulsoup.chengfeng.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beautifulsoup.chengfeng.exception.TokenException;
import com.beautifulsoup.chengfeng.security.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter{
	
	private RequestMatcher requiresAuthenticationRequestMatcher;
	private List<RequestMatcher> permissiveRequestMatchers;
	private AuthenticationManager authenticationManager;
	private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

	public TokenAuthenticationFilter() {
		//拦截header含Authorization的请求
		this.requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher("Authorization");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (!requiresAuthentication(request, response)) {
			filterChain.doFilter(request, response);
			return;
		}
		Authentication authResult = null;
		AuthenticationException failed = null;
		try {
			//获取token交给authenticationManager认证
			String token = getJwtToken(request);
			if(StringUtils.isNotBlank(token)) {
				UserToken authToken = new UserToken(JWT.decode(token));
			    authResult = this.getAuthenticationManager().authenticate(authToken);
			} else {
				failed = new InsufficientAuthenticationException("Token 为空");
			}
		} catch(JWTDecodeException e) {
			log.error("JWT 解析失败", e);
			failed = new InsufficientAuthenticationException("JWT 格式错误", e);
		}catch (InternalAuthenticationServiceException e) {
			log.error("认证用户的过程中出错",e);
			failed = e;
		}catch (AuthenticationException e) {
			failed = e;
		}
		//认证成功的逻辑
		if(authResult != null) {
		    successfulAuthentication(request, response, filterChain, authResult);
		} else if(!permissiveRequest(request)){
			unsuccessfulAuthentication(request, response, failed);
			return;
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(authenticationManager, "authenticationManager must be specified");
		Assert.notNull(successHandler, "AuthenticationSuccessHandler must be specified");
		Assert.notNull(failureHandler, "AuthenticationFailureHandler must be specified");
	}

	//判断header中是否含有指定的头部信息,判断header中是否含有指定的头部信息,否则返回false
	protected boolean requiresAuthentication(HttpServletRequest request,
											 HttpServletResponse response) {
		return requiresAuthenticationRequestMatcher.matches(request);
	}


	//获取token
	protected String getJwtToken(HttpServletRequest request) {
		String authInfo = request.getHeader("Authorization");
		return StringUtils.removeStart(authInfo, "Bearer ");
	}

	//token认证成功的处理逻辑
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain, Authentication authResult) 
			throws IOException, ServletException{
		SecurityContextHolder.getContext().setAuthentication(authResult);
		successHandler.onAuthenticationSuccess(request, response, authResult);
	}

	//token认证失败的处理逻辑
	protected void unsuccessfulAuthentication(HttpServletRequest request,
											  HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request, response, failed);
	}


	protected AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	//判断请求是否在免权限的列表中
	protected boolean permissiveRequest(HttpServletRequest request) {
		if(permissiveRequestMatchers == null)
			return false;
		for(RequestMatcher permissiveMatcher : permissiveRequestMatchers) {
			if(permissiveMatcher.matches(request))
				return true;
		}		
		return false;
	}
	//将指定url添加到免权限列表
	public void setPermissiveUrl(String... urls) {
		if(permissiveRequestMatchers == null)
			permissiveRequestMatchers = new ArrayList<>();
		for(String url : urls)
			permissiveRequestMatchers .add(new AntPathRequestMatcher(url));
	}
	
	public void setAuthenticationSuccessHandler(
			AuthenticationSuccessHandler successHandler) {
		Assert.notNull(successHandler, "successHandler cannot be null");
		this.successHandler = successHandler;
	}

	public void setAuthenticationFailureHandler(
			AuthenticationFailureHandler failureHandler) {
		Assert.notNull(failureHandler, "failureHandler cannot be null");
		this.failureHandler = failureHandler;
	}

}
