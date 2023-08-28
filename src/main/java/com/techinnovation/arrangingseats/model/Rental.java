package com.techinnovation.arrangingseats.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "rental")
public class Rental {
    @Id
    @Column(name = "rental_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalNo;

    @NotNull
    @Column(name = "user_id")
    @Size(max = 11)
    private String userId;

    @NotNull
    @Column(name = "seat_no")
    private int seatNo;

    @NotNull
    @Column(name = "start_Date")
    private Date startDate;

    @NotNull
    @Column(name = "end_Date")
    private Date endDate;

    public Rental() {};
}
