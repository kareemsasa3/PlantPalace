package com.sasa.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasa.backend.service.GooglePlacesService;

@RestController
public class GooglePlacesController {
    
    private final GooglePlacesService googlePlacesService;

    public GooglePlacesController(GooglePlacesService googlePlacesService) {
        this.googlePlacesService = googlePlacesService;
    }

    @GetMapping("/api/places")
    public String getPlaceSuggestions(@RequestParam String input) {
        return googlePlacesService.getSuggestions(input);
    }
}
