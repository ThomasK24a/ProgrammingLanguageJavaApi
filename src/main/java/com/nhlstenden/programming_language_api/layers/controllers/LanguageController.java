package com.nhlstenden.programming_language_api.layers.controllers;

import com.nhlstenden.programming_language_api.layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.layers.services.LanguageService;
import com.nhlstenden.programming_language_api.layers.transformers.LanguageTransformer;
import com.nhlstenden.programming_language_api.layers.validators.LanguageValidator;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "languages")
@Api(value = "Language controller", protocols = "GET,PUT,POST,DELETE", consumes = "application/json, application/xml", produces = "application/json, application/xml")
public class LanguageController extends GenericController<LanguageService, LanguageValidator, LanguageDto, Language, LanguageRepository, LanguageTransformer>{
    public LanguageController(LanguageService languageService, LanguageValidator languageValidator) {
        super(languageService, languageValidator);
    }
}
