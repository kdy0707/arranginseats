package com.techinnovation.arrangingseats.service;

import java.util.List;

import com.techinnovation.arrangingseats.payload.response.SeatDeleteResponse;
import com.techinnovation.arrangingseats.payload.response.SeatInfoResponse;
import com.techinnovation.arrangingseats.payload.response.SingleSeatInfoResponse;

public interface SeatService {

  public List<SeatInfoResponse> seats() throws Exception;

  public SingleSeatInfoResponse getSeatByNumber(String seatNumber) throws Exception;

  public SeatInfoResponse getMySeat(String userId) throws Exception;

  public SeatDeleteResponse deleteSeat(String seatNumber, String loginId) throws Exception;

  public List<SeatInfoResponse> searchSeatbyName(String name) throws Exception;
  
}
