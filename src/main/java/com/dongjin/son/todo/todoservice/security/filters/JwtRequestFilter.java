package com.dongjin.son.todo.todoservice.security.filters;

import com.dongjin.son.todo.todoservice.security.JwtUtil;
import com.dongjin.son.todo.todoservice.security.services.TodoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Check received request header for Authorization && validate and save in the SecurityContextHolder
 */

//@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private TodoUserDetailsService todoUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String username = null;
        String jwt = null;

        // (1) Check Authorization Header
        final String authorizatinoHeader = request.getHeader("Authorization");

        // (2) if authorization header exists, get jwt token and extract username
        if (authorizatinoHeader != null && authorizatinoHeader.startsWith("Bearer ")) {
            jwt = authorizatinoHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        // (3) validate:

        // IF username is not null  && SecurityContextHolder does not have Authentication yet

        // (Q) why only validate when Authentication does not existing in SecurityContextHolder ?
        //     ==> I.e., how can we sure requeted user is the same ? (as the one who saved a token in securityContextHolder)

        // if valid, create a UsernamepasswordAuthenticationToken and save it (Authentication) in th SecurityContext
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.todoUserDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // (Q) why no authenticate call
                //    e.g.,  this.getAuthenticationManager()
                //         .authenticate(authRequest);

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
