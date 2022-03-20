package com.nhlstenden.programming_language_api.api_layers.repositories;

import com.nhlstenden.programming_language_api.exceptions.ObjectNotFoundException;
import com.nhlstenden.programming_language_api.data.models.Language;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class LanguageRepository {
    @PersistenceContext(unitName = "PlaPersistenceUnit")
    EntityManager entityManager;

    public List<Language> getAll() {
        TypedQuery<Language> query = entityManager.createQuery("SELECT language FROM Language language", Language.class);
        return query.getResultList();
    }

    public Language getFromId(long id) throws ObjectNotFoundException {
        Language language = entityManager.find(Language.class, id);
        if(language != null){
            return language;
        }else{
            throw new ObjectNotFoundException(id, "language");
        }
    }
    @Transactional
    public void save(Language language){
        entityManager.persist(language);
    }

    @Transactional
    public void update(Language language, long id) throws ObjectNotFoundException {
        Language detachedLanguage = getFromId(id);
        entityManager.detach(detachedLanguage);
        detachedLanguage.setLanguageName(language.getLanguageName());
        detachedLanguage.setFileExtension(language.getFileExtension());
        detachedLanguage.setHelloWorldProgram(language.getHelloWorldProgram());
        entityManager.merge(detachedLanguage);

    }

    @Transactional
    public void delete(long id) throws ObjectNotFoundException {
        Language language = this.getFromId(id);
        entityManager.remove(language);
    }
}

