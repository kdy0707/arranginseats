package com.techinnovation.arrangingseats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techinnovation.arrangingseats.service.SeatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

  private final SeatService seatService;

  @GetMapping("/")
  public ResponseEntity<?> list() throws Exception {
    return ResponseEntity.ok(seatService.seats());
  }

  @GetMapping("/{number}")
  public ResponseEntity<?> getSeat(@PathVariable String number) {
    return null;
  }
  
}
