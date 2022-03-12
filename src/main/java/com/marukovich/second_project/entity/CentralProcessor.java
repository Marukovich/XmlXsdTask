package com.marukovich.second_project.entity;

public class CentralProcessor extends AbstractDevice{

    private String centralProcessorParameters;

    public CentralProcessor(){}

    public String getCentralProcessorParameters() {
        return centralProcessorParameters;
    }

    public void setCentralProcessorParameters(String centralProcessorParameters) {
        this.centralProcessorParameters = centralProcessorParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CentralProcessor that = (CentralProcessor) o;

        return centralProcessorParameters.equals(that.centralProcessorParameters);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + centralProcessorParameters.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("centralProcessor{");
        sb.append(super.toString());
        sb.append("centralProcessorParameters='").append(centralProcessorParameters).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
