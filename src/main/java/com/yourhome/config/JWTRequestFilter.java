package com.yourhome.config;


import com.yourhome.entity.BookingUser;
import com.yourhome.repository.BookingUserRepository;
import com.yourhome.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private BookingUserRepository bookingUserRepository;


    public JWTRequestFilter(JWTService jwtService, BookingUserRepository bookingUserRepository) {
        this.jwtService = jwtService;
        this.bookingUserRepository = bookingUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken!=null && authorizationToken.startsWith("Bearer ")){
            String substring = authorizationToken.substring(8, authorizationToken.length() - 1);
            String username = jwtService.getUsername(substring);

            Optional<BookingUser> byUsername = bookingUserRepository.findByUsername(username);

            if (byUsername.isPresent()){
                BookingUser bookingUser = byUsername.get();

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(byUsername,null,new ArrayList<>());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }

        filterChain.doFilter(request,response);

    }
}
