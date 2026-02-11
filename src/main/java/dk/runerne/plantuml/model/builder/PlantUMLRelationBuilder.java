package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.PlantUMLType;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import lombok.Getter;

@Getter
public abstract class PlantUMLRelationBuilder {

    private final PlantUMLType from;
    private String fromText;

    private final PlantUMLType to;
    private String toText;

    private String name;
    private int length = 2;

    protected PlantUMLRelationBuilder(PlantUMLType from, PlantUMLType to) {
        this.from = from;
        this.to = to;
    }

    public PlantUMLRelationBuilder fromText(String fromText) {
        this.fromText = fromText;
        return this;
    }

    public PlantUMLRelationBuilder toText(String toText) {
        this.toText = toText;
        return this;
    }

    public PlantUMLRelationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PlantUMLRelationBuilder length(int length) {
        this.length = length;
        return this;
    }

    public abstract PlantUMLRelation build();

}