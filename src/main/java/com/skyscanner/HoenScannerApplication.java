package com.skyscanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyscanner.api.SearchResult;
import com.skyscanner.resources.SearchResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {
    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) throws IOException {
        // Create ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        
        // Load search results from JSON files
        List<SearchResult> searchResults = new ArrayList<>();
        
        // Load hotels
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("hotels.json")) {
            searchResults.addAll(mapper.readValue(is, new TypeReference<List<SearchResult>>() {}));
        }
        
        // Load rental cars
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("rental_cars.json")) {
            searchResults.addAll(mapper.readValue(is, new TypeReference<List<SearchResult>>() {}));
        }
        
        // Register SearchResource
        environment.jersey().register(new SearchResource(searchResults));
    }
}
