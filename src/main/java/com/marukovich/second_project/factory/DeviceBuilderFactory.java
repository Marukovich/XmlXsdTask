package com.marukovich.second_project.factory;

import com.marukovich.second_project.builder.AbstractDeviceBuilder;
import com.marukovich.second_project.builder.DomDeviceBuilder;
import com.marukovich.second_project.builder.SaxDeviceBuilder;
import com.marukovich.second_project.builder.StaxDeviceBuilder;
import com.marukovich.second_project.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeviceBuilderFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final DeviceBuilderFactory instance = new DeviceBuilderFactory();
    private static final String REGEX = "^\\w{3,4}$";

    private static final String SAX = "SAX";
    private static final String STAX = "STAX";
    private static final String DOM = "DOM";

    private DeviceBuilderFactory() {
    }

    public static DeviceBuilderFactory getInstance() {
        return instance;
    }

    public AbstractDeviceBuilder deviceBuilder(String deviceBuilderTypeName) throws CustomException {
        if (deviceBuilderTypeName == null || !deviceBuilderTypeName.matches(REGEX)) {
            logger.log(Level.ERROR, "deviceBuilderTypeName is null or invalid");
            throw new CustomException("deviceBuilderTypeName is null or invalid");
        }
        String type = (deviceBuilderTypeName.toUpperCase());
        switch (type) {
            case SAX -> {
                return new SaxDeviceBuilder();
            }
            case STAX -> {
                return new StaxDeviceBuilder();
            }
            case DOM -> {
                return new DomDeviceBuilder();
            }
            default -> {
                logger.log(Level.ERROR,"Wrong builder type: {} or type is unsupported",  type);
                throw new CustomException("Wrong builder type: " + type + " or type is unsupported");
            }
        }
    }
}
