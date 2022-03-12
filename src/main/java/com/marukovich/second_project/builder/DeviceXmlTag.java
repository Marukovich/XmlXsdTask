package com.marukovich.second_project.builder;

public enum DeviceXmlTag {
    DEVICES("devices"),
    DEVICE_ID("device-id"),
    NAME("name"),
    ORIGIN("origin"),
    PRICE("price"),
    TYPE("type"),
    IS_PERIPHERY("is-periphery"),
    ENERGY_CONSUMPTION("energy-consumption"),
    NUMBERS_OF_COOLERS("numbers-of-coolers"),
    COMPONENT_GROUP("component-group"),
    PORTS("ports"),
    CRITICAL("critical"),
    GRAPHIC_CARD("graphic-card"),
    CENTRAL_PROCESSOR("central-processor"),
    TITLE("title");

    private String value;

    DeviceXmlTag(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
