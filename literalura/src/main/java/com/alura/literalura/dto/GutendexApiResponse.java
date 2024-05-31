package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexApiResponse {
    private List<GutenbergBook> results;

    public List<GutenbergBook> getResults() {
        return results;
    }

    public void setResults(List<GutenbergBook> results) {
        this.results = results;
    }

    // Getters e Setters
}
