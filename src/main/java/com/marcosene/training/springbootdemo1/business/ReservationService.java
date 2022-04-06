package com.marcosene.training.springbootdemo1.business;

import com.marcosene.training.springbootdemo1.data.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(date);
        List<RoomReservation> roomReservations = new ArrayList<>();
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setDate(date);
            roomReservation.setRoomId(reservation.getRoom().getId());
            roomReservation.setRoomName(reservation.getRoom().getName());
            roomReservation.setRoomNumber(reservation.getRoom().getRoomNumber());
            roomReservation.setFirstName(reservation.getGuest().getFirstName());
            roomReservation.setLastName(reservation.getGuest().getLastName());
            roomReservation.setGuestId(reservation.getGuest().getId());
            roomReservations.add(roomReservation);
        });

        roomReservations.sort((o1, o2) -> {
            if (o1.getRoomName().equals(o2.getRoomName())) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
            return o1.getRoomName().compareTo(o2.getRoomName());
        });
        return roomReservations;
    }
}

