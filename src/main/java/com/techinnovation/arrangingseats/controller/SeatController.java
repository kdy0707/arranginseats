package com.techinnovation.arrangingseats.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techinnovation.arrangingseats.security.jwt.AuthEntryPointJwt;
import com.techinnovation.arrangingseats.security.jwt.JwtService;
import com.techinnovation.arrangingseats.service.SeatService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

  private static final Logger logger = LoggerFactory.getLogger(SeatController.class);

  private final SeatService seatService;
  private final JwtService jwtService;

  @GetMapping("/")
  public ResponseEntity<?> list() throws Exception {
    return ResponseEntity.ok(seatService.seats());
  }

  @GetMapping("/{number}")
  public ResponseEntity<?> getSeat(@PathVariable("number") String number) throws Exception {
    return ResponseEntity.ok(seatService.getSeatByNumber(number));
  }

  @DeleteMapping("/{number}")
  public ResponseEntity<?> cancleSeat(@PathVariable("number") String number, HttpServletRequest request) throws Exception {
    String authHeader = request.getHeader("Authorization");
    String jwt = authHeader.substring(7);
    String loginId = jwtService.extractUsername(jwt);

    logger.info("loginId ======" + loginId);
    return ResponseEntity.ok(seatService.deleteSeat(number, loginId));
  }

  @GetMapping("/searchSeat/{name}")
  public ResponseEntity<?> searchSeat(@PathVariable("name") String name) throws Exception {
    return ResponseEntity.ok(seatService.searchSeatbyName(name));
  }

  @GetMapping("/mySeat")
  public ResponseEntity<?> getMySeat(HttpServletRequest request) throws Exception {
    String authHeader = request.getHeader("Authorization");
    String jwt = authHeader.substring(7);
    String loginId = jwtService.extractUsername(jwt);

    logger.info("loginId ======" + loginId);
    return ResponseEntity.ok(seatService.getMySeat(loginId));
  }
}
