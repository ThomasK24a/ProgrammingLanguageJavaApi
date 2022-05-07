package com.nhlstenden.programming_language_api.layers.controllers;

import com.nhlstenden.programming_language_api.exceptions.InvalidIdException;
import com.nhlstenden.programming_language_api.layers.repositories.GenericRepository;
import com.nhlstenden.programming_language_api.layers.services.GenericService;
import com.nhlstenden.programming_language_api.layers.transformers.GenericTransformer;
import com.nhlstenden.programming_language_api.layers.validators.GenericValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.util.List;

/**
 * Creates endpoints for each CRUD operation (Create, Read, Update, Delete) of the given object
 * @param <Service> Service connects the controller with the rest of the application
 * @param <Validator> Validates the json and xml of the object using their respective schemas
 * @param <DTO> Model of object that is generally used in the business layer of the code,
 *             as it is easier to manipulate the object
 * @param <Entity> Model of object that uses javax persistence and is stored in the sql database, needs to implement
 *                javax.persistence entity annotation
 * @param <Repository> Repository that is used to communicate with the object's respective table in the database
 * @param <Transformer> Transforms the object between the json/xml string, dto model and entity model
 */
public abstract class GenericController <Service extends GenericService<Entity, DTO, Repository, Transformer>, Validator extends GenericValidator, DTO, Entity, Repository extends GenericRepository<Entity>, Transformer extends GenericTransformer<Entity, DTO>>{
    private final Service service;
    private final Validator validator;

    public GenericController(Service service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Get all values in the database", responseContainer = "List")
    public @ResponseBody ResponseEntity<List<DTO>> getAllEntities(){
        List<DTO> returnObject = service.getAll();
        return ResponseEntity.ok(returnObject);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Get a single value in the database based on id")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Entity not found") })
    public @ResponseBody ResponseEntity<DTO> getEntity(@PathVariable("id") String idString) {
        return new ResponseEntity<>(service.getOne(parseId(idString)), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace a single value in the database, accepts both xml and json")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied or invalid entity supplied"),
            @ApiResponse(code = 404, message = "Entity to replace not found") })
    public @ResponseBody ResponseEntity<Void> updateEntityJson(@PathVariable("id") String idString, @RequestBody String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        validator.validate(jsonObject);
        service.update(jsonObject, parseId(idString));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a single value to the database, accepts both xml and json")
    @ApiResponse(code = 400, message = "Invalid entity supplied")
    public @ResponseBody ResponseEntity<Void> postEntityJson(@RequestBody String jsonString) {
        System.out.println(jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        validator.validate(jsonObject);
        service.save(jsonObject);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Replace a single value in the database, accepts both xml and json")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied or invalid entity supplied"),
            @ApiResponse(code = 404, message = "Entity to replace not found") })
    public @ResponseBody ResponseEntity<Void> updateEntityXml(@PathVariable("id") String idString, @RequestBody String xmlString) throws SAXException {
        validator.validate(xmlString);
        service.update(xmlString, parseId(idString));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Add a single value to the database, accepts both xml and json")
    @ApiResponse(code = 400, message = "Invalid entity supplied")
    public @ResponseBody ResponseEntity<Void> postEntityXml(@RequestBody String xmlString) throws SAXException {
        validator.validate(xmlString);
        service.save(xmlString);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove a single value from the database")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Entity not found") })
    public @ResponseBody ResponseEntity<Void> deleteEntity(@PathVariable("id") String idString) {
        service.delete(parseId(idString));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private int parseId(String idString){
        int id = 0;
        try{
            id = Integer.parseInt(idString);
        }catch(Exception ignored){

        }
        if(id <= 0){
            throw new InvalidIdException();
        }
        return id;
    }
}
