package com.techinnovation.arrangingseats.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techinnovation.arrangingseats.enums.TokenType;
import com.techinnovation.arrangingseats.exception.ValidateException;
import com.techinnovation.arrangingseats.model.Token;
import com.techinnovation.arrangingseats.model.User;
import com.techinnovation.arrangingseats.payload.request.AuthenticationRequest;
import com.techinnovation.arrangingseats.payload.request.RegisterRequest;
import com.techinnovation.arrangingseats.payload.response.AuthenticationResponse;
import com.techinnovation.arrangingseats.repository.TokenRepository;
import com.techinnovation.arrangingseats.repository.UserRepository;
import com.techinnovation.arrangingseats.security.jwt.JwtService;
import com.techinnovation.arrangingseats.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;

  @Override
  public AuthenticationResponse register(RegisterRequest request) {

    if (userRepository.existsById(request.getMobileNumber())) {
      throw new ValidateException("Error: Id is already taken!");
    }

    User user = User.builder()
        .loginId(request.getMobileNumber())
        .password(passwordEncoder.encode(request.getMobileNumber()))
        .username(request.getUsername())
        .department(request.getDepartment())
        .position(request.getPosition())
        .project(request.getProject())
        .build();

    var savedUser = userRepository.save(user);

    String jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getMobileNumber(), 
        request.getMobileNumber()));

    var user = userRepository.findById(request.getMobileNumber())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    // revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    // var validUserTokens =
    // tokenRepository.findAllValidTokenByUser(user.getLoginId());
    // if (validUserTokens.isEmpty())
    // return;
    // validUserTokens.forEach(token -> {
    // token.setExpired(true);
    // token.setRevoked(true);
    // });
    // tokenRepository.saveAll(validUserTokens);
  }
}
