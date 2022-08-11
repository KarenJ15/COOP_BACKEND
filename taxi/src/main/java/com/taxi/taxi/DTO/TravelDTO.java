package com.taxi.taxi.DTO;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelDTO extends NewTravelDTO{
    private Long id;
    private List<ClientDTO> clients;
}
