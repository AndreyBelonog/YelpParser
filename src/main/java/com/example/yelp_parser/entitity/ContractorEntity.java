package com.example.yelp_parser.entitity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CONTRACTORS")
@Getter
@Setter
public class ContractorEntity {

    @Id
    private String name;
    private String contacts;
    private String rating;
    private String description;
    private Integer zip = 60605;
}
