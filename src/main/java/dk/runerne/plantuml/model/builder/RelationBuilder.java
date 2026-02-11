package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.PlantUMLType;

public interface RelationBuilder {

    PlantUMLType getFrom();
    PlantUMLType getTo();
    int getLength();

}