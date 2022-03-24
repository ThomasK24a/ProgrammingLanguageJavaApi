package com.nhlstenden.programming_language_api.layers.controllers;

import com.nhlstenden.programming_language_api.layers.repositories.LanguageIssueCountRepository;
import com.nhlstenden.programming_language_api.layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.layers.services.LanguageIssueCountService;
import com.nhlstenden.programming_language_api.layers.services.LanguageService;
import com.nhlstenden.programming_language_api.layers.transformers.LanguageIssueCountTransformer;
import com.nhlstenden.programming_language_api.layers.transformers.LanguageTransformer;
import com.nhlstenden.programming_language_api.layers.validators.LanguageIssueCountValidator;
import com.nhlstenden.programming_language_api.layers.validators.LanguageValidator;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import com.nhlstenden.programming_language_api.models.LanguageIssueCount;
import com.nhlstenden.programming_language_api.models.LanguageIssueCountDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "languages")
public class LanguageIssueCountController extends GenericController<LanguageIssueCountService, LanguageIssueCountValidator, LanguageIssueCountDto, LanguageIssueCount, LanguageIssueCountRepository, LanguageIssueCountTransformer>{
    public LanguageIssueCountController(LanguageIssueCountService languageService, LanguageIssueCountValidator languageValidator) {
        super(languageService, languageValidator);
    }
}
