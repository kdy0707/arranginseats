package com.techinnovation.arrangingseats.model;

import jakarta.inject.Named;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "seat")
public class Seat {

    @Id
    @Named("seat_no")
    private int seatNo;
    
    @Named("seat_row")
    private String seatRow;

    @Named("seat_col")
    private String seatCol;

    private String status;

    public Seat() {};
}
