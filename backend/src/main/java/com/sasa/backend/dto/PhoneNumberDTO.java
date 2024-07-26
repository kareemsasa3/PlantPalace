package com.sasa.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberDTO {
    private Long id;
    private String number;
    private String type;
}
