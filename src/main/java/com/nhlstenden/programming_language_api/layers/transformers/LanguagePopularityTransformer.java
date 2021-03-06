package com.nhlstenden.programming_language_api.layers.transformers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nhlstenden.programming_language_api.exceptions.ObjectNotFoundException;
import com.nhlstenden.programming_language_api.exceptions.TransformerErrorException;
import com.nhlstenden.programming_language_api.layers.services.LanguageService;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import com.nhlstenden.programming_language_api.models.LanguagePopularity;
import com.nhlstenden.programming_language_api.models.LanguagePopularityDto;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class LanguagePopularityTransformer extends GenericTransformer<LanguagePopularity, LanguagePopularityDto> {

    private final LanguageService languageService;

    public LanguagePopularityTransformer(LanguageService languageService) {
        super(LanguagePopularityDto.class);
        this.languageService = languageService;
    }

    @Override
    public LanguagePopularityDto jsonToDTO(JSONObject jsonEntity) {
        try{
            TypeReference<LanguagePopularityDto> mapType= new TypeReference<>() {};;
            return objectMapper.readValue(String.valueOf(jsonEntity), mapType);
        }catch (Exception exception){
            throw new TransformerErrorException("json");
        }
    }

    @Override
    public LanguagePopularityDto entityToDTO(LanguagePopularity languagePopularity) {
        LanguagePopularityDto languagePopularityDto = new LanguagePopularityDto();
        languagePopularityDto.setLanguageName(languagePopularity.getLanguage().getLanguageName());
        languagePopularityDto.setYear(languagePopularity.getYear());
        languagePopularityDto.setRatingPercentile(languagePopularity.getRatingPercentile());
        languagePopularityDto.setId(languagePopularity.getId());
        return languagePopularityDto;
    }

    @Override
    public LanguagePopularity dtoToEntity(LanguagePopularityDto languagePopularityDto) {
        LanguagePopularity languagePopularity = new LanguagePopularity();
        Language language = languageService.getLanguageFromName(languagePopularityDto.getLanguageName());
        if(language == null){
            throw new ObjectNotFoundException(languagePopularityDto.getLanguageName());
        }
        languagePopularity.setLanguage(language);
        languagePopularity.setYear(languagePopularityDto.getYear());
        languagePopularity.setRatingPercentile(languagePopularityDto.getRatingPercentile());
        return languagePopularity;
    }
}