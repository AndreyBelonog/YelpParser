package com.example.yelp_parser.tasks;

import com.example.yelp_parser.parser.impl.YelpParser;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    @Autowired
    YelpParser yelpParser;


    @Scheduled(fixedRate = 1000000000)
    public void test() throws IOException {
        yelpParser.parseWebPage();
    }
}
