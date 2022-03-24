package com.nhlstenden.programming_language_api.api_layers.controllers;

import com.nhlstenden.programming_language_api.api_layers.services.LanguageService;
import com.nhlstenden.programming_language_api.api_layers.validators.LanguageValidator;
import com.nhlstenden.programming_language_api.data.models.LanguageDto;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "languages")
public class LanguageController {
    private final LanguageService languageService;
    private final LanguageValidator languageValidator;

    public LanguageController(LanguageService languageService, LanguageValidator languageValidator) {
        this.languageService = languageService;
        this.languageValidator = languageValidator;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<LanguageDto>> getAllLanguages(){
        List<LanguageDto> returnObject = languageService.getAll();
        return ResponseEntity.ok(returnObject);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<LanguageDto> getLanguage(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        return new ResponseEntity<>(languageService.getOne(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> updateLanguageJson(@PathVariable("id") String idString, @RequestBody String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        int id = Integer.parseInt(idString);
            languageValidator.validate(jsonObject);

        languageService.update(jsonObject, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> postLanguageJson(@RequestBody String jsonString) {
        System.out.println(jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        languageValidator.validate(jsonObject);
        languageService.save(jsonObject);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Void> deleteLanguage(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        languageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public JSONObject parseJsonString(String jsonString){
        try{
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonString);
        } catch (ParseException e) {
            return new JSONObject();
        }
    }
}
