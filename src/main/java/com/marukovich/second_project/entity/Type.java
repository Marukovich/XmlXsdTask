package com.marukovich.second_project.entity;

public class Type {

    private String isPeriphery;
    private int energyConsumption;
    private int numbersOfCoolers;
    private String componentGroup;
    private String ports;

    public Type(String isPeriphery, int energyConsumption, int numbersOfCoolers, String componentGroup, String ports) {
        this.isPeriphery = isPeriphery;
        this.energyConsumption = energyConsumption;
        this.numbersOfCoolers = numbersOfCoolers;
        this.componentGroup = componentGroup;
        this.ports = ports;
    }

    public Type(){}

    public String getIsPeriphery() {
        return isPeriphery;
    }

    public void setIsPeriphery(String isPeriphery) {
        this.isPeriphery = isPeriphery;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public int getNumbersOfCoolers() {
        return numbersOfCoolers;
    }

    public void setNumbersOfCoolers(int numbersOfCoolers) {
        this.numbersOfCoolers = numbersOfCoolers;
    }

    public String getComponentGroup(){return componentGroup;}

    public void setComponentGroup(String componentGroup){this.componentGroup = componentGroup;}

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        if (energyConsumption != type.energyConsumption) return false;
        if (numbersOfCoolers != type.numbersOfCoolers) return false;
        if (!isPeriphery.equals(type.isPeriphery)) return false;
        if (!componentGroup.equals(type.componentGroup)) return false;
        return ports.equals(type.ports);
    }

    @Override
    public int hashCode() {
        int result = isPeriphery.hashCode();
        result = 31 * result + energyConsumption;
        result = 31 * result + numbersOfCoolers;
        result = 31 * result + componentGroup.hashCode();
        result = 31 * result + ports.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Type{");
        sb.append("isPeriphery='").append(isPeriphery).append('\'');
        sb.append(", energyConsumption=").append(energyConsumption);
        sb.append(", numbersOfCoolers=").append(numbersOfCoolers);
        sb.append(", componentGroup=").append(componentGroup);
        sb.append(", ports='").append(ports).append('\'');
        sb.append(super.toString()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
