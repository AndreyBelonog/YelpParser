package com.example.yelp_parser.worker;

import com.example.yelp_parser.dto.ContractorDto;
import com.example.yelp_parser.entitity.ContractorEntity;
import com.example.yelp_parser.mapper.ContractorMapper;
import com.example.yelp_parser.parser.impl.YelpParser;
import com.example.yelp_parser.repository.ContractorsRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Yelp worker spring component for comparing existing "Contractors" values
 * with new values from "Yelp" resource. Writes new ones to a DB.
 */
@Component
public class YelpWorker {

    @Autowired
    private YelpParser yelpParser;
    @Autowired
    private ContractorsRepository contractorsRepository;
    @Autowired
    private ContractorMapper contractorMapper;

    private Map<String, String> yelpContractorsNamesAndLinks;


    /**
     * Initializes a collection of all "Contractors" entities by name and redirect link for those.
     *
     * @throws IOException ex
     * @throws InterruptedException ex
     */
    public void initializeYelpContractorsNamesAndLinks() throws IOException, InterruptedException {
        yelpContractorsNamesAndLinks = yelpParser.findAllContractorsNamesAndLinks();
        yelpParser.setYelpContractorsNamesAndLinks(yelpContractorsNamesAndLinks);
    }

    /**
     * Writes non-existent "Contractors" entities to a DB.
     *
     * @throws IOException ex
     * @throws InterruptedException ex
     */
    public void writeNewContractorsToDb() throws IOException, InterruptedException {
        List<String> newContractorsNames = getNewContractorsNames();

        List<ContractorDto> contractorDtos = yelpParser.composeNewContractorsRecordsDtos(newContractorsNames);

        contractorsRepository.saveAll(contractorMapper.toList(contractorDtos));

    }

    private List<String> retrieveAllExistingContractorsNames() {
        return contractorsRepository.findAll().stream()
                .map(ContractorEntity::getName)
                .collect(Collectors.toList());
    }

    private List<String> getNewContractorsNames() throws IOException, InterruptedException {
        List<String> existingContractorsNames = retrieveAllExistingContractorsNames();
        List<String> retrievedYelpContractorsNames =
                new ArrayList<>(yelpContractorsNamesAndLinks.keySet());

        return retrievedYelpContractorsNames.stream()
                .filter(yelpContractorName -> !existingContractorsNames.contains(yelpContractorName))
                .collect(Collectors.toList());
    }
}
