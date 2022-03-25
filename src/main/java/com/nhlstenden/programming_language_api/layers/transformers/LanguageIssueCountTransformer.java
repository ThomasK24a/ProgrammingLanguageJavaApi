package com.nhlstenden.programming_language_api.layers.transformers;

import com.nhlstenden.programming_language_api.exceptions.ObjectNotFoundException;
import com.nhlstenden.programming_language_api.layers.services.LanguageService;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageIssueCount;
import com.nhlstenden.programming_language_api.models.LanguageIssueCountDto;
import org.springframework.stereotype.Component;

@Component
public class LanguageIssueCountTransformer extends GenericTransformer<LanguageIssueCount, LanguageIssueCountDto> {

    private final LanguageService languageService;

    public LanguageIssueCountTransformer(LanguageService languageService) {
        super(LanguageIssueCountDto.class);
        this.languageService = languageService;
    }

    @Override
    public LanguageIssueCountDto entityToDTO(LanguageIssueCount languageIssueCount) {
        LanguageIssueCountDto languageIssueCountDto = new LanguageIssueCountDto();
        languageIssueCountDto.setLanguageName(languageIssueCount.getLanguage().getLanguageName());
        languageIssueCountDto.setIssueCount(languageIssueCount.getIssueCount());
        languageIssueCountDto.setQuarter(languageIssueCount.getQuarter());
        languageIssueCountDto.setYear(languageIssueCount.getYear());
        languageIssueCountDto.setId(languageIssueCount.getId());
        return languageIssueCountDto;
    }

    @Override
    public LanguageIssueCount dtoToEntity(LanguageIssueCountDto languageIssueCountDto) {
        LanguageIssueCount languageIssueCount = new LanguageIssueCount();
        Language language = languageService.getLanguageFromName(languageIssueCountDto.getLanguageName());
        if(language == null){
            throw new ObjectNotFoundException(languageIssueCountDto.getLanguageName());
        }
        languageIssueCount.setLanguage(language);
        languageIssueCount.setIssueCount(languageIssueCountDto.getIssueCount());
        languageIssueCount.setQuarter(languageIssueCountDto.getQuarter());
        languageIssueCount.setYear(languageIssueCountDto.getYear());
        languageIssueCount.setId(languageIssueCountDto.getId());
        return languageIssueCount;
    }
}