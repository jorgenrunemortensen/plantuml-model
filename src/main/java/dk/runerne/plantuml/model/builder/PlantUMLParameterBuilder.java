package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.Mutability;
import dk.runerne.plantuml.model.type.PlantUMLParameter;

public class PlantUMLParameterBuilder {

    private final String name;
    private final String typeName;
    private Mutability mutability = Mutability.MUTABLE;

    public static PlantUMLParameterBuilder newInstance(String name, String typeName) {
        return new PlantUMLParameterBuilder(name, typeName);
    }

    public PlantUMLParameterBuilder isFinal() {
        this.mutability = Mutability.IMMUTABLE;
        return this;
    }

    public PlantUMLParameter build() {
        return new PlantUMLParameter(name, typeName, mutability);
    }

    private PlantUMLParameterBuilder(String name, String typeName)
    {
        this.name = name;
        this.typeName = typeName;
    }

}