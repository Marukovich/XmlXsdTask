package com.marukovich.second_project.builder;

import com.marukovich.second_project.entity.AbstractDevice;
import com.marukovich.second_project.exception.CustomException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDeviceBuilder {
    public static final String DEVICES = "devices";
    public static final String DEVICE_ID = "device-id";
    public static final String NAME = "name";
    public static final String ORIGIN = "origin";
    public static final String PRICE = "price";
    public static final String TYPE = "type";
    public static final String IS_PERIPHERY = "is-periphery";
    public static final String ENERGY_CONSUMPTION = "energy-consumption";
    public static final String NUMBERS_OF_COOLERS = "numbers-of-coolers";
    public static final String COMPONENT_GROUP = "component_group";
    public static final String PORTS = "ports";
    public static final String CRITICAL = "critical";
    public static final String GRAPHIC_CARD = "graphic-card";
    public static final String CENTRAL_PROCESSOR = "central-processor";
    public static final String TITLE = "title";

    protected Set<AbstractDevice> deviceSet;

    protected AbstractDeviceBuilder() {
        deviceSet = new HashSet<>();
    }

    protected AbstractDeviceBuilder(Set<AbstractDevice> devices){
        this.deviceSet = new HashSet<>(devices);
    }

    public Set<AbstractDevice> getDevices(){
        return new HashSet<>(deviceSet);
    }

    public abstract void buildDevices(String fileName) throws CustomException;
}
