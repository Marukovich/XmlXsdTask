package com.marukovich.second_project.builder;

import com.marukovich.second_project.entity.AbstractDevice;
import com.marukovich.second_project.entity.CentralProcessor;
import com.marukovich.second_project.entity.GraphicCard;
import com.marukovich.second_project.entity.Type;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class DomDeviceBuilder extends AbstractDeviceBuilder {

    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilderFactory factory;
    private DocumentBuilder documentBuilder;

    @Override
    public void buildDevices(String fileName) {
        File file = new File(fileName);

        try {
            factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            NodeList deviceList = document.getElementsByTagName(GRAPHIC_CARD);
            for (int i = 0; i < deviceList.getLength(); i++) {
                Node node = deviceList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element deviceElement = (Element) node;
                    AbstractDevice device = buildDevices(deviceElement);
                    deviceSet.add(device);
                }
            }

            deviceList = document.getElementsByTagName(CENTRAL_PROCESSOR);
            for (int i = 0; i < deviceList.getLength(); i++) {
                Element deviceElement = (Element) deviceList.item(i);
                AbstractDevice device = buildDevices(deviceElement);
            }

        } catch (ParserConfigurationException exception) {
            logger.log(Level.ERROR, "File was not parsed!", exception);
            exception.printStackTrace();
        } catch (IOException exception) {
            logger.log(Level.ERROR, "File was not read!", exception);
        } catch (SAXException exception) {
            logger.log(Level.ERROR, "File was not parsed!", exception);
        }
    }

    public AbstractDevice buildDevices(Element element) {
        AbstractDevice device = element.getTagName().equals(GRAPHIC_CARD)
                ? new GraphicCard() : new CentralProcessor();
        String data = element.getAttribute(DEVICE_ID);
        device.setDeviceId(data);
        data = element.getAttribute(TITLE);
        device.setTitle(data);
        data = element.getAttribute(NAME);
        device.setName(data);
        data = element.getAttribute(ORIGIN);
        device.setOrigin(data);
        data = element.getAttribute(PRICE);
        device.setPrice(BigDecimal.valueOf(Double.parseDouble(data)));
        data = element.getAttribute(CRITICAL);
        device.setCritical(data);

        Type type = device.getType();
        data = getElementTextContent(element, IS_PERIPHERY);
        type.setIsPeriphery(data);
        data = getElementTextContent(element, ENERGY_CONSUMPTION);
        type.setEnergyConsumption(Integer.parseInt(data));
        data = getElementTextContent(element, NUMBERS_OF_COOLERS);
        type.setNumbersOfCoolers(Integer.parseInt(data));
        data = getElementTextContent(element, COMPONENT_GROUP);
        type.setComponentGroup(data);
        data = getElementTextContent(element, PORTS);
        type.setPorts(data);
        return device;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
