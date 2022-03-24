package com.nhlstenden.programming_language_api.layers.services;

import com.nhlstenden.programming_language_api.layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.layers.transformers.LanguageTransformer;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import org.springframework.stereotype.Component;

@Component
public class LanguageService extends GenericService<Language, LanguageDto, LanguageRepository, LanguageTransformer>{

    public LanguageService(LanguageRepository repository, LanguageTransformer transformer) {
        super(repository, transformer);
    }
}
