package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.relation.PlantUMLComposition;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import dk.runerne.plantuml.model.type.PlantUMLType;

public class PlantUMLCompositionBuilder extends PlantUMLRelationBuilder {

    public static PlantUMLCompositionBuilder newInstance(PlantUMLType from, PlantUMLType to) {
        return new PlantUMLCompositionBuilder(from, to);
    }

    public PlantUMLRelation build() {
        return new PlantUMLComposition(getFrom(), getFromText(), getTo(), getToText(), getName(), getLength());
    }

    private PlantUMLCompositionBuilder(PlantUMLType from, PlantUMLType to) {
        super(from, to);
    }

}