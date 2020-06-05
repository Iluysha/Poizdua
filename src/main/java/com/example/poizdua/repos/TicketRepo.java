package com.example.poizdua.repos;

import com.example.poizdua.domain.Ticket;
import com.example.poizdua.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepo extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByFromAndToAndDate(String from, String to, String Date);
    List<Ticket> findByBuyer(User buyer);
    Optional<Ticket> findById(Integer id);
}