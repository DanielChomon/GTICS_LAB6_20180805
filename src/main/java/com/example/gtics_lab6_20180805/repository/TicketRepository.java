package com.example.gtics_lab6_20180805.repository;

import com.example.gtics_lab6_20180805.entity.Location;
import com.example.gtics_lab6_20180805.entity.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    }
