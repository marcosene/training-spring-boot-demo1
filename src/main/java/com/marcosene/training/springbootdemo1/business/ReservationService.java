package com.marcosene.training.springbootdemo1.business;

import com.marcosene.training.springbootdemo1.data.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Map<Room, RoomReservation> roomReservationMap = new TreeMap<>(Comparator.comparing(Room::getRoomNumber));

        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(room -> roomReservationMap.put(room, new RoomReservation(room.getId(), room.getName(), room.getRoomNumber())));

        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(date);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setDate(date);
            roomReservation.setRoomId(reservation.getRoom().getId());
            roomReservation.setRoomName(reservation.getRoom().getName());
            roomReservation.setRoomNumber(reservation.getRoom().getRoomNumber());
            roomReservation.setFirstName(reservation.getGuest().getFirstName());
            roomReservation.setLastName(reservation.getGuest().getLastName());
            roomReservation.setGuestId(reservation.getGuest().getId());
            roomReservationMap.put(reservation.getRoom(), roomReservation);
        });
        return roomReservationMap.values().stream().toList();
    }
}

