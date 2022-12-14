package com.taxi.taxi.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewTaxiDTO {
    private String licensePlate; 
    private String model; 
    private String tradeMark; 
    private String registration;
}
