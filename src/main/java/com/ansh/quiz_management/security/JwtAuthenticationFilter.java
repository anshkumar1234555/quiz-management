package com.ansh.quiz_management.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("===== JWT FILTER STARTED =====");

        String header = request.getHeader("Authorization");
        System.out.println("Header = " + header);

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        System.out.println("Token = " + token);

        try {

            String email = jwtService.extractEmail(token);
            String role = jwtService.extractRole(token);

            System.out.println("Email = " + email);
            System.out.println("Role = " + role);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(
                                    new SimpleGrantedAuthority("ROLE_" + role)
                            )
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("===== AUTHORITIES =====");
            System.out.println(
                    SecurityContextHolder.getContext()
                            .getAuthentication()
                            .getAuthorities()
            );
            System.out.println("=======================");

            System.out.println("Authorities = " +
                    SecurityContextHolder.getContext()
                            .getAuthentication()
                            .getAuthorities());

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}