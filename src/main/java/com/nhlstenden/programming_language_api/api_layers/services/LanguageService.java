package com.nhlstenden.programming_language_api.api_layers.services;

import com.nhlstenden.programming_language_api.api_layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.data.models.*;
import com.nhlstenden.programming_language_api.data.transformers.LanguageTransformer;
import com.nhlstenden.programming_language_api.exceptions.*;
import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.xml.crypto.dsig.XMLObject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LanguageService {
    LanguageRepository repository;
    LanguageTransformer transformer;

    public LanguageService(LanguageRepository repository, LanguageTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    public List<LanguageDto> getAll(){
        final Collection<Language> languages = repository.getAll();
        return languages.stream().map(transformer::languageToLanguageDto)
                .collect(Collectors.toList());
    }

    public LanguageDto getOne(long id) throws ObjectNotFoundException {
        Language language = repository.getFromId(id);
        return transformer.languageToLanguageDto(language);
    }

    public void save(JSONObject jsonObject) {
        Language language = transformer.languageDtoToLanguage(transformer.jsonToLanguageDto(jsonObject));
        repository.save(language);
    }

    public void update(JSONObject jsonObject, long id) {
        Language language = transformer.languageDtoToLanguage(transformer.jsonToLanguageDto(jsonObject));
        repository.update(language, id);
    }

    public void save(XMLObject xmlObject) {
        throw new NotImplementedException();
    }

    public void update(XMLObject xmlObject, long id) {
        throw new NotImplementedException();
    }

    public void delete(long languageId) throws ObjectNotFoundException {
        repository.delete(languageId);
    }



}
