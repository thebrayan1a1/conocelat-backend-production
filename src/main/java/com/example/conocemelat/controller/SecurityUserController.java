package com.example.conocemelat.controller;

import com.example.conocemelat.exceptions.BadRequestException;
import com.example.conocemelat.jwt.model.AuthenticationResquest;
import com.example.conocemelat.jwt.model.AutheticationResponse;
import com.example.conocemelat.jwt.security.JwtUtil;
import com.example.conocemelat.model.User;
import com.example.conocemelat.service.UserService;
import com.example.conocemelat.service.impl.UserDetailsServiceImpl;
import com.example.conocemelat.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@CrossOrigin
@RestController
public class SecurityUserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;


   @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationResquest authenticationResquest) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationResquest.getEmail(), authenticationResquest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect credentials", e);
        }

        final String jwt = jwtUtil.generateToken(authentication);

        return ResponseEntity.ok(new AutheticationResponse((jwt)));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String hello() {
        return "Felicitaciones pudiste ingresar dentro de nuestra app";
    }


    /*@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationResquest authenticationResquest) throws Exception {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationResquest.getEmail(), authenticationResquest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (authentication == null) {
                return ResponseEntity.badRequest().body("Username not found");
            }

            if (!authenticationResquest.getEmail().equals(userDetailsService.loadUserByUsername(authenticationResquest.getEmail())) || !authenticationResquest.getPassword().equals(userDetailsService.loadUserByUsername(authenticationResquest.getPassword()))) {
                return ResponseEntity.badRequest().body("Incorrect password");
            }
            }catch(BadCredentialsException e){
                throw new Exception("Incorrect credentials", e);
            }

            // Build JWT token
            String token = jwtUtil.generateToken(authentication);

            return ResponseEntity.ok().body(new AutheticationResponse(token));
        }*/


}
