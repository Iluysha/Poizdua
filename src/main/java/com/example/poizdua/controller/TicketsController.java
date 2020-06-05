package com.example.poizdua.controller;

import com.example.poizdua.domain.Ticket;
import com.example.poizdua.domain.User;
import com.example.poizdua.repos.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TicketsController {
    @Autowired
    private TicketRepo ticketRepo;

    @GetMapping("/tickets")
    public String main(@AuthenticationPrincipal User buyer, Map<String, Object> model) {
        Iterable<Ticket> tickets = ticketRepo.findByBuyer(buyer);

        if(tickets.spliterator().getExactSizeIfKnown() == 0){
            model.put("title", "You haven`t bought any tickets yet");
        } else{
            model.put("title", "Your tickets");
            model.put("tickets", tickets);
        }
        return "tickets";
    }

    @PostMapping("/return")
    public String main(@RequestParam Integer id, @AuthenticationPrincipal User buyer, Map<String, Object> model) {

        Ticket ticket = ticketRepo.findById(id).orElseGet(Ticket::new);
        ticket.setBuyer(null);
        ticketRepo.save(ticket);
        model.put("title", "Ticket returned");

        Iterable<Ticket> tickets = ticketRepo.findByBuyer(buyer);
        if(tickets.spliterator().getExactSizeIfKnown() == 0){
            model.put("title", "You haven`t bought any tickets yet");
            model.put("tickets", false);
        } else {
            model.put("title", "Your tickets");
            model.put("tickets", tickets);
        }
        return "tickets";
    }
}