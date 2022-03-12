package com.marukovich.second_project.validator.impl;

import com.marukovich.second_project.handler.DeviceErrorHandler;
import com.marukovich.second_project.validator.XmlXsdValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlXsdValidatorImpl implements XmlXsdValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final XmlXsdValidatorImpl instance = new XmlXsdValidatorImpl();

    private XmlXsdValidatorImpl(){}

    public static XmlXsdValidatorImpl getInstance(){
        return instance;
    }
    @Override
    public boolean validateXml(String fileName, String schemaName) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        fileName = "data/devices.xml";
        schemaName = "data/devices.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            //schema creation
            Schema schema = factory.newSchema(schemaLocation);
            //creating a schema-based validator
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            //document check
            validator.setErrorHandler(new DeviceErrorHandler());
            validator.validate(source);
        } catch (IOException | SAXException e) {
            logger.log(Level.INFO, "{} is not correct, or valid", fileName, e);
        }
        return false;
    }

}
