package com.taxi.taxi.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientListDTO {
    private Long id;
    private String name; 
    private String cellphone;
    private String direction;
}
