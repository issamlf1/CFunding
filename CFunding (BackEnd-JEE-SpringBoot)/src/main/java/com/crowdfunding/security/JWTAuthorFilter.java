package com.crowdfunding.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JWTAuthorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Headers","Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,authorization");
        response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials,authorization");
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            String jwt=request.getHeader("Authorization");
            System.out.println(jwt);
            if (jwt==null || jwt.startsWith("Bearer ")){
                filterChain.doFilter(request,response); return;
           }
            //jwt.split(" ");
            Claims claims = Jwts.parser().setSigningKey("crowdfunding").parseClaimsJws(jwt).getBody();
            String userEmail =claims.getSubject();
            ArrayList<Map<String,String>> role =(ArrayList<Map<String, String>>)  claims.get("role");
            List<GrantedAuthority> authorities = new ArrayList<>();
            role.forEach(r->{
                authorities.add(new SimpleGrantedAuthority(r.get("authority")));
            });
            System.out.println(userEmail);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userEmail,null,authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request,response);
        }
    }
}
