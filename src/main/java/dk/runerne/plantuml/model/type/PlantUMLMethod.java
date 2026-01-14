package dk.runerne.plantuml.model.type;

import lombok.Getter;

import java.util.List;

@Getter
public class PlantUMLMethod extends AbstractPlantUMLCallPoint {

    private final String name;
    private final String returnTypeName;
    private final Staticness staticness;

    public PlantUMLMethod(
        String name,
        String returnTypeName,
        AbstractionLevel abstractionLevel,
        AccessModifier accessModifier,
        Staticness staticness,
        List<PlantUMLParameter> parameters
    ) {
        super(abstractionLevel, accessModifier, parameters);
        this.name = name;
        this.returnTypeName = returnTypeName;
        this.staticness = staticness;
    }

}