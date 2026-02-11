package dk.runerne.plantuml.model.relation;

import dk.runerne.plantuml.model.type.PlantUMLType;

public class PlantUMLAggregation extends AbstractPlantUMLRelation {

    public PlantUMLAggregation(PlantUMLType from, String fromText, PlantUMLType to, String toText, String name, int length) {
        super(from, fromText, to, toText, name, length);
    }

}