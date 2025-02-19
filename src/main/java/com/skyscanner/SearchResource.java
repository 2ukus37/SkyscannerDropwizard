package com.skyscanner;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {
    private final List<SearchResult> searchResults;

    public SearchResource(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    @POST
    public Response search(Search search) {
        if (search == null || search.getCity() == null || search.getCity().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "City field is required."))
                    .build();
        }

        String city = search.getCity().trim().toLowerCase(); // Normalize input
        List<SearchResult> filteredResults = searchResults.stream()
                .filter(result -> result.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

        if (filteredResults.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("message", "No results found for the given city."))
                    .build();
        }

        return Response.ok(filteredResults).build();
    }
}
