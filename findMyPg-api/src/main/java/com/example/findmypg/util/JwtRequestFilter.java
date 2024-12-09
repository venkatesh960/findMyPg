//package com.example.findmypg.util;
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.example.findmypg.owner.OwnerRegController;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//    }
//    
//    @Autowired
//    private OwnerRegController ownerRegController;
//
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
////            throws ServletException, IOException {
////        
////        final String authorizationHeader = request.getHeader("Authorization");
////
////        String username = null;
////        String jwt = null;
////
////        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
////            jwt = authorizationHeader.substring(7);
////            username = jwtUtil.extractUsername(jwt);
////        }
////
////        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            @SuppressWarnings("unused")
////			UserDetails userByUsername = ownerRegController.loadUserByUsername(username);
////            // Add your JWT validation logic here
////        }
////
////        chain.doFilter(request, response);
////    }
//
//	@Override
//	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
//			throws jakarta.servlet.ServletException, IOException {
//		final String authorizationHeader = request.getHeader("Authorization");
//
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            username = jwtUtil.extractUsername(jwt);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            @SuppressWarnings("unused")
//			UserDetails userByUsername = ownerRegController.loadUserByUsername(username);
//            // Add your JWT validation logic here
//        }
//
//        chain.doFilter(request, response);
//
//		
//	}
//}
