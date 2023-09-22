package com.example.gtics_lab6_20180805.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "sites")
@Getter
@Setter
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SiteID")
    private int id;
    private String SiteName;
    @ManyToOne
    @JoinColumn(name = "LocationID")
    private Location location;

    private Date InstallationDate;
    private String Latitude;
    private String Longitude;
}
