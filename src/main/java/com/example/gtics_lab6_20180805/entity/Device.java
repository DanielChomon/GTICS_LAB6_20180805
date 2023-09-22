package com.example.gtics_lab6_20180805.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "devices")
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DeviceID")
    private int id;
    private String DeviceName;
    private String DeviceType;
    private String Model;
    private String SerialNumber;
    @ManyToOne
    @JoinColumn(name = "SiteID")
    private Site site;
}
