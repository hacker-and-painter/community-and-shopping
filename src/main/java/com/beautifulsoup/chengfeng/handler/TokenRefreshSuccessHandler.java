package com.beautifulsoup.chengfeng.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.TokenVo;
import com.beautifulsoup.chengfeng.security.UserToken;
import com.beautifulsoup.chengfeng.security.UserInfoService;
import com.beautifulsoup.chengfeng.utils.TokenConferUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenRefreshSuccessHandler implements AuthenticationSuccessHandler{
	
	private static final int tokenRefreshInterval = 20;  //刷新间隔5分钟
	
	private UserInfoService userInfoService;
	
	public TokenRefreshSuccessHandler(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		DecodedJWT jwt = ((UserToken)authentication).getToken();
		boolean shouldRefresh = shouldTokenRefresh(jwt.getIssuedAt());
		if(shouldRefresh) {
            String newToken = userInfoService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
        }
	}
	
	protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }

}
