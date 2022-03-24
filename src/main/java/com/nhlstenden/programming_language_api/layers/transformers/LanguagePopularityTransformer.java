package com.nhlstenden.programming_language_api.layers.transformers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nhlstenden.programming_language_api.exceptions.TransformerErrorException;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import com.nhlstenden.programming_language_api.models.LanguagePopularity;
import com.nhlstenden.programming_language_api.models.LanguagePopularityDto;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class LanguagePopularityTransformer extends GenericTransformer<LanguagePopularity, LanguagePopularityDto> {

    public LanguagePopularityTransformer() {
        super();
    }

    public LanguagePopularityDto JsonToDTO(JSONObject jsonLanguagePopularity){
        try{
            TypeReference<LanguagePopularityDto> mapType= new TypeReference<>() {};;
            return objectMapper.readValue(String.valueOf(jsonLanguagePopularity), mapType);
        }catch (Exception exception){
            throw new TransformerErrorException("json");
        }
    }

    public JSONObject DTOToJson(LanguagePopularityDto languagePopularityDto) {
        try{
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(languagePopularityDto);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonString);
        }catch(Exception ignored){
            throw new TransformerErrorException("languagePopularity");
        }
    }

    @Override
    public LanguagePopularityDto entityToDTO(LanguagePopularity languagePopularity) {
        LanguagePopularityDto languagePopularityDto = new LanguagePopularityDto();
        languagePopularityDto.setLanguageId(languagePopularity.getLanguage().getId());
        languagePopularityDto.setYear(languagePopularity.getYear());
        languagePopularityDto.setRatingPercentile(languagePopularity.getRatingPercentile());
        languagePopularityDto.setId(languagePopularity.getId());
        return languagePopularityDto;
    }

    @Override
    public LanguagePopularity dtoToEntity(LanguagePopularityDto languagePopularityDto) {
        LanguagePopularity languagePopularity = new LanguagePopularity();
        //languagePopularity.setLanguage(languagePopularityDto.getLanguageId());
        languagePopularity.setYear(languagePopularityDto.getYear());
        languagePopularity.setRatingPercentile(languagePopularityDto.getRatingPercentile());
        return languagePopularity;
    }



}