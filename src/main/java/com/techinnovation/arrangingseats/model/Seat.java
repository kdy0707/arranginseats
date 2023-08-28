package com.techinnovation.arrangingseats.model;



import jakarta.persistence.Column;
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
    @Column(name = "seat_no")
    private int seatNo;
    
    @Column(name = "seat_row")
    private String seatRow;

    @Column(name = "seat_col")
    private String seatCol;

    private String status;

    public Seat() {};
}
