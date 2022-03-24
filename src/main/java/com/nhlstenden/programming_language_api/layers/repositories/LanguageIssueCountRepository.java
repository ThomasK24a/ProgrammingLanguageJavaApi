package com.nhlstenden.programming_language_api.layers.repositories;

import com.nhlstenden.programming_language_api.exceptions.ObjectNotFoundException;
import com.nhlstenden.programming_language_api.models.Language;
import com.nhlstenden.programming_language_api.models.LanguageIssueCount;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class LanguageIssueCountRepository extends GenericRepository<LanguageIssueCount> {

    public List<LanguageIssueCount> getAll() {
        TypedQuery<LanguageIssueCount> query = entityManager.createQuery("SELECT languageIssueCount FROM LanguageIssueCount languageIssueCount", LanguageIssueCount.class);
        return query.getResultList();
    }

    public LanguageIssueCount getFromId(long id) throws ObjectNotFoundException {
        LanguageIssueCount languageIssueCount = entityManager.find(LanguageIssueCount.class, id);
        if(languageIssueCount != null){
            return languageIssueCount;
        }else{
            throw new ObjectNotFoundException(id, "language issue count");
        }
    }

    @Transactional
    public void update(LanguageIssueCount languageIssueCount, long id) throws ObjectNotFoundException {
        LanguageIssueCount detachedLanguageIssueCount = getFromId(id);
        entityManager.detach(detachedLanguageIssueCount);
        detachedLanguageIssueCount.setLanguage(languageIssueCount.getLanguage());
        detachedLanguageIssueCount.setIssueCount(languageIssueCount.getIssueCount());
        detachedLanguageIssueCount.setQuarter(languageIssueCount.getQuarter());
        detachedLanguageIssueCount.setYear(languageIssueCount.getYear());
        entityManager.merge(detachedLanguageIssueCount);

    }
}

