package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.relation.PlantUMLImplementation;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import dk.runerne.plantuml.model.type.PlantUMLType;

public class PlantUMLImplementationBuilder extends PlantUMLRelationBuilder {

    public static PlantUMLImplementationBuilder newInstance(PlantUMLType from, PlantUMLType to) {
        return new PlantUMLImplementationBuilder(from, to);
    }

    public PlantUMLRelation build() {
        return new PlantUMLImplementation(getFrom(), getFromText(), getTo(), getToText(), getName(), getLength());
    }

    private PlantUMLImplementationBuilder(PlantUMLType from, PlantUMLType to) {
        super(from, to);
    }

}