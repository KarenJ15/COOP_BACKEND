package com.taxi.taxi.models;

//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_TRAVELS" )
@Getter
@Setter
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORIGIN")
    private String origin; 

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "COST")
    private Float cost;

    @ManyToOne
    @JoinColumn(name="TAXI_ID", nullable=false)
    private Taxi taxi;

    // @OneToMany(mappedBy = "travel")
    // private List<Client> clients;
}
