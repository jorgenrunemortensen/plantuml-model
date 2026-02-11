package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.relation.PlantUMLInheritance;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import dk.runerne.plantuml.model.type.PlantUMLType;

public class PlantUMLInheritanceBuilder extends PlantUMLRelationBuilder {

    public static PlantUMLInheritanceBuilder newInstance(PlantUMLType from, PlantUMLType to) {
        return new PlantUMLInheritanceBuilder(from, to);
    }

    public PlantUMLRelation build() {
        return new PlantUMLInheritance(getFrom(), getFromText(), getTo(), getToText(), getName(), getLength());
    }

    private PlantUMLInheritanceBuilder(PlantUMLType from, PlantUMLType to) {
        super(from, to);
    }

}