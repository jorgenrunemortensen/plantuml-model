package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.PlantUMLConstructor;
import dk.runerne.plantuml.model.type.PlantUMLEnum;

import java.util.ArrayList;
import java.util.List;

public class PlantUMLEnumBuilder extends TypeBuilder<PlantUMLEnumBuilder> {

    private final List<String> values = new ArrayList<>();
    private final List<PlantUMLConstructor> constructors = new ArrayList<>();

    public static PlantUMLEnumBuilder newInstance(String name) {
        return new PlantUMLEnumBuilder(name);
    }

    public PlantUMLEnumBuilder value(String value) {
        values.add(value);
        return this;
    }

    public PlantUMLEnumBuilder constructor(PlantUMLConstructorBuilder builder) {
        constructors.add(builder.build());
        return this;
    }

    public PlantUMLEnum build() {
        return new PlantUMLEnum(getName(), getAbstractionLevel(), getStaticness(), getAccessModifier() , values, getProperties(), constructors, getMethods());
    }

    private PlantUMLEnumBuilder(String name) {
        super(name);
    }

}