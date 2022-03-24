package com.nhlstenden.programming_language_api.layers.services;

import com.nhlstenden.programming_language_api.exceptions.ObjectNotFoundException;
import com.nhlstenden.programming_language_api.layers.repositories.GenericRepository;
import com.nhlstenden.programming_language_api.layers.transformers.GenericTransformer;
import org.json.JSONObject;

import javax.xml.crypto.dsig.XMLObject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericService<Entity, DTO, Repository extends GenericRepository<Entity>, Transformer extends GenericTransformer<Entity, DTO>>{
    Repository repository;
    Transformer transformer;

    public GenericService(Repository repository, Transformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    public List<DTO> getAll(){
        final Collection<Entity> entities = repository.getAll();
        return entities.stream().map(transformer::entityToDTO)
                .collect(Collectors.toList());
    }

    public DTO getOne(long id) throws ObjectNotFoundException {
        Entity entity = repository.getFromId(id);
        return transformer.entityToDTO(entity);
    }

    public void save(JSONObject jsonObject) {
        Entity entity = transformer.dtoToEntity(transformer.jsonToDTO(jsonObject));
        repository.save(entity);
    }

    public void update(JSONObject jsonObject, long id) {
        Entity entity = transformer.dtoToEntity(transformer.jsonToDTO(jsonObject));
        repository.update(entity, id);
    }

    public void save(XMLObject xmlObject) {
        throw new UnsupportedOperationException();
    }

    public void update(XMLObject xmlObject, long id) {
        throw new UnsupportedOperationException();
    }

    public void delete(long id) throws ObjectNotFoundException {
        repository.delete(id);
    }



}
