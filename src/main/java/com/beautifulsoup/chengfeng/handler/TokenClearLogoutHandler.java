package com.beautifulsoup.chengfeng.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.security.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.io.IOException;


public class TokenClearLogoutHandler implements LogoutHandler {
	
	private UserInfoService userInfoService;
	
	public TokenClearLogoutHandler(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		clearToken(authentication);

		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			ResponseResult responseResult=ResponseResult.createByError(HttpStatus.OK.value(),"用户登出成功");

			ObjectMapper mapper=new ObjectMapper();
			mapper.writeValue(response.getWriter(),responseResult);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	protected void clearToken(Authentication authentication) {
		if(authentication == null)
			return;
		SecurityContextHolder.getContext().setAuthentication(null);
		UserDetails user = (UserDetails)authentication.getPrincipal();
		if(user!=null && user.getUsername()!=null)
		    userInfoService.deleteUserLoginInfo(user.getUsername());

	}

}
