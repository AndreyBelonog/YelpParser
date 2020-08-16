package com.example.yelp_parser.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractorDto {

    private String name;
    private String contacts;
    private String rating;
    private String description;
    private final Integer zip = 60605;
}
