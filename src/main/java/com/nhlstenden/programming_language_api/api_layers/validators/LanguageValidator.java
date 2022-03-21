package com.nhlstenden.programming_language_api.api_layers.validators;

import com.nhlstenden.programming_language_api.exceptions.InvalidSchemaException;
import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import javax.xml.crypto.dsig.XMLObject;
import java.io.InputStream;
import java.util.Objects;

@Component
public class LanguageValidator {

    private Schema getJsonLanguageSchema(){
        try (InputStream inputStream = this.getClass().getResourceAsStream(
                "/schemas/LanguageJsonSchema.json")) {
            JSONTokener tokener = new JSONTokener(Objects.requireNonNull(inputStream));
            JSONObject rawSchema = new JSONObject(tokener);
            return SchemaLoader.load(rawSchema);
        } catch (Exception ex) {
            throw new InvalidSchemaException("language");
        }
    }

    public void validate(JSONObject jsonObject){
        getJsonLanguageSchema().validate(jsonObject);
    }

    private Schema getXmlLanguageSchema(){
        throw new UnsupportedOperationException();
    }

    public void validate(XMLObject xmlObject){
        getXmlLanguageSchema().validate(xmlObject);
    }
}
