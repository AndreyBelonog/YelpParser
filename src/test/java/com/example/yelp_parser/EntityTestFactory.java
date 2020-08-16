package com.example.yelp_parser;

import com.example.yelp_parser.entitity.ContractorEntity;

public class EntityTestFactory {

    public static ContractorEntity buildContractorEntity() {
        ContractorEntity contractorEntity = new ContractorEntity();
        contractorEntity.setName("name_1");
        contractorEntity.setContacts("contacts_1");
        contractorEntity.setDescription("description_1");
        contractorEntity.setRating("rating_1");

        return contractorEntity;
    }
}
