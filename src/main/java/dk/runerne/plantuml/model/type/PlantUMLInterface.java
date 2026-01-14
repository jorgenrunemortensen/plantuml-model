package dk.runerne.plantuml.model.type;

import lombok.Getter;

import java.util.List;

@Getter
public class PlantUMLInterface extends AbstractPlantUMLType {

    private final Mutability mutability;

    public PlantUMLInterface(
        String name,
        AbstractionLevel abstractionLevel,
        Staticness staticness,
        AccessModifier accessModifier,
        Mutability mutability,
        List<PlantUMLProperty> properties,
        List<PlantUMLMethod> methods
    ) {
        super(name, abstractionLevel, staticness, accessModifier, properties, methods);
        this.mutability = mutability;
    }

}