package com.nhlstenden.programming_language_api.data.transformers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhlstenden.programming_language_api.data.models.Language;
import com.nhlstenden.programming_language_api.data.models.LanguageDto;
import com.nhlstenden.programming_language_api.exceptions.TransformerErrorException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageTransformer {
    private final ObjectMapper objectMapper;

    public LanguageTransformer() {
        objectMapper = new ObjectMapper();
    }

    public LanguageDto jsonToLanguageDto(JSONObject jsonLanguage){
        try{
            TypeReference<LanguageDto> mapType= new TypeReference<>() {};;
            return objectMapper.readValue(String.valueOf(jsonLanguage), mapType);
        }catch (Exception exception){
            throw new TransformerErrorException("json");
        }
    }

    public JSONObject languageDtoToJson(LanguageDto languageDto) {
        try{
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(languageDto);
            return new JSONObject(json);
        }catch(Exception ignored){
            throw new TransformerErrorException("language");
        }
    }

    public LanguageDto languageToLanguageDto(Language language) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLanguageName(language.getLanguageName());
        languageDto.setFileExtension(language.getFileExtension());
        languageDto.setHelloWorldProgram(language.getHelloWorldProgram());
        languageDto.setId(language.getId());
        return languageDto;
    }

    public Language languageDtoToLanguage(LanguageDto languageDto) {
        Language language = new Language();
        language.setLanguageName(languageDto.getLanguageName());
        language.setFileExtension(languageDto.getFileExtension());
        language.setHelloWorldProgram(languageDto.getHelloWorldProgram());
        return language;
    }

    public JSONObject languageDtoListToJson(List<LanguageDto> languageDtoList){
        try{
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(languageDtoList);
            return new JSONObject(json);
        }catch(Exception ignored){
            throw new TransformerErrorException("language");
        }
    }

    public List<LanguageDto> jsonToLanguageDtoList(JSONObject jsonObject){
        try{
            TypeReference<List<LanguageDto>> mapType= new TypeReference<>() {};;
            return objectMapper.readValue(String.valueOf(jsonObject), mapType);
        }catch (Exception exception){
            throw new TransformerErrorException("json");
        }
    }
}