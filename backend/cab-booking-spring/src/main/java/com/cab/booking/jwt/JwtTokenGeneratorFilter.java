package com.cab.booking.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {

            SecretKey key = Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());

            String jwt = Jwts
                    .builder()
                    .setIssuer("Nikhil Sharma")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 86400000))
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .claim("username", authentication.getName())
                    .signWith(key)
                    .compact();

            response.setHeader(JwtSecurityContext.JWT_HEADER, jwt);
        }
        filterChain.doFilter(request, response);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : collection) {

            authorities.add(authority.getAuthority());
        }
        return String.join("", authorities);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return !request.getServletPath().equals("/signin");
    }
}
