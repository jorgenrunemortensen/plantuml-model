package dk.runerne.plantuml.model.type;

import lombok.Getter;

import java.util.List;

@Getter
public class PlantUMLClass extends AbstractPlantUMLType {

    private final Mutability mutability;
    private final List<PlantUMLConstructor> constructors;

    public PlantUMLClass(
        String name,
        AbstractionLevel abstractionLevel,
        Staticness staticness,
        AccessModifier accessModifier,
        Mutability mutability,
        List<PlantUMLProperty> properties,
        List<PlantUMLConstructor> constructors,
        List<PlantUMLMethod> methods
    ) {
        super(name, abstractionLevel, staticness, accessModifier, properties, methods);
        this.mutability = mutability;
        this.constructors = constructors;
    }

}