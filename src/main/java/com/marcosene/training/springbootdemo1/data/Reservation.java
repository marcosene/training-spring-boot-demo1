package com.marcosene.training.springbootdemo1.data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="RESERVATION_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name="ROOM_ID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "GUEST_ID")
    private Guest guest;

    @Column(name="RES_DATE")
    private Date reservationDate;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room +
                ", guest=" + guest +
                ", reservationDate=" + reservationDate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
