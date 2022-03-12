package com.marukovich.second_project.handler;

import com.marukovich.second_project.builder.DeviceXmlTag;
import com.marukovich.second_project.entity.AbstractDevice;
import com.marukovich.second_project.entity.CentralProcessor;
import com.marukovich.second_project.entity.GraphicCard;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static com.marukovich.second_project.builder.DeviceXmlTag.*;

public class DeviceHandler extends DefaultHandler {
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    private Set<AbstractDevice> devices;
    private EnumSet<DeviceXmlTag> enumDeviceSet;
    private AbstractDevice device;
    private DeviceXmlTag deviceTag;

    public DeviceHandler() {
        devices = new HashSet<>();
        enumDeviceSet = EnumSet.range(NAME, CRITICAL);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String graphicCardTag = GRAPHIC_CARD.getValue();
        String centralProcessorTag = CENTRAL_PROCESSOR.getValue();
        if (graphicCardTag.equals(qName) || centralProcessorTag.equals(qName)) {
            device = graphicCardTag.equals(qName) ? new GraphicCard() : new CentralProcessor();
            device.setDeviceId(attributes.getValue(DEVICE_ID.getValue()));
            device.setTitle(attributes.getValue(TITLE.getValue()));
        } else {
            DeviceXmlTag tempTag = valueOf(qName.toUpperCase().replace(HYPHEN,UNDERSCORE));
            if (enumDeviceSet.contains(tempTag)){
                deviceTag = tempTag;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (deviceTag != null) {
            switch (deviceTag) {
                case NAME -> device.setName(data);
                case ORIGIN -> device.setOrigin(data);
                case PRICE -> device.setPrice(BigDecimal.valueOf(Double.parseDouble(data)));
                case IS_PERIPHERY -> device.getType().setIsPeriphery(data);
                case ENERGY_CONSUMPTION -> device.getType().setEnergyConsumption(Integer.parseInt(data));
                case NUMBERS_OF_COOLERS -> device.getType().setNumbersOfCoolers(Integer.parseInt(data));
                case COMPONENT_GROUP -> device.getType().setComponentGroup(data);
                case PORTS -> device.getType().setPorts(data);
                case CRITICAL -> device.setCritical(data);

            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String graphicCardTag = GRAPHIC_CARD.getValue();
        String centralProcessorTag = CENTRAL_PROCESSOR.getValue();
        if (graphicCardTag.equals(qName) || centralProcessorTag.equals(qName)){
            devices.add(device);
        }
    }

    public Set<AbstractDevice> getDevices(){
        return devices;
    }
}
