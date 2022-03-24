package com.nhlstenden.programming_language_api.layers.transformers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhlstenden.programming_language_api.exceptions.TransformerErrorException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public abstract class GenericTransformer<Entity, DTO> {
    protected final ObjectMapper objectMapper;

    public GenericTransformer() {
        objectMapper = new ObjectMapper();
    }

    public DTO jsonToDTO(JSONObject jsonEntity){
        try{
            TypeReference<DTO> mapType= new TypeReference<>() {};;
            return objectMapper.readValue(String.valueOf(jsonEntity), mapType);
        }catch (Exception exception){
            throw new TransformerErrorException("json");
        }
    }

    public JSONObject dtoToJson(DTO dto) {
        try{
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonString);
        }catch(Exception ignored){
            throw new TransformerErrorException(dto.getClass().getSimpleName());
        }
    }

    public abstract DTO entityToDTO(Entity entity);

    public abstract Entity dtoToEntity(DTO dto);

    public abstract JSONObject DTOToJson(DTO dto);

}