package com.example.yelp_parser.worker;

import com.example.yelp_parser.entitity.ContractorEntity;
import com.example.yelp_parser.parser.impl.YelpParser;
import com.example.yelp_parser.repository.ContractorsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YelpWorker {

    @Autowired
    YelpParser yelpParser;

    @Autowired
    ContractorsRepository contractorsRepository;


    public void writeNewContractorsToDb() {
//        List<String> existingContractorsNames = retrieveAllExistingContractorsNames();

        List<String> websiteContractors = null;

//        for (String contractorName : existingContractorsNames) {
//            if (!existingContractorsNames.contains(contractorName)) {
//                TODO yelpParser -> parse all data for contractor
//                contractorsRepository.save(yelpParser)
            }




//    }

//    private List<String> retrieveAllExistingContractorsNames() {
//        return contractorsRepository.findAll().stream()
//                .map(ContractorEntity::getName)
//                .collect(Collectors.toList());
//    }
}
