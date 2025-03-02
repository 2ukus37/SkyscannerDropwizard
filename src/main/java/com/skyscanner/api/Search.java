package com.skyscanner.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    private String city;

    public Search() {
        // Jackson deserialization
    }

    @JsonProperty
    public String getCity() {
        return city;
    }

    @JsonProperty
    public void setCity(String city) {
        this.city = city;
    }
}
