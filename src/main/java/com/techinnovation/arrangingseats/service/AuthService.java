package com.techinnovation.arrangingseats.service;

import java.io.IOException;

import com.techinnovation.arrangingseats.payload.request.AuthenticationRequest;
import com.techinnovation.arrangingseats.payload.request.RegisterRequest;
import com.techinnovation.arrangingseats.payload.response.AuthenticationResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

  public AuthenticationResponse register(RegisterRequest request);

  public AuthenticationResponse authenticate(AuthenticationRequest request);

  //public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
  
}
