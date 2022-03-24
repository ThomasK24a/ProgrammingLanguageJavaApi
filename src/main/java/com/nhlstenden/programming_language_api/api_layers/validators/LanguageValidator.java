package com.nhlstenden.programming_language_api.api_layers.validators;

import com.google.gson.JsonObject;
import com.nhlstenden.programming_language_api.exceptions.InvalidSchemaException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class LanguageValidator {

    private Schema getJsonLanguageSchema(){
        try (InputStream inputStream = this.getClass().getResourceAsStream(
                "/schemas/LanguageJsonSchema.json")) {
            JSONParser jsonParser = new JSONParser();
            JSONObject simpleJsonObject = (JSONObject) jsonParser.parse(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            org.json.JSONObject rawSchema = new org.json.JSONObject(simpleJsonObject.toJSONString());
            return SchemaLoader.load(rawSchema);
        } catch (Exception ex) {
            throw new InvalidSchemaException("language");
        }
    }

    public void validate(JsonObject jsonObject){
        getJsonLanguageSchema().validate(jsonObject);
    }

    private javax.xml.validation.Schema getXmlLanguageSchema(){
        try{
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            return factory.newSchema(new StreamSource(this.getClass().getResourceAsStream("/temperature.xsd")));
        } catch (SAXException e) {
            throw new InvalidSchemaException("language");
        }
    }

    public void validate(XMLObject xmlObject) throws SAXException {
        try{
            Validator validator = getXmlLanguageSchema().newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(xmlObject.toString().getBytes())));
        } catch (IOException e) {
            throw new InvalidSchemaException("language");
        }
    }
}
