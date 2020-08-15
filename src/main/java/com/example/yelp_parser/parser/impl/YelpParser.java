package com.example.yelp_parser.parser.impl;

import com.example.yelp_parser.parser.WebsiteParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class YelpParser implements WebsiteParser {

    private static final String BASE_CONTRACTORS_WEBSITE_URL =
            "https://www.yelp.com/search?cflt=contractors&find_loc=60605";
    private static final String BASE_YELP_URL = "https://www.yelp.com";



    public String parseWebPage() throws IOException, InterruptedException {
        Document document = Jsoup.connect(BASE_CONTRACTORS_WEBSITE_URL).get();

        List<String> pagesLinks = getAllPagesLinks(document);
        Map<String, String> contractorsWithLinks = findAllContractors(pagesLinks);



        return null;
    }

    private Map<String, String> findAllContractors(List<String> allPagesLinks) throws IOException,
            InterruptedException {
        Map<String, String> result = new LinkedHashMap<>();
        for (String pageLink : allPagesLinks) {
            Document document = Jsoup.connect(pageLink).get();

            Elements businessNameElements =
                    document.select("div[class*=\"businessName\"]");

            businessNameElements.stream()
                    .map(businessNameElement -> businessNameElement.child(0).child(0).child(0).child(0))
                    .forEach(fourthChild -> {
                        String contractorName = fourthChild.attr("name");
                        String contractorRedirectingLink = fourthChild.attr("href");

                        result.put(contractorName, contractorRedirectingLink);
                    });
            //            This is just for purpose to not get computer ip banned on the resource
            Thread.sleep(3000);
        }

        return result;
    }

    private List<String> retrieveContractorsLinks(List<Element> contractors) throws IOException {
        List<String> result = new ArrayList<>();

        for (Element contractor : contractors) {
            String linkToContractor = contractor.attr("href");

            Document document = Jsoup.connect(BASE_YELP_URL + linkToContractor).get();
            String contractorLink = document.getElementsByTag("script").stream()
                    .filter(element -> element.data().contains("location.replace"))
                    .map(Element::data)
                    .map(elementData -> elementData.substring(elementData.indexOf("\"") + 1,
                            elementData.lastIndexOf("\"")))
                    .findFirst()
                    .orElse(null);

            result.add(contractorLink);
        }

        return result;
    }

    private List<String> getAllPagesLinks(Document document) {
        List<String> result = new ArrayList<>();
        result.add(BASE_CONTRACTORS_WEBSITE_URL);

        Elements paginationElements =
                document.select("a[class*=\"pagination-link-component\"]");

        for (Element paginationElement : paginationElements) {
            String pageUri = paginationElement.attr("href");

            result.add(BASE_YELP_URL + pageUri);
        }

        //        TODO IMPLEMENT LOGIC WITH RETRIEVING ALL NON-VISIBLE PAGES(THEY BECOME VISIBLE WHEN YOU"RE O
        //TODO         ON A LAST VISIBLE PAGE)

        return result;
    }
}

