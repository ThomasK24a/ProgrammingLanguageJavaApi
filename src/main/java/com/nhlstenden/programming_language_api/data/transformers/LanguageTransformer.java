package com.nhlstenden.programming_language_api.data.transformers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhlstenden.programming_language_api.data.models.Language;
import com.nhlstenden.programming_language_api.data.models.LanguageDto;
import com.nhlstenden.programming_language_api.exceptions.TransformerErrorException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

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
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(languageDto);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonString);
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


}