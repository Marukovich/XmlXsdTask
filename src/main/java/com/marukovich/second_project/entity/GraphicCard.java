package com.marukovich.second_project.entity;

public class GraphicCard extends AbstractDevice{
    private String graphicCardParameters;

    public GraphicCard(){}

    public String getGraphicCardParameters() {
        return graphicCardParameters;
    }

    public void setGraphicCardParameters(String graphicCardParameters) {
        this.graphicCardParameters = graphicCardParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GraphicCard that = (GraphicCard) o;

        return graphicCardParameters.equals(that.graphicCardParameters);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + graphicCardParameters.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("graphicCard{");
        sb.append(super.toString());
        sb.append("graphicCardParameters='").append(graphicCardParameters).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
