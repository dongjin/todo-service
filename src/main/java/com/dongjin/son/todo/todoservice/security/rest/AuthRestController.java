package com.dongjin.son.todo.todoservice.security.rest;

import com.dongjin.son.todo.todoservice.security.JwtUtil;
import com.dongjin.son.todo.todoservice.security.models.AuthRequest;
import com.dongjin.son.todo.todoservice.security.models.AuthResponse;
import com.dongjin.son.todo.todoservice.security.services.TodoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// With the following, request from restclient / api tester does not work (returns 403)
// Without the following, the request from the angualr web ui does not work (returns 403)
@CrossOrigin(origins = "http://localhost:4200")

//@CrossOrigin
@RestController
public class AuthRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TodoUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect Username or Password!");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        System.out.println("userDetails = " + userDetails);

        final String jwt = jwtUtil.generateToken(userDetails);

        System.out.println("jwt = " + jwt);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
