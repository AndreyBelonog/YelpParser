package com.example.yelp_parser.repository;

import com.example.yelp_parser.entitity.ContractorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorsRepository extends JpaRepository<ContractorEntity, String> {
}
