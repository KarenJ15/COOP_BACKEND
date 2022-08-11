package com.taxi.taxi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_CLIENTS")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAMES")
    private String name; 

    @Column(name = "CELLPHONE")
    private String cellphone;

    @Column(name = "DIRECTION")
    private String direction;

    // @ManyToOne
    // @JoinColumn(name="TRAVEL_ID", nullable=false)
    // private Travel travel;    
}
