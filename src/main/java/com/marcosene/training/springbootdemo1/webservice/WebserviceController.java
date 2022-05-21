package com.marcosene.training.springbootdemo1.webservice;

import com.marcosene.training.springbootdemo1.business.GuestService;
import com.marcosene.training.springbootdemo1.business.ReservationService;
import com.marcosene.training.springbootdemo1.business.RoomReservation;
import com.marcosene.training.springbootdemo1.business.RoomService;
import com.marcosene.training.springbootdemo1.data.Guest;
import com.marcosene.training.springbootdemo1.data.Room;
import com.marcosene.training.springbootdemo1.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class WebserviceController {

    private final DateUtils dateUtils;
    private final RoomService roomService;
    private final GuestService guestService;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, RoomService roomService, GuestService guestService, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.roomService = roomService;
        this.guestService = guestService;
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/reservations")
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }

    @PostMapping(path = "/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReservation(@RequestBody RoomReservation roomReservation) {
        Room room = roomService.getRoom(roomReservation.getRoomId());
        Guest guest = guestService.getGuest(roomReservation.getGuestId());
        reservationService.addReservation(room, guest, roomReservation.getDate());
    }
}
