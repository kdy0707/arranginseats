package com.techinnovation.arrangingseats.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techinnovation.arrangingseats.model.Rental;
import com.techinnovation.arrangingseats.model.User;
import java.util.List;


public interface Rentalrepository extends JpaRepository<Rental, Long>{
  Optional<Rental> findBySeatNo(int seatNo);
  Optional<Rental> findByUserId(String userId);
}
