package com.nhlstenden.programming_language_api.layers.validators;

import com.nhlstenden.programming_language_api.models.LanguageIssueCount;
import org.springframework.stereotype.Component;

@Component
public class LanguageIssueCountValidator extends GenericValidator<LanguageIssueCount>{

    public LanguageIssueCountValidator() {
        super("language issue count", "/schemas/GithubIssuesJsonSchema.json", "/schemas/GithubIssuesXmlSchema.xsd");
    }
}
