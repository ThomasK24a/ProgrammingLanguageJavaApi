package com.nhlstenden.programming_language_api.layers.transformers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nhlstenden.programming_language_api.exceptions.TransformerErrorException;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageDto;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class LanguageTransformer extends GenericTransformer<Language, LanguageDto> {

    public LanguageTransformer() {
        super(LanguageDto.class);
    }

    @Override
    public LanguageDto jsonToDTO(JSONObject jsonEntity) {
        try{
            TypeReference<LanguageDto> mapType= new TypeReference<>() {};;
            return objectMapper.readValue(String.valueOf(jsonEntity), mapType);
        }catch (Exception exception){
            throw new TransformerErrorException("json");
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