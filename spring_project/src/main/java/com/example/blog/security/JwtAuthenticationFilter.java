package com.example.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		// 1. get token
		
		String requestToken = request.getHeader("Authorization");
		
		System.out.println(requestToken);
		
		String username = null;
		
		String token = null;
		
		if(request != null && requestToken.startsWith("Bearer")) {
			
//			System.out.println("coming here");
			
			token = requestToken.substring(6);	
			
			System.out.println(token);
			
			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);
				
				System.out.println("username: "  + username);
				
			} 
			catch(IllegalArgumentException e) {
				System.out.println("Unable to get Jwt Token");
				
			}
			catch(ExpiredJwtException e) {
				System.out.println("Jwt Token expired !!");
			}
			catch(MalformedJwtException e) {
				System.out.println("Invalid Jwt !!");
			}
			
		} 
		else {
			System.out.println("Jwt Token does not start with Bearer");
		}
		
		// once we get token, now validate
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationtoken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationtoken);
				
			} else {
				
				System.out.println("Invalid token !!");
				
			}
			
		} else {
			
			System.out.println("Username is null or context is not null !!");
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
