package com.nhlstenden.programming_language_api.layers.repositories;

import com.nhlstenden.programming_language_api.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Uses Javax.persistence to store Entity objects into a sql database
 * @param <Entity> Entity object that has the follows the format of the table it is associated with
 */
@Component
public abstract class GenericRepository <Entity> {
    @PersistenceContext(unitName = "PlaPersistenceUnit")
    EntityManager entityManager;

    public abstract List<Entity> getAll();

    public abstract Entity getFromId(long id) throws ObjectNotFoundException;

    @Transactional
    public void save(Entity dto){
        entityManager.persist(dto);
    }

    public abstract void update(Entity entity, long id) throws ObjectNotFoundException;

    @Transactional
    public void delete(long id) throws ObjectNotFoundException {
        Entity entity = this.getFromId(id);
        entityManager.remove(entity);
    }
}

