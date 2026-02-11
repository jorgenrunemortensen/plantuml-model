package dk.runerne.plantuml.model.relation;

import dk.runerne.plantuml.model.type.PlantUMLType;

public interface PlantUMLRelation {

    PlantUMLType getFrom();
    String getFromText();

    PlantUMLType getTo();
    String getToText();

    String getName();

    int getLength();

}