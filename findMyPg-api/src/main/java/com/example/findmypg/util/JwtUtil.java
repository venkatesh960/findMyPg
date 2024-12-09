//package com.example.findmypg.util;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtUtil {
////	openssl rand -base64 32 
//	// command to generate a secret key
//	private String SECRET_KEY = "gD3qx3OpYygGwRC+D5OukL6kDXPp3V1a6TvRvDs1UGA="; // Change this to a secure key
//	private int JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours
//
//	public String generateToken(String username) {
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(claims, username);
//	}
//
//	private String createToken(Map<String, Object> claims, String userName) {
//		return Jwts.builder()
//				.setClaims(claims)
//				.setSubject(userName)
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//				.compact();
//	}
//
//	public boolean validateToken(String token, String username) {
//		final String extractedUsername = extractUsername(token);
//		return (extractedUsername.equals(username) && !isTokenExpired(token));
//	}
//
//	public String extractUsername(String token) {
//		return extractAllClaims(token).getSubject();
//	}
//
//	private Claims extractAllClaims(String token) {
//		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//	}
//
//	private boolean isTokenExpired(String token) {
//		return extractAllClaims(token).getExpiration().before(new Date());
//	}
//}
