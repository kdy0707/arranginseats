package com.techinnovation.arrangingseats.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techinnovation.arrangingseats.payload.request.AuthenticationRequest;
import com.techinnovation.arrangingseats.payload.request.RegisterRequest;
import com.techinnovation.arrangingseats.payload.response.AuthenticationResponse;
import com.techinnovation.arrangingseats.service.AuthService;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
    AuthenticationResponse authResponse = authService.authenticate(authenticationRequest);

    return ResponseEntity.ok(authResponse);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
    AuthenticationResponse authResponse = authService.register(registerRequest);

    return ResponseEntity.ok(authResponse);
  }

}
