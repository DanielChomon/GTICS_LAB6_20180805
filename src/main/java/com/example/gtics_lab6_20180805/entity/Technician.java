package com.example.gtics_lab6_20180805.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "technicians")
@Getter
@Setter
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TechnicianID")
    private int id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Phone;

}
