package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.PlantUMLType;

abstract class AbstractRelationBuilder implements RelationBuilder {

    private final PlantUMLType from;
    private final PlantUMLType to;
    private int length = 2;

    public AbstractRelationBuilder length(int length) {
        this.length = length;
        return this;
    }

    @Override
    public PlantUMLType getFrom() {
        return from;
    }

    @Override
    public PlantUMLType getTo() {
        return to;
    }

    @Override
    public int getLength() {
        return length;
    }

    protected AbstractRelationBuilder(PlantUMLType from, PlantUMLType to) {
            this.from = from;
            this.to = to;
    }

}