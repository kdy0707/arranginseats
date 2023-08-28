package com.techinnovation.arrangingseats.service;

import com.techinnovation.arrangingseats.payload.response.RentalResponse;

public interface Rentalservice {

  public RentalResponse rent(); 
  public RentalResponse cancle();
  
}
