package com.techinnovation.arrangingseats.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.techinnovation.arrangingseats.controller.SeatController;
import com.techinnovation.arrangingseats.model.Rental;
import com.techinnovation.arrangingseats.model.Seat;
import com.techinnovation.arrangingseats.model.User;
import com.techinnovation.arrangingseats.payload.response.SeatDeleteResponse;
import com.techinnovation.arrangingseats.payload.response.SeatInfoResponse;
import com.techinnovation.arrangingseats.payload.response.SeatUserInfo;
import com.techinnovation.arrangingseats.payload.response.SingleSeatInfoResponse;
import com.techinnovation.arrangingseats.repository.Rentalrepository;
import com.techinnovation.arrangingseats.repository.SeatRepository;
import com.techinnovation.arrangingseats.repository.UserRepository;
import com.techinnovation.arrangingseats.service.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

  private static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

  private final SeatRepository seatRepository;
  private final Rentalrepository rentalrepository;
  private final UserRepository userRepository;

  @Override
  public List<SeatInfoResponse> seats() throws Exception {
    List<SeatInfoResponse> response = new ArrayList<>();
    List<Seat> seats = seatRepository.findAll();
    List<Rental> rentals = rentalrepository.findAll();

    for (Seat seat : seats) {
      Optional<Rental> rentalInfo = rentals
          .stream()
          .filter((rental) -> rental.getSeatNo() == seat.getSeatNo())
          .findFirst();

      SeatUserInfo seatUserInfo = new SeatUserInfo();
      if (!rentalInfo.isEmpty()) {
        Optional<User> user = userRepository.findById(rentalInfo.get().getUserId());

        if (user.isPresent()) {
          seatUserInfo.setSName(user.get().getUsername());
          seatUserInfo.setSPart(user.get().getDepartment());
          seatUserInfo.setSPhoneNumber(rentalInfo.get().getUserId());
          seatUserInfo.setSPosition(user.get().getPosition());
        }
      }

      response.add(SeatInfoResponse.builder()
          .id(seat.getSeatNo())
          .seatRow(seat.getSeatRow())
          .seatCol(seat.getSeatCol())
          .occupancy(!"Empty".equals(seat.getStatus()) ? true : false)
          .seatColLen(seat.getSeatCol())
          .seatRowLen(seat.getSeatRow())
          .seatUserInfo(seatUserInfo)
          .build());
    }

    return response;
  }

  @Override
  public SingleSeatInfoResponse getSeatByNumber(String seatNumber) throws Exception {
    SeatUserInfo seatUserInfo = new SeatUserInfo();
    Optional<Seat> seat = seatRepository.findById(seatNumber);

    if (seat.isEmpty()) {
      throw new Exception("좌석이 없습니다.");
    }

    Optional<Rental> rental = rentalrepository.findBySeatNo(seat.get().getSeatNo());

    if (rental.isPresent()) {
      Optional<User> user = userRepository.findById(rental.get().getUserId());
      seatUserInfo.setSName(user.get().getUsername());
      seatUserInfo.setSPart(user.get().getDepartment());
      seatUserInfo.setSPhoneNumber(user.get().getLoginId());
      seatUserInfo.setSPosition(user.get().getPosition());
    }

    return SingleSeatInfoResponse.builder()
        .id(seat.get().getSeatNo())
        .occupancy(!"empty".equals(seat.get().getStatus()) ? true : false)
        .seatUserInfo(seatUserInfo)
        .build();
  }

  @Override
  public SeatDeleteResponse deleteSeat(String seatNumber, String loginId) throws Exception {
    Optional<Seat> seat = seatRepository.findById(seatNumber);
    if (seat.isEmpty()) {
      throw new Exception("좌석이 없습니다.");
    }

    Optional<Rental> rental = rentalrepository.findBySeatNo(seat.get().getSeatNo());
    if (rental.isPresent()) {
      Optional<User> user = userRepository.findById(rental.get().getUserId());

      if (user.isPresent()) {
        String userId = user.get().getLoginId();
        if (userId.equals(loginId)) {
          rentalrepository.deleteById(rental.get().getRentalNo());
          seat.get().setStatus("empty");
          seatRepository.save(seat.get());
        } else {
          throw new Exception("다른 사용자의 좌석을 삭제할 수 없습니다.");
        }
      }
    } else {
      throw new Exception("삭제할 좌석이 없습니다.");
    }

    return SeatDeleteResponse.builder().success("Ok").build();
  }

  @Override
  public List<SeatInfoResponse> searchSeatbyName(String name) throws Exception {
    List<SeatInfoResponse> response = new ArrayList<>();
    SeatUserInfo seatUserInfo = new SeatUserInfo();
    List<User> users = userRepository.findByUsername(name);

    // if(user.isEmpty()) {
    // throw new Exception("사용자가 없습니다.");
    // }

    for (User user : users) {

      logger.info("user ===============" + user.toString());

      Optional<Rental> rental = rentalrepository.findByUserId(user.getLoginId());

      if (rental.isPresent()) {
        Optional<Seat> seat = seatRepository.findById(Integer.toString(rental.get().getSeatNo()));

        if (seat.isPresent()) {
          seatUserInfo.setSName(user.getUsername());
          seatUserInfo.setSPart(user.getDepartment());
          seatUserInfo.setSPhoneNumber(user.getLoginId());
          seatUserInfo.setSPosition(user.getPosition());

          response.add(SeatInfoResponse.builder()
              .id(seat.get().getSeatNo())
              .seatRow(seat.get().getSeatRow())
              .seatCol(seat.get().getSeatCol())
              .occupancy(!"Empty".equals(seat.get().getStatus()) ? true : false)
              .seatColLen(seat.get().getSeatCol())
              .seatRowLen(seat.get().getSeatRow())
              .seatUserInfo(seatUserInfo)
              .build());
        }
      }
    }

    return response;
  }

  @Override
  public SeatInfoResponse getMySeat(String userId) throws Exception {
    SeatUserInfo seatUserInfo = new SeatUserInfo();
    Optional<Rental> rental = rentalrepository.findByUserId(userId);

    if (rental.isEmpty()) {
      throw new Exception("좌석이 없습니다.");
    } else {
      Optional<User> user = userRepository.findById(rental.get().getUserId());
      seatUserInfo.setSName(user.get().getUsername());
      seatUserInfo.setSPart(user.get().getDepartment());
      seatUserInfo.setSPhoneNumber(user.get().getLoginId());
      seatUserInfo.setSPosition(user.get().getPosition());

      Optional<Seat> seat = seatRepository.findById(Integer.toString(rental.get().getSeatNo()));

      return SeatInfoResponse.builder()
          .id(seat.get().getSeatNo())
          .seatRow(seat.get().getSeatRow())
          .seatCol(seat.get().getSeatCol())
          .occupancy(!"Empty".equals(seat.get().getStatus()) ? true : false)
          .seatColLen(seat.get().getSeatCol())
          .seatRowLen(seat.get().getSeatRow())
          .seatUserInfo(seatUserInfo)
          .build();
    }
  }

}
