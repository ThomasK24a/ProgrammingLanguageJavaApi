package com.nhlstenden.programming_language_api.layers.controllers;

import com.nhlstenden.programming_language_api.layers.repositories.GenericRepository;
import com.nhlstenden.programming_language_api.layers.services.GenericService;
import com.nhlstenden.programming_language_api.layers.validators.GenericValidator;
import com.nhlstenden.programming_language_api.layers.transformers.GenericTransformer;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController <Service extends GenericService<Entity, DTO, Repository, Transformer>, Validator extends GenericValidator<Entity>, DTO, Entity, Repository extends GenericRepository<Entity>, Transformer extends GenericTransformer<Entity, DTO>>{
    private final Service service;
    private final Validator validator;

    public GenericController(Service service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<DTO>> getAllEntities(){
        List<DTO> returnObject = service.getAll();
        return ResponseEntity.ok(returnObject);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<DTO> getEntity(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> updateEntityJson(@PathVariable("id") String idString, @RequestBody String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        int id = Integer.parseInt(idString);
            validator.validate(jsonObject);

        service.update(jsonObject, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> postEntityJson(@RequestBody String jsonString) {
        System.out.println(jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        validator.validate(jsonObject);
        service.save(jsonObject);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Void> deleteEntity(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
