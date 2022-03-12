package com.marukovich.second_project.builder;

import com.marukovich.second_project.builder.AbstractDeviceBuilder;
import com.marukovich.second_project.handler.DeviceErrorHandler;
import com.marukovich.second_project.handler.DeviceHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxDeviceBuilder extends AbstractDeviceBuilder {
    private static final Logger logger = LogManager.getLogger();
    private XMLReader reader;
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser saxParser;

    @Override
    public void buildDevices(String fileName) {
        File file = new File(fileName);
        try {
            saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            DeviceHandler handler = new DeviceHandler();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new DeviceErrorHandler());
            reader.parse(String.valueOf(file));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}
