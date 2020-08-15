package com.example.yelp_parser.tasks;

import com.example.yelp_parser.parser.impl.YelpParser;
import com.example.yelp_parser.worker.YelpWorker;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    @Autowired
    YelpWorker yelpWorker;

    @Autowired
    YelpParser yelpParser;




    @Scheduled(fixedRate = 1000000000)
    public void test() throws IOException, InterruptedException {
        yelpParser.parseWebPage();
    }
}
