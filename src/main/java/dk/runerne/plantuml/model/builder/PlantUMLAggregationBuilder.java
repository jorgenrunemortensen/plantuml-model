package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.relation.PlantUMLAggregation;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import dk.runerne.plantuml.model.type.PlantUMLType;

public class PlantUMLAggregationBuilder extends PlantUMLRelationBuilder {

    public static PlantUMLAggregationBuilder newInstance(PlantUMLType from, PlantUMLType to) {
        return new PlantUMLAggregationBuilder(from, to);
    }

    public PlantUMLRelation build() {
        return new PlantUMLAggregation(getFrom(), getFromText(), getTo(), getToText(), getName(), getLength());
    }

    private PlantUMLAggregationBuilder(PlantUMLType from, PlantUMLType to) {
        super(from, to);
    }

}