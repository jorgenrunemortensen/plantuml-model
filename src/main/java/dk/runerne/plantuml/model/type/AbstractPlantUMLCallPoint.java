package dk.runerne.plantuml.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
abstract class AbstractPlantUMLCallPoint implements PlantUMLCallPoint {

    private final AbstractionLevel abstractionLevel;
    private final AccessModifier accessModifier;
    private final List<PlantUMLParameter> parameters;

}