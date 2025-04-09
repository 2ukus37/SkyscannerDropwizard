// to recieve input
package com.example.hoen;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    private final String city;

    public Search(@JsonProperty("city") String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
