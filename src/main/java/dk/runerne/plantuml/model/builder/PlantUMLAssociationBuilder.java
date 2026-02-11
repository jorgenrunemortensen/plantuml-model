package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.PlantUMLType;
import dk.runerne.plantuml.model.relation.PlantUMLAssociation;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;

public class PlantUMLAssociationBuilder extends PlantUMLRelationBuilder {

    public static PlantUMLAssociationBuilder newInstance(PlantUMLType from, PlantUMLType to) {
        return new PlantUMLAssociationBuilder(from, to);
    }

    public PlantUMLRelation build() {
        return new PlantUMLAssociation(getFrom(), getFromText(), getTo(), getToText(), getName(), getLength());
    }

    private PlantUMLAssociationBuilder(PlantUMLType from, PlantUMLType to) {
        super(from, to);
    }

}