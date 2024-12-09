//package com.example.findmypg.util;
//import java.security.SecureRandom;
//import java.util.Base64;
//
//public class SecretKeyGenerator {
//	
//	public String generateSecretKey() {
//		
//		SecureRandom random = new SecureRandom();
//		byte[] key = new byte[32]; 
//		random.nextBytes(key);
//		String secretKey = Base64.getEncoder().encodeToString(key);
//		System.out.println("Scret Key => "+secretKey);
//		return secretKey;
//	}
//   
// 
//}
