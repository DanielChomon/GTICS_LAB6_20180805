package com.example.gtics_lab6_20180805.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "SiteID")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "TechnicianID")
    private Technician technician;

    private String Status;
    private Timestamp OpenedDate;
    private Timestamp ClosedDate;
}
