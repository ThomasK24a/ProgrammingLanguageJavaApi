package com.nhlstenden.programming_language_api.api_layers.controllers;

import com.nhlstenden.programming_language_api.api_layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.api_layers.services.LanguageService;
import com.nhlstenden.programming_language_api.api_layers.validators.LanguageValidator;
import com.nhlstenden.programming_language_api.data.models.Language;
import com.nhlstenden.programming_language_api.data.models.LanguageDto;
import com.nhlstenden.programming_language_api.data.transformers.LanguageTransformer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "languages")
public class LanguageController extends GenericController<LanguageService, LanguageValidator, LanguageDto, Language, LanguageRepository, LanguageTransformer>{

    public LanguageController(LanguageService languageService, LanguageValidator languageValidator) {
        super(languageService, languageValidator);
    }


}
