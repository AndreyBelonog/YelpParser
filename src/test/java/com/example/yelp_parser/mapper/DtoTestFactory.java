package com.example.yelp_parser.mapper;

import com.example.yelp_parser.dto.ContractorDto;
import java.util.Arrays;
import java.util.List;

public class DtoTestFactory {

    public static ContractorDto buildContractorDto() {
        ContractorDto contractorDto = new ContractorDto();
        contractorDto.setName("name_1");
        contractorDto.setContacts("contacts_1");
        contractorDto.setDescription("description_1");
        contractorDto.setRating("rating_1");

        return contractorDto;
    }

    public static List<ContractorDto> buildContractorDtoList() {
        ContractorDto firstContractorDto = new ContractorDto();
        firstContractorDto.setName("name_1");
        firstContractorDto.setContacts("contacts_1");
        firstContractorDto.setDescription("description_1");
        firstContractorDto.setRating("rating_1");

        ContractorDto secondContractorDto = new ContractorDto();
        secondContractorDto.setName("name_2");
        secondContractorDto.setContacts("contacts_2");
        secondContractorDto.setDescription("description_2");
        secondContractorDto.setRating("rating_2");

        return Arrays.asList(firstContractorDto, secondContractorDto);
    }
}
