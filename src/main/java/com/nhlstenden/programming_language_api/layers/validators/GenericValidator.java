package com.nhlstenden.programming_language_api.layers.validators;

import com.nhlstenden.programming_language_api.exceptions.InvalidSchemaException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
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

public abstract class GenericValidator<Entity> {

    private final String entityName;
    private final String jsonSchemaUrl;
    private final String xmlSchemaUrl;

    public GenericValidator(String entityName, String jsonSchemaUrl, String xmlSchemaUrl) {
        this.entityName = entityName;
        this.jsonSchemaUrl = jsonSchemaUrl;
        this.xmlSchemaUrl = xmlSchemaUrl;
    }


    private Schema getJsonSchema() {
        try (InputStream inputStream = this.getClass().getResourceAsStream(
                jsonSchemaUrl)) {
            JSONParser jsonParser = new JSONParser();
            org.json.simple.JSONObject simpleJsonObject = (org.json.simple.JSONObject) jsonParser.parse(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            org.json.JSONObject rawSchema = new org.json.JSONObject(simpleJsonObject.toJSONString());
            return SchemaLoader.load(rawSchema);
        } catch (Exception ex) {
            throw new InvalidSchemaException(entityName);
        }
    }

    public void validate(JSONObject jsonObject){
        getJsonSchema().validate(jsonObject);
    }

    private javax.xml.validation.Schema getXmlSchema(){
            try{
                SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                return factory.newSchema(new StreamSource(this.getClass().getResourceAsStream(xmlSchemaUrl)));
            } catch (SAXException e) {
                //TODO: replace with custom runtime exception with info about schema violation
                throw new InvalidSchemaException(entityName);
            }
        }

    public void validate(String xmlObject) throws SAXException {
        try{
            Validator validator = getXmlSchema().newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(xmlObject.getBytes())));
        } catch (IOException e) {
            throw new InvalidSchemaException(entityName);
        }
    }
}
