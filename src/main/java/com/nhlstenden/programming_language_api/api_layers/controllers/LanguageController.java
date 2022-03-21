package com.nhlstenden.programming_language_api.api_layers.controllers;

import com.nhlstenden.programming_language_api.api_layers.services.LanguageService;

import com.nhlstenden.programming_language_api.data.models.LanguageDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<JSONArray> getAllLanguages(){
        return new ResponseEntity<>(languageService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<JSONObject> getLanguage(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        return new ResponseEntity<>(languageService.getOne(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> updateLanguage(@PathVariable("id") String idString, @RequestBody JSONObject jsonObject) {
        int id = Integer.parseInt(idString);
        languageService.update(jsonObject, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> postLanguage(@RequestBody JSONObject jsonObject) {
        languageService.save(jsonObject);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Void> deleteLanguage(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        languageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
