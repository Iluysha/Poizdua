package com.example.poizdua.controller;

import com.example.poizdua.domain.Ticket;
import com.example.poizdua.repos.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class EditController {
    @Autowired
    private TicketRepo ticketRepo;

    @GetMapping("/edit")
    public String main() {
        return "edit";
    }

    @PostMapping("/add")
    public String addTickets(@RequestParam String from, @RequestParam String to,
                             @RequestParam String date, Map<String, Object> model) {

        for(int i = 1; i < 31; i++) {
        Ticket ticket = new Ticket(i, from, to, date, null);
            ticketRepo.save(ticket);
        }
        return "edit";
    }
}