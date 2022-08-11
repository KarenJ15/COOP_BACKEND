package com.taxi.taxi.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelTaxiDTO {
    private TaxiDTO taxi;
    private TravelDTO travel;
}
