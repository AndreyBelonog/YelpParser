package com.example.yelp_parser.entitity;

//import javax.persistence.Entity;
//import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public class ContractorEntity {

//    @Id
    private Integer id;
    private Integer zip = 60605;
    private String name;
    private String contacts;
    private Short rating;
    private String description;
}
