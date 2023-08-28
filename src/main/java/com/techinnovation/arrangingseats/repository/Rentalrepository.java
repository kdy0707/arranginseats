package com.techinnovation.arrangingseats.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techinnovation.arrangingseats.model.Rental;
import com.techinnovation.arrangingseats.model.User;

public interface Rentalrepository extends JpaRepository<Rental, String>{
  Optional<Rental> findByseatNo(int seatNo);
}
