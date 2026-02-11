package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.PlantUMLConstructor;

public class PlantUMLConstructorBuilder extends CallPointBuilder<PlantUMLConstructorBuilder> {

    public static PlantUMLConstructorBuilder newInstance(String name) {
        return new PlantUMLConstructorBuilder(name);
    }

    public PlantUMLConstructor build() {
        return new PlantUMLConstructor(getAbstractionLevel(), getAccessModifier(), getParameters());
    }

    private PlantUMLConstructorBuilder(String name) {
        super(name);
    }

}