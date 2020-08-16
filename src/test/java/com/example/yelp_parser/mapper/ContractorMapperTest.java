package com.example.yelp_parser.mapper;

import com.example.yelp_parser.dto.ContractorDto;
import com.example.yelp_parser.entitity.ContractorEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mapstruct.factory.Mappers;

@TestInstance(Lifecycle.PER_CLASS)
class ContractorMapperTest {

    private ContractorMapper contractorMapper;


    @BeforeAll
    void setUp() {
        contractorMapper = Mappers.getMapper(ContractorMapper.class);
    }

    @Test
    void toDto_test() {
        ContractorDto contractorDto = DtoTestFactory.buildContractorDto();

        ContractorEntity contractorEntity1 = contractorMapper.toEntity(contractorDto);

        Assertions
                .assertThat(contractorDto)
                .isEqualToComparingFieldByField(contractorEntity1);
    }
}
