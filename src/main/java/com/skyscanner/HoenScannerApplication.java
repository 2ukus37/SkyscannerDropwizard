package com.skyscanner;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

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
    public void run(HoenScannerConfiguration configuration, Environment environment) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<SearchResult> searchResults = new ArrayList<>();
    
        searchResults.addAll(Arrays.asList(
            mapper.readValue(
                Resources.getResource("hotels.json"),
                SearchResult[].class
            )
        ));
    
        searchResults.addAll(Arrays.asList(
            mapper.readValue(
                Resources.getResource("rental_cars.json"),
                SearchResult[].class
            )
        ));
    
        environment.jersey().register(new SearchResource(searchResults));
    }


}
