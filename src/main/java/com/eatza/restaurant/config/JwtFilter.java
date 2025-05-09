package com.eatza.restaurant.config;


import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import com.eatza.restaurant.dto.ErrorResponseDto;
import com.eatza.restaurant.exception.InvalidTokenException;
import com.eatza.restaurant.exception.UnauthorizedException;
import com.eatza.restaurant.util.ErrorCodesEnum;
import com.eatza.restaurant.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilterBean {
	
	JwtTokenUtil tokenUtil;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException
	{

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");
		
		try {
			
			if (authHeader == null || !authHeader.startsWith("Bearer ")) 
				throw new InvalidTokenException("Missing or invalid Authorization header");
			
			final String token = authHeader.substring(7);
			
			if(!tokenUtil.validateToken(token))
				throw new UnauthorizedException("Invalid token");
			
		} catch (InvalidTokenException iex) {
			filterErrorHandler(iex, response);
			return;
		} catch (UnauthorizedException uex) {
			filterErrorHandler(uex, response);
			return;
		} catch (Exception ex) {
			filterErrorHandler(ex, response);
			return;
		}

		chain.doFilter(req, res);
	}
	
	public void filterErrorHandler(InvalidTokenException exception, HttpServletResponse response ) {
		log.debug("Handling InvalidTokenException");
		ErrorResponseDto errorResponseDto = 
				new ErrorResponseDto(exception.getError().getCode(), 
							 exception.getError().getMsg(),
							 exception.getMessage());
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); 
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		try {
			response.getWriter().write(objectMapper.writeValueAsString(errorResponseDto));
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Unable to write error object to the response");
		} 		
	}
	
	public void filterErrorHandler(UnauthorizedException exception, HttpServletResponse response ) {
		log.debug("Handling UnauthorizedException");
		ErrorResponseDto errorResponseDto = 
				new ErrorResponseDto(exception.getError().getCode(), 
							 exception.getError().getMsg(),
							 exception.getMessage());
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); 
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		try {
			response.getWriter().write(objectMapper.writeValueAsString(errorResponseDto));
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Unable to write error object to the response");
		} 		
	}
	
	public void filterErrorHandler(Exception exception, HttpServletResponse response ) {
		log.debug("Handling Default Exception");
		ErrorResponseDto errorResponseDto = 
				new ErrorResponseDto(ErrorCodesEnum.INTERNAL_SERVER_ERROR.getCode(), 
						ErrorCodesEnum.INTERNAL_SERVER_ERROR.getMsg(),
							 exception.getMessage());
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); 
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		try {
			response.getWriter().write(objectMapper.writeValueAsString(errorResponseDto));
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Unable to write error object to the response");
		} 		
	}
}
