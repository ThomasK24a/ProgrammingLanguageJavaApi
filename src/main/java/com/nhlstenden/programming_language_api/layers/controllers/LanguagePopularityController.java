package com.nhlstenden.programming_language_api.layers.controllers;

import com.nhlstenden.programming_language_api.layers.repositories.LanguagePopularityRepository;
import com.nhlstenden.programming_language_api.layers.services.LanguagePopularityService;
import com.nhlstenden.programming_language_api.layers.transformers.LanguagePopularityTransformer;
import com.nhlstenden.programming_language_api.layers.validators.LanguagePopularityValidator;
import com.nhlstenden.programming_language_api.models.LanguagePopularity;
import com.nhlstenden.programming_language_api.models.LanguagePopularityDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "languages")
public class LanguagePopularityController extends GenericController<LanguagePopularityService, LanguagePopularityValidator, LanguagePopularityDto, LanguagePopularity, LanguagePopularityRepository, LanguagePopularityTransformer>{
    public LanguagePopularityController(LanguagePopularityService languagePopularityService, LanguagePopularityValidator languagePopularityValidator) {
        super(languagePopularityService, languagePopularityValidator);
    }
}
