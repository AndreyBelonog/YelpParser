package com.example.yelp_parser.parser;

import java.io.IOException;

public interface WebsiteParser {

    public String parseWebPage() throws IOException, InterruptedException;

}
