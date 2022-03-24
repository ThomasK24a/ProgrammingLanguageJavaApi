package com.nhlstenden.programming_language_api.api_layers.validators;

import com.nhlstenden.programming_language_api.data.models.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageValidator extends GenericValidator<Language>{

    public LanguageValidator() {
        super("language", "/schemas/LanguageJsonSchema.json", "");
    }
}
