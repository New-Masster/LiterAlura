package com.alura.literalura.service;

import com.alura.literalura.dto.GutendexApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GutendexService {

    private static final String API_URL = "https://gutendex.com/books/";

    public GutendexApiResponse buscarLivros(String titulo) throws Exception {
        String url = API_URL + "?search=" + titulo;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), GutendexApiResponse.class);
    }
}
