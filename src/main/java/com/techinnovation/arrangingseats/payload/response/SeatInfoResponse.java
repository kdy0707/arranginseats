package com.techinnovation.arrangingseats.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatInfoResponse {
  private int id;
  private String seatRow;
  private String seatCol;
  private boolean occupancy;
  private String seatRowLen;
  private String seatColLen;
  private SeatUserInfo seatUserInfo;
}
