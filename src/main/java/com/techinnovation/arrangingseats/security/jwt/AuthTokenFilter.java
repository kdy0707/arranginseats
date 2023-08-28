package com.techinnovation.arrangingseats.security.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.techinnovation.arrangingseats.repository.TokenRepository;
import com.techinnovation.arrangingseats.service.UserService;


@Service
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserService userService;
  private final TokenRepository tokenRepository;

  @Override
  protected void doFilterInternal(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain filterChain
  ) throws ServletException, IOException {
      if (request.getServletPath().contains("/api/auth")) {
          filterChain.doFilter(request, response);
          return;
      }
      if (request.getServletPath().contains("/v3/api-docs") || request.getServletPath().contains("/swagger-ui")) {
          filterChain.doFilter(request, response);
          return;
      }
      final String authHeader = request.getHeader("Authorization");
      final String jwt;
      final String loginId;
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
          filterChain.doFilter(request, response);
          return;
      }
      jwt = authHeader.substring(7);
      loginId = jwtService.extractUsername(jwt);
      if (loginId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = this.userService.loadUserByUsername(loginId);
          var isTokenValid = tokenRepository.findByToken(jwt)
                  .map(t -> !t.isExpired() && !t.isRevoked())
                  .orElse(false);
          if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
              UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                      userDetails,
                      null,
                      userDetails.getAuthorities()
              );
              authToken.setDetails(
                      new WebAuthenticationDetailsSource().buildDetails(request)
              );
              SecurityContextHolder.getContext().setAuthentication(authToken);
          }
      }
      filterChain.doFilter(request, response);
  }

}
