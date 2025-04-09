// to return results
package com.example.hoen;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
    private final String city;
    private final String kind;
    private final String title;

    public SearchResult(@JsonProperty("city") String city,
                        @JsonProperty("kind") String kind,
                        @JsonProperty("title") String title) {
        this.city = city;
        this.kind = kind;
        this.title = title;
    }

    public String getCity() { return city; }
    public String getKind() { return kind; }
    public String getTitle() { return title; }
}
