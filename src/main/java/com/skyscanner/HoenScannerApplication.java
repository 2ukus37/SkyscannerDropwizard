package com.skyscanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

import javax.naming.directory.SearchResult;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    private List<SearchResult> searchResults;

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {
        // No initialization needed for now
    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load JSON files into a list
        try (InputStream carStream = getClass().getResourceAsStream("/rental_cars.json");
             InputStream hotelStream = getClass().getResourceAsStream("/hotels.json")) {

            List<SearchResult> carResults = objectMapper.readValue(carStream, new TypeReference<>() {});
            List<SearchResult> hotelResults = objectMapper.readValue(hotelStream, new TypeReference<>() {});

            searchResults = new ArrayList<>();
            searchResults.addAll(carResults);
            searchResults.addAll(hotelResults);
        }

        // Register the search resource with Dropwizard
        environment.jersey().register(new SearchResource(searchResults));

    }


    }






































