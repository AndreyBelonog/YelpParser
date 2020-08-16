package com.example.yelp_parser.worker;

import com.example.yelp_parser.parser.impl.YelpParser;
import com.example.yelp_parser.repository.ContractorsRepository;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class YelpWorkerTest {

    @Mock
    private YelpParser yelpParser;

    @Mock
    private ContractorsRepository contractorsRepository;

    @InjectMocks
    private YelpWorker yelpWorker;


    @Test
    void test_initializeYelpContractorsNamesAndLinks() throws IOException, InterruptedException {
        yelpWorker.initializeYelpContractorsNamesAndLinks();

        Mockito.verify(yelpParser, Mockito.times(1)).findAllContractorsNamesAndLinks();
        Mockito.verify(yelpParser, Mockito.times(1))
                .setYelpContractorsNamesAndLinks(ArgumentMatchers.anyMap());
    }
}
