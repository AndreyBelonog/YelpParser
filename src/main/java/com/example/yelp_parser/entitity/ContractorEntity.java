package com.example.yelp_parser.entitity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CONTRACTORS")
public class ContractorEntity {

    @Id
    private String name;
    private Integer zip = 60605;
    private String contacts;
    private Short rating;
    private String description;
}
