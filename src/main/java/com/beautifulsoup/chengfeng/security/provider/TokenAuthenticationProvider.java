package com.beautifulsoup.chengfeng.security.provider;


import java.util.Date;

import com.beautifulsoup.chengfeng.exception.TokenException;
import com.beautifulsoup.chengfeng.security.UserToken;
import com.beautifulsoup.chengfeng.security.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Slf4j
public class TokenAuthenticationProvider implements AuthenticationProvider{
	
	private UserInfoService userService;
	
	public TokenAuthenticationProvider(UserInfoService userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		DecodedJWT jwt = ((UserToken)authentication).getToken();



		boolean expire=jwt.getExpiresAt().before(new Date());

		if(expire)
			throw new TokenException("Token 已经失效");

		String username = jwt.getSubject();

		UserDetails user = userService.getUserLoginInfo(username);

		if(user == null || user.getPassword()==null)
			throw new TokenException("Token 已经失效");
		String encryptSalt = user.getPassword();
		try {
            Algorithm algorithm = Algorithm.HMAC256(encryptSalt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject(username)
                    .build();
            verifier.verify(jwt.getToken());
        } catch (Exception e) {
            throw new BadCredentialsException("Token 校验错误", e);
        }
		UserToken token = new UserToken(user, jwt, user.getAuthorities());

		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UserToken.class);
	}

}
