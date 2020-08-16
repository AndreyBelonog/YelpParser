package com.example.yelp_parser.parser.impl;

import com.example.yelp_parser.dto.ContractorDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Yelp web resource parser.
 */
@Component
public class YelpParser {

    private static final Logger LOG = LoggerFactory.getLogger(YelpParser.class);
    private static final String BASE_CONTRACTORS_WEBSITE_URL =
            "https://www.yelp.com/search?cflt=contractors&find_loc=60605";
    private static final String BASE_YELP_URL = "https://www.yelp.com";
    private static final String NAME = "name";
    private static final String CONTACTS = "contacts";
    private static final String RATING = "rating";
    private static final String DESCRIPTION = "description";

    @Getter @Setter
    private Map<String, String> yelpContractorsNamesAndLinks;


    /**
     * Retrieving all "Contractors" entities' names and links from each page for the Yelp resource.
     *
     * @return Map<String, String>
     * @throws IOException ex
     * @throws InterruptedException ex
     */
    public Map<String, String> findAllContractorsNamesAndLinks() throws IOException,
            InterruptedException {

        Map<String, String> result = new LinkedHashMap<>();
        List<String> allPagesLinks = getAllPagesLinks();
        for (String pageLink : allPagesLinks) {
            Document document = Jsoup.connect(pageLink).get();
            Thread.sleep(5000);

            Elements businessNameElements =
                    document.select("div[class*=\"businessName\"]");

            businessNameElements.stream()
                    .map(businessNameElement -> businessNameElement.child(0).child(0).child(0).child(0))
                    .forEach(fourthChild -> {
                        String contractorName = fourthChild.attr(NAME);
                        String contractorRedirectingLink = fourthChild.attr("href");

                        result.put(contractorName, contractorRedirectingLink);
                    });
            //            This is just for purpose to not get computer ip banned on the resource
            Thread.sleep(5000);
        }

        return result;
    }

    /**
     * Creates and returns a collection of retrieved new "Contractors" entities.
     *
     * @param contractorsNames List<String> contractorsNames
     * @return List<ContractorDto>
     * @throws IOException ex
     * @throws InterruptedException ex
     */
    public List<ContractorDto> composeNewContractorsRecordsDtos(List<String> contractorsNames)
            throws IOException, InterruptedException {
        List<ContractorDto> result = new ArrayList<>();

        Map<String, String> newRecords = yelpContractorsNamesAndLinks.entrySet().stream()
                .filter(map -> contractorsNames.contains(map.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (Map.Entry<String, String> entry : newRecords.entrySet()) {
            Map<String, String> contractorsData = retrieveContractorsDataFromYelp(entry.getKey(), entry.getValue());
            if (contractorsData == null) {
                continue;
            }

            ContractorDto contractorDto = new ContractorDto();
            contractorDto.setName(contractorsData.get(NAME));
            contractorDto.setContacts(contractorsData.get(CONTACTS));
            contractorDto.setRating(contractorsData.get(RATING));
            contractorDto.setDescription(contractorsData.get(DESCRIPTION));

            result.add(contractorDto);
        }

        return result;
    }

    private Map<String, String> retrieveContractorsDataFromYelp(String contractorName, String redirectLink)
            throws IOException, InterruptedException {
        Map<String, String> result = new HashMap<>();
        result.put(NAME, contractorName);

        LOG.info("Parsing all data for {} contractor", contractorName);


        String composedValidLink = retrieveContractorLink(redirectLink);
        Document document = Jsoup.connect(composedValidLink).get();
        if (document == null) {
            return null;
        }
        //            This is just for purpose to not get computer ip banned on the resource
        Thread.sleep(5000);


        Element contactElement = document.select("p:contains(Phone number)").first();
        if (contactElement == null) {
            return null;
        }
        String contact = contactElement.parent().child(1).wholeText();
        result.put(CONTACTS, contact);

        Element ratingElement = document.select("img[src*=assets/public/stars]")
                .first();
        if (ratingElement != null) {
            String rating = ratingElement.parent().attr("aria-label");
            result.put(RATING, rating);
        }

        Element aboutTheBusinessElement = document.select("h4:contains(About the Business)").first();
        if (aboutTheBusinessElement != null) {
            String description = aboutTheBusinessElement.parent().parent().parent().child(1).child(1).wholeText();
            result.put(DESCRIPTION, description);
        }

        return result;
    }

    private String retrieveContractorLink(String redirectLink) throws IOException, InterruptedException {
        Document document = Jsoup.connect(BASE_YELP_URL + redirectLink).get();
        //            This is just for purpose to not get computer ip banned on the resource
        Thread.sleep(5000);
        return document.location();
    }


    private List<String> getAllPagesLinks() throws IOException, InterruptedException {
        List<String> result = new ArrayList<>();
        Document document = Jsoup.connect(BASE_CONTRACTORS_WEBSITE_URL).get();
        //            This is just for purpose to not get computer ip banned on the resource
        Thread.sleep(5000);

        result.add(BASE_CONTRACTORS_WEBSITE_URL);

        Elements paginationElements =
                document.select("a[class*=\"pagination-link-component\"]");

        for (Element paginationElement : paginationElements) {
            String pageUri = paginationElement.attr("href");

            result.add(BASE_YELP_URL + pageUri);
        }

        return result;
    }
}

