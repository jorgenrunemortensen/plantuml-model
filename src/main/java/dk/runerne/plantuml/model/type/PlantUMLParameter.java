package dk.runerne.plantuml.model.type;

import lombok.Data;

@Data
public class PlantUMLParameter {

    private final String name;
    private final String typeName;
    private final Mutability mutability;

}