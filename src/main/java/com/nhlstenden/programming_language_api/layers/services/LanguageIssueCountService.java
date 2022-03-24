package com.nhlstenden.programming_language_api.layers.services;

import com.nhlstenden.programming_language_api.layers.repositories.LanguageIssueCountRepository;
import com.nhlstenden.programming_language_api.layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.layers.transformers.LanguageIssueCountTransformer;
import com.nhlstenden.programming_language_api.layers.transformers.LanguageTransformer;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import com.nhlstenden.programming_language_api.models.LanguageIssueCount;
import com.nhlstenden.programming_language_api.models.LanguageIssueCountDto;
import org.springframework.stereotype.Component;

@Component
public class LanguageIssueCountService extends GenericService<LanguageIssueCount, LanguageIssueCountDto, LanguageIssueCountRepository, LanguageIssueCountTransformer>{

    public LanguageIssueCountService(LanguageIssueCountRepository repository, LanguageIssueCountTransformer transformer) {
        super(repository, transformer);
    }
}
