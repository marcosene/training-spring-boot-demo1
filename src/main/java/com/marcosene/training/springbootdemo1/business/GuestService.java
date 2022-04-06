package com.marcosene.training.springbootdemo1.business;

import com.marcosene.training.springbootdemo1.data.Guest;
import com.marcosene.training.springbootdemo1.data.GuestRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getGuests() {
        Iterable<Guest> guestsIt = guestRepository.findAll();
        List<Guest> guests = new ArrayList<>();
        guestsIt.forEach(guests::add);
        guests.sort((o1, o2) -> {
            if (StringUtils.equals(o1.getLastName(), o2.getLastName())) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            } else {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return guests;
    }
}
