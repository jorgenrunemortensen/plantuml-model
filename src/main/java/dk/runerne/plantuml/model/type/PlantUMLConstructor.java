package dk.runerne.plantuml.model.type;

import lombok.Getter;

import java.util.List;

@Getter
public class PlantUMLConstructor extends AbstractPlantUMLCallPoint {

    public PlantUMLConstructor(
        AbstractionLevel abstractionLevel,
        AccessModifier accessModifier,
        List<PlantUMLParameter> parameters
    ) {
        super(abstractionLevel, accessModifier, parameters);
    }
}