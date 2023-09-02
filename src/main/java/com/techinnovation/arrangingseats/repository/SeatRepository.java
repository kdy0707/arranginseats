package com.techinnovation.arrangingseats.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techinnovation.arrangingseats.model.Seat;
import com.techinnovation.arrangingseats.model.User;

public interface SeatRepository extends JpaRepository<Seat, String> {
    
}
