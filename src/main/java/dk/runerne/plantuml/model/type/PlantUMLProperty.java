package dk.runerne.plantuml.model.type;

import lombok.Data;

@Data
public class PlantUMLProperty {

    private final String name;
    private final String typeName;
    private final AbstractionLevel abstractionLevel;
    private final AccessModifier accessModifier;
    private final Mutability mutability;
    private final Staticness staticness;

}