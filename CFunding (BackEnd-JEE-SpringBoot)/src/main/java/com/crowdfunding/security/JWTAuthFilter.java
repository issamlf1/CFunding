package com.crowdfunding.security;

import com.crowdfunding.entities.User;
import com.crowdfunding.metier.UserMetier;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public JWTAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User u =null;
        try {
            u= new ObjectMapper().readValue(request.getInputStream(),User.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        u.getEmailUser(),
                        u.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        System.err.println("test id --->"+UserDetailsServiceImpl.idUser);
        String jwt = Jwts.builder().setSubject(userSpring.getUsername()).setExpiration(new Date(System.currentTimeMillis()+864_000_000)).signWith(SignatureAlgorithm.HS256,"crowdfunding").claim("role",userSpring.getAuthorities()).compact();
        response.addHeader("Authorization","Bearer "+jwt+" "+String.valueOf(UserDetailsServiceImpl.idUser));


    }
}
