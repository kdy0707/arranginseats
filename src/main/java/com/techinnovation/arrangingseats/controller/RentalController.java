package com.techinnovation.arrangingseats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rental")
@RequiredArgsConstructor
public class RentalController {

  @PostMapping("/mySeat")
  public ResponseEntity<?> getMySeat() {
    return null;
  }

  @DeleteMapping("/{number}")
  public ResponseEntity<?> cancleSeat(@PathVariable String number) {
    return null;
  }

  @GetMapping("/searchSeat/{name}")
  public ResponseEntity<?> searchSeat(@PathVariable String name) {
    return null;
  }

}
