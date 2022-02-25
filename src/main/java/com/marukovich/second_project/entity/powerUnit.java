package com.marukovich.second_project.entity;

public class powerUnit extends AbstractDevice{

    private String powerUnitParameters;

    public powerUnit(){}

    public String getPowerUnitParameters() {
        return powerUnitParameters;
    }

    public void setPowerUnitParameters(String powerUnitParameters) {
        this.powerUnitParameters = powerUnitParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        powerUnit powerUnit = (powerUnit) o;

        return powerUnitParameters.equals(powerUnit.powerUnitParameters);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + powerUnitParameters.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("powerUnit{");
        sb.append(super.toString());
        sb.append("powerUnitParameters='").append(powerUnitParameters).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
