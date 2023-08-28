package com.techinnovation.arrangingseats.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatUserInfo {
  private String sName;
  private String sPosition;
  private String sPart;
  private String sPhoneNumber;
}
