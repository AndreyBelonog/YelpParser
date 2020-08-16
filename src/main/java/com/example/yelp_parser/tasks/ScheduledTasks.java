package com.example.yelp_parser.tasks;

import com.example.yelp_parser.worker.YelpWorker;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final long MILLIS_PER_DAY = 86400000;


    @Autowired
    private YelpWorker yelpWorker;


    @Scheduled(fixedRate = MILLIS_PER_DAY)
    public void retrieveNewContractorsAndWriteThemToDb() throws IOException, InterruptedException {
        yelpWorker.initializeYelpContractorsNamesAndLinks();
        yelpWorker.writeNewContractorsToDb();
    }
}
