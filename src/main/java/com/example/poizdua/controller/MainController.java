package com.example.poizdua.controller;

import com.example.poizdua.domain.Role;
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
public class MainController {
    @Autowired
    private TicketRepo ticketRepo;

    @GetMapping("/")
    public String login(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User buyer, Map<String, Object> model) {
        model.put("flag", buyer.getRoles().contains(Role.ADMIN));
        return "main";
    }

    @PostMapping("find")
    public String filter(@RequestParam String from, @RequestParam String to,
                         @RequestParam String date, Map<String, Object> model) {

        Iterable<Ticket> tickets = ticketRepo.findByFromAndToAndDate(from, to, date);

        if(tickets.spliterator().getExactSizeIfKnown() == 0){
            model.put("title", "No tickets per your request");
        } else{
            model.put("title", "Tickets on " + date + " from " + from + " to " + to + " :");
            model.put("tickets", tickets);
        }

        return "main";
    }

    @PostMapping("buy")
    public String buy(@RequestParam Integer id, @AuthenticationPrincipal User buyer, Map<String, Object> model) {

        Ticket ticket = ticketRepo.findById(id).orElseGet(Ticket::new);
        model.put("title", "You bought ticket from " + ticket.getFrom() +
                " to " + ticket.getTo() + " on " + ticket.getDate() + ", place " + ticket.getNum());
        ticket.setBuyer(buyer);
        ticketRepo.save(ticket);

        return "main";
    }
}