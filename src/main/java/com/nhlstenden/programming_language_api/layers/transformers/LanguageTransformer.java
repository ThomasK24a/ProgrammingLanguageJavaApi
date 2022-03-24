package com.nhlstenden.programming_language_api.layers.transformers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import com.nhlstenden.programming_language_api.exceptions.TransformerErrorException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class LanguageTransformer extends GenericTransformer<Language, LanguageDto> {

    public LanguageTransformer() {
        super();
    }

    public LanguageDto JsonToDTO(JSONObject jsonLanguage){
        try{
            TypeReference<LanguageDto> mapType= new TypeReference<>() {};;
            return objectMapper.readValue(String.valueOf(jsonLanguage), mapType);
        }catch (Exception exception){
            throw new TransformerErrorException("json");
        }
    }

    public JSONObject DTOToJson(LanguageDto languageDto) {
        try{
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(languageDto);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonString);
        }catch(Exception ignored){
            throw new TransformerErrorException("language");
        }
    }

    @Override
    public LanguageDto entityToDTO(Language language) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLanguageName(language.getLanguageName());
        languageDto.setFileExtension(language.getFileExtension());
        languageDto.setHelloWorldProgram(language.getHelloWorldProgram());
        languageDto.setId(language.getId());
        return languageDto;
    }

    @Override
    public Language dtoToEntity(LanguageDto languageDto) {
        Language language = new Language();
        language.setLanguageName(languageDto.getLanguageName());
        language.setFileExtension(languageDto.getFileExtension());
        language.setHelloWorldProgram(languageDto.getHelloWorldProgram());
        return language;
    }



}