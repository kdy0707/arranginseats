package com.techinnovation.arrangingseats.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleSeatInfoResponse {
    private int id;
    private boolean occupancy;
    private SeatUserInfo seatUserInfo;
    
}
