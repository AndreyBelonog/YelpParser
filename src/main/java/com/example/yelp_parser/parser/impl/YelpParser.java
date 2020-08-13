package com.example.yelp_parser.parser.impl;

import com.example.yelp_parser.parser.WebsiteParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class YelpParser implements WebsiteParser {

    private static final Logger LOG = LoggerFactory.getLogger(YelpParser.class);
    private static final String WEBSITE_URL = "https://www.yelp.com/search?cflt=contractors&find_loc=60605";



    public String parseWebPage() throws IOException {
        Document document = Jsoup.connect(WEBSITE_URL).get();

        List<Element> contractors = findAllContractors(document);
        List<ContractorDto>






        System.out.println(322);


        return null;
    }

    private List<Element> findAllContractors(Document document) {
        Elements businessNameElement =
                document.select("div[class*=\"businessName\"]");

        return businessNameElement.stream()
                .map(element -> element.child(0).child(0).child(0).child(0))
                .collect(Collectors.toList());
    }

    private List<String> parseContractorsData() {

    }
}

