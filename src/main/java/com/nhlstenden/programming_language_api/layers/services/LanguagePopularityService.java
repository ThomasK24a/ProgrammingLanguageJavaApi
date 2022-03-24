package com.nhlstenden.programming_language_api.layers.services;

import com.nhlstenden.programming_language_api.layers.repositories.LanguagePopularityRepository;
import com.nhlstenden.programming_language_api.layers.transformers.LanguagePopularityTransformer;
import com.nhlstenden.programming_language_api.models.LanguagePopularity;
import com.nhlstenden.programming_language_api.models.LanguagePopularityDto;
import org.springframework.stereotype.Component;

@Component
public class LanguagePopularityService extends GenericService<LanguagePopularity, LanguagePopularityDto, LanguagePopularityRepository, LanguagePopularityTransformer>{

    public LanguagePopularityService(LanguagePopularityRepository repository, LanguagePopularityTransformer transformer) {
        super(repository, transformer);
    }
}
