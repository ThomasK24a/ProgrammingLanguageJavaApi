package com.nhlstenden.programming_language_api.layers.controllers;

import com.nhlstenden.programming_language_api.layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.layers.services.LanguageService;
import com.nhlstenden.programming_language_api.layers.transformers.LanguageTransformer;
import com.nhlstenden.programming_language_api.layers.validators.LanguageValidator;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "languages")
public class LanguagePopularityController extends GenericController<LanguagePopularityService, LanguagePopularityValidator, LanguagePopularityDto, LanguagePopularity, LanguagePopularityRepository, LanguagePopularityTransformer>{
    public LanguagePopularityController(LanguagePopularityService languagePopularityService, LanguagePopularityValidator languagePopularityValidator) {
        super(languagePopularityService, languagePopularityValidator);
    }
}
