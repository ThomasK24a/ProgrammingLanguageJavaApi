package com.nhlstenden.programming_language_api.layers.validators;

import com.nhlstenden.programming_language_api.models.LanguagePopularity;
import org.springframework.stereotype.Component;

@Component
public class LanguagePopularityValidator extends GenericValidator{

    public LanguagePopularityValidator() {
        super("language popularity", "/schemas/PopularityLanguagesJsonSchema.json", "/schemas/PopularityLanguagesXmlSchema.xsd");
    }
}
