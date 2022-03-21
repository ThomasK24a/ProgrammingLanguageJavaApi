package com.nhlstenden.programming_language_api.api_layers.services;

import com.nhlstenden.programming_language_api.api_layers.repositories.LanguageRepository;
import com.nhlstenden.programming_language_api.data.models.*;
import com.nhlstenden.programming_language_api.data.transformers.LanguageTransformer;
import com.nhlstenden.programming_language_api.exceptions.*;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LanguageService {
    LanguageRepository repository;
    LanguageTransformer transformer;

    public LanguageService(LanguageRepository repository, LanguageTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    public JSONArray getAll(){
        final Collection<Language> languages = repository.getAll();
        List<LanguageDto> languageDtoList = languages.stream().map(transformer::languageToLanguageDto)
                .collect(Collectors.toList());

        JSONArray languageList = new JSONArray();
        for (LanguageDto languageDto : languageDtoList){
            JSONObject jsonLanguageDto = transformer.languageDtoToJson(languageDto);
            validate(jsonLanguageDto);
            languageList.put(jsonLanguageDto);
        }

        return languageList;
    }

    public JSONObject getOne(long id) throws ObjectNotFoundException {
        Language language = repository.getFromId(id);
        JSONObject jsonObject = transformer.languageDtoToJson(transformer.languageToLanguageDto(language));
        validate(jsonObject);
        return jsonObject;
    }

    public void save(JSONObject jsonObject) {
        validate(jsonObject);
        Language language = transformer.languageDtoToLanguage(transformer.jsonToLanguageDto(jsonObject));
        repository.save(language);
    }

    public void update(JSONObject jsonObject, long id) throws ObjectNotFoundException {
        validate(jsonObject);
        Language language = transformer.languageDtoToLanguage(transformer.jsonToLanguageDto(jsonObject));
        repository.update(language, id);
    }

    public void delete(long languageId) throws ObjectNotFoundException {
        repository.delete(languageId);
    }

    private Schema getJsonLanguageSchema(){
        try (InputStream inputStream = this.getClass().getResourceAsStream(
                "/schemas/LanguageJsonSchema.json")) {
            JSONTokener tokener = new JSONTokener(Objects.requireNonNull(inputStream));
            JSONObject rawSchema = new JSONObject(tokener);
            return SchemaLoader.load(rawSchema);
        } catch (Exception ex) {
            throw new InvalidSchemaException("language");
        }
    }

    private void validate(JSONObject jsonObject){
        getJsonLanguageSchema().validate(jsonObject);
    }

}
