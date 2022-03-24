package com.nhlstenden.programming_language_api.layers.repositories;

import com.nhlstenden.programming_language_api.exceptions.ObjectNotFoundException;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguagePopularity;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class LanguagePopularityRepository extends GenericRepository<LanguagePopularity> {

    public List<LanguagePopularity> getAll() {
        TypedQuery<LanguagePopularity> query = entityManager.createQuery("SELECT languagePopularity FROM LanguagePopularity languagePopularity", LanguagePopularity.class);
        return query.getResultList();
    }

    public LanguagePopularity getFromId(long id) throws ObjectNotFoundException {
        LanguagePopularity languagePopularity = entityManager.find(LanguagePopularity.class, id);
        if(languagePopularity != null){
            return languagePopularity;
        }else{
            throw new ObjectNotFoundException(id, "languagePopularity");
        }
    }

    @Transactional
    public void update(LanguagePopularity languagePopularity, long id) throws ObjectNotFoundException {
        LanguagePopularity detachedLanguagePopularity = getFromId(id);
        entityManager.detach(detachedLanguagePopularity);
        detachedLanguagePopularity.setLanguage(languagePopularity.getLanguage());
        detachedLanguagePopularity.setRatingPercentile(languagePopularity.getRatingPercentile());
        detachedLanguagePopularity.setYear(languagePopularity.getYear());
        entityManager.merge(detachedLanguagePopularity);

    }
}

