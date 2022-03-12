package com.marukovich.second_project.builder;

import com.marukovich.second_project.entity.AbstractDevice;
import com.marukovich.second_project.entity.CentralProcessor;
import com.marukovich.second_project.entity.GraphicCard;
import com.marukovich.second_project.entity.Type;
import com.marukovich.second_project.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Set;

public class StaxDeviceBuilder extends AbstractDeviceBuilder {
    private static final Logger logger = LogManager.getLogger();

    public Set<AbstractDevice> getDevices() {
        return deviceSet;
    }

    @Override
    public void buildDevices(String fileName) throws CustomException {
        File file = new File(fileName);
        String name;
        try (InputStream input = new FileInputStream(file)) {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(DeviceXmlTag.GRAPHIC_CARD.getValue()) ||
                            name.equals(DeviceXmlTag.CENTRAL_PROCESSOR.getValue())) {
                        AbstractDevice device = buildDevice(reader);
                        deviceSet.add(device);
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File {} can't be read", file, e);
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "File {} can't be parsed", file, e);
        }
    }

    private AbstractDevice buildDevice(XMLStreamReader reader) throws XMLStreamException, CustomException {
        AbstractDevice device = reader.getLocalName().equals(GRAPHIC_CARD)
                ? new GraphicCard() : new CentralProcessor();
        device.setDeviceId(reader.getAttributeValue(null, DEVICE_ID));
        device.setTitle(reader.getAttributeValue(null, TITLE));
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> getStartElement(reader, device);
                case XMLStreamConstants.END_ELEMENT -> {
                    String name = reader.getLocalName();
                    if (name.equals(GRAPHIC_CARD) || name.equals(CENTRAL_PROCESSOR)) {
                        return device;
                    }
                }
            }
        }
        logger.log(Level.ERROR, "Parsing error. Unknown element in xml file");
        throw new CustomException("Parsing error. Unknown element in xml file");
    }

    private void getStartElement(XMLStreamReader reader, AbstractDevice device) throws CustomException, XMLStreamException {
        String name = reader.getLocalName();
        String data = getTextFromXmlReader(reader);
        switch (name) {
            case NAME -> device.setName(data);
            case ORIGIN -> device.setOrigin(data);
            case PRICE -> device.setPrice(BigDecimal.valueOf(Double.parseDouble(data)));
            case TYPE -> getTypeFromXml(reader, device.getType());
            case CRITICAL -> device.setCritical(data);
            default -> {
                logger.log(Level.ERROR, "Unsupported tag: {}", name);
                throw new CustomException("Unsupported tag: {} " + name);
            }
        }
    }

    private void getTypeFromXml(XMLStreamReader reader, Type type) throws XMLStreamException {
        while (reader.hasNext()) {
            int temp = reader.next();
            String name = reader.getLocalName();
            String data = getTextFromXmlReader(reader);
            switch (temp) {
                case XMLStreamConstants.START_ELEMENT -> {
                    switch (name) {
                        case IS_PERIPHERY -> type.setIsPeriphery(data);
                        case ENERGY_CONSUMPTION -> type.setEnergyConsumption(Integer.parseInt(data));
                        case NUMBERS_OF_COOLERS -> type.setNumbersOfCoolers(Integer.parseInt(data));
                        case COMPONENT_GROUP -> type.setComponentGroup(data);
                        case PORTS -> type.setPorts(data);
                        default -> logger.log(Level.ERROR, "Unsupported tag: {}", name);
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    if (name.equals(PORTS)) {
                        return;
                    }
                }
                default -> logger.log(Level.ERROR, "Unsupported tag: {}", name);
            }
        }

    }

    private String getTextFromXmlReader(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
