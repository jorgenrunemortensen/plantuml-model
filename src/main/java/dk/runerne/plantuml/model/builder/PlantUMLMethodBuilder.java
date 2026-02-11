package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.PlantUMLMethod;
import dk.runerne.plantuml.model.type.Staticness;

public class PlantUMLMethodBuilder extends CallPointBuilder<PlantUMLMethodBuilder> {

    private final String returnTypeName;
    private Staticness staticness = Staticness.NON_STATIC;

    public static PlantUMLMethodBuilder newInstance(String name, String returnTypeName) {
        return new PlantUMLMethodBuilder(name, returnTypeName);
    }

    public static PlantUMLMethodBuilder newInstance(String name) {
        return new PlantUMLMethodBuilder(name, null);
    }

    public PlantUMLMethodBuilder isStatic() {
        this.staticness = Staticness.STATIC;
        return this;
    }

    public PlantUMLMethod build() {
        return new PlantUMLMethod(name, returnTypeName, getAbstractionLevel(), getAccessModifier(), staticness, getParameters());
    }

    private PlantUMLMethodBuilder(String name, String returnTypeName) {
        super(name);
        this.returnTypeName = returnTypeName;
    }

}