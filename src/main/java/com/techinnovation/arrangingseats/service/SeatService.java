package com.techinnovation.arrangingseats.service;

import java.util.List;

import com.techinnovation.arrangingseats.payload.response.SeatInfoResponse;

public interface SeatService {

  public List<SeatInfoResponse> seats() throws Exception;

  public SeatInfoResponse getSeatByNumber(String seatNumber) throws Exception;

  public void delteSeat(String rentalNo);

  public List<SeatInfoResponse> searchSeatbyName(String name);
  
}
