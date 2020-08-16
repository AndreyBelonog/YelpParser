package com.example.yelp_parser.mapper;

import com.example.yelp_parser.dto.ContractorDto;
import com.example.yelp_parser.entitity.ContractorEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractorMapper {

    ContractorEntity toEntity(ContractorDto contractorDto);

    List<ContractorEntity> toList(List<ContractorDto> contractorDtos);
}
