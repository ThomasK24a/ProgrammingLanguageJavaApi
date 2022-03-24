package com.nhlstenden.programming_language_api.api_layers.services;

import com.nhlstenden.programming_language_api.api_layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.data.models.Language;
import com.nhlstenden.programming_language_api.data.models.LanguageDto;
import com.nhlstenden.programming_language_api.data.transformers.LanguageTransformer;
import org.springframework.stereotype.Component;

@Component
public class LanguageService extends GenericService<Language, LanguageDto, LanguageRepository, LanguageTransformer>{

    public LanguageService(LanguageRepository repository, LanguageTransformer transformer) {
        super(repository, transformer);
    }
}
