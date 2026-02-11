package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.relation.PlantUMLDependency;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import dk.runerne.plantuml.model.type.PlantUMLType;

public class PlantUMLDependencyBuilder extends PlantUMLRelationBuilder {

    public static PlantUMLDependencyBuilder newInstance(PlantUMLType from, PlantUMLType to) {
        return new PlantUMLDependencyBuilder(from, to);
    }

    public PlantUMLRelation build() {
        return new PlantUMLDependency(getFrom(), getFromText(), getTo(), getToText(), getName(), getLength());
    }

    private PlantUMLDependencyBuilder(PlantUMLType from, PlantUMLType to) {
        super(from, to);
    }

}