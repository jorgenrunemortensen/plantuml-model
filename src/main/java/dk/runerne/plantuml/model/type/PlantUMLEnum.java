package dk.runerne.plantuml.model.type;

import lombok.Getter;

import java.util.List;

@Getter
public class PlantUMLEnum extends AbstractPlantUMLType {

    private final List<String> values;
    private final List<PlantUMLConstructor> constructors;

    public PlantUMLEnum(
        String name,
        AbstractionLevel abstractionLevel,
        Staticness staticness,
        AccessModifier accessModifier,
        List<String> values,
        List<PlantUMLProperty> properties,
        List<PlantUMLConstructor> constructors,
        List<PlantUMLMethod> methods
    ) {
        super(name, abstractionLevel, staticness, accessModifier, properties, methods);
        this.values = values;
        this.constructors = constructors;
    }

}