package com.example.yelp_parser.tasks;

import com.example.yelp_parser.worker.YelpWorker;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ScheduledTasksTest {

    @Mock
    private YelpWorker yelpWorker;

    @InjectMocks
    private ScheduledTasks scheduledTasks;


    @Test
    void test_retrieveNewContractorsAndWriteThemToDb() throws IOException, InterruptedException {
        scheduledTasks.retrieveNewContractorsAndWriteThemToDb();

        Mockito.verify(yelpWorker, Mockito.times(1)).writeNewContractorsToDb();
        Mockito.verify(yelpWorker, Mockito.times(1)).initializeYelpContractorsNamesAndLinks();
    }
}
