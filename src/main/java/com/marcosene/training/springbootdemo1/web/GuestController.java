package com.marcosene.training.springbootdemo1.web;

import com.marcosene.training.springbootdemo1.business.GuestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String getGuests(Model model) {
        model.addAttribute("guests", guestService.getGuests());
        return "hotel-guests";
    }
}
