package dk.runerne.plantuml.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
abstract class AbstractPlantUMLType implements PlantUMLType {

    private final String name;
    private final AbstractionLevel abstractionLevel;
    private final Staticness staticness;
    private final AccessModifier accessModifier;
    private final List<PlantUMLProperty> properties;
    private final List<PlantUMLMethod> methods;

}