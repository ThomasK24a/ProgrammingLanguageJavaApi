package com.nhlstenden.programming_language_api.layers.validators;

import com.nhlstenden.programming_language_api.models.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageValidator extends GenericValidator<Language>{

    public LanguageValidator() {
        super("language", "/schemas/LanguageJsonSchema.json", "/schemas/LanguageXmlSchema.xsd");
    }
}
