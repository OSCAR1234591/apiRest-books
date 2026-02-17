package org.company.books.backend.controllers;

import org.company.books.backend.Service.JwtService;
import org.company.books.backend.request.AuthResquet;
import org.company.books.backend.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthResquet resquet){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resquet.getUsuario(),resquet.getContrasenia())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(resquet.getUsuario());

        final String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(jwt));
    }

}
