package dk.runerne.plantuml.model.relation;

import dk.runerne.plantuml.model.type.PlantUMLType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
abstract class AbstractPlantUMLRelation implements PlantUMLRelation {

    private final PlantUMLType from;
    private final String fromText;
    private final PlantUMLType to;
    private final String toText;
    private final String name;
    private final int length;

}