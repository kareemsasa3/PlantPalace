package com.sasa.backend.service.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;

@Service
public class GooglePlacesService {
    
    @Value("${google.places.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GooglePlacesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getSuggestions(String input) {
        try {
            String encodedInput = URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
            String urlString = String.format("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%s&key=%s", 
                                             encodedInput, 
                                             apiKey);
            return restTemplate.getForObject(urlString, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
