package com.nhlstenden.programming_language_api.layers.transformers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nhlstenden.programming_language_api.exceptions.TransformerErrorException;
import com.nhlstenden.programming_language_api.models.LanguageIssueCount;
import com.nhlstenden.programming_language_api.models.LanguageIssueCountDto;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class LanguageIssueCountTransformer extends GenericTransformer<LanguageIssueCount, LanguageIssueCountDto> {

    public LanguageIssueCountTransformer() {
        super();
    }

    public LanguageIssueCountDto JsonToDTO(JSONObject jsonLanguageIssueCount){
        try{
            TypeReference<LanguageIssueCountDto> mapType= new TypeReference<>() {};;
            return objectMapper.readValue(String.valueOf(jsonLanguageIssueCount), mapType);
        }catch (Exception exception){
            throw new TransformerErrorException("json");
        }
    }

    public JSONObject DTOToJson(LanguageIssueCountDto languageIssueCountDto) {
        try{
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(languageIssueCountDto);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonString);
        }catch(Exception ignored){
            throw new TransformerErrorException("languageIssueCount");
        }
    }

    @Override
    public LanguageIssueCountDto entityToDTO(LanguageIssueCount languageIssueCount) {
        LanguageIssueCountDto languageIssueCountDto = new LanguageIssueCountDto();
        languageIssueCountDto.setLanguageId(languageIssueCount.getLanguage().getId());
        languageIssueCountDto.setIssueCount(languageIssueCount.getIssueCount());
        languageIssueCountDto.setQuarter(languageIssueCount.getQuarter());
        languageIssueCountDto.setYear(languageIssueCount.getYear());
        languageIssueCountDto.setId(languageIssueCount.getId());
        return languageIssueCountDto;
    }

    @Override
    public LanguageIssueCount dtoToEntity(LanguageIssueCountDto languageIssueCountDto) {
        LanguageIssueCount languageIssueCount = new LanguageIssueCount();
        //languageIssueCount.setLanguage(); TODO: Get language
        languageIssueCount.setIssueCount(languageIssueCountDto.getIssueCount());
        languageIssueCount.setQuarter(languageIssueCountDto.getQuarter());
        languageIssueCount.setYear(languageIssueCountDto.getYear());
        languageIssueCount.setId(languageIssueCountDto.getId());
        return languageIssueCount;
    }



}