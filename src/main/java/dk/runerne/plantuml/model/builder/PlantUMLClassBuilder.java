package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.Mutability;
import dk.runerne.plantuml.model.type.PlantUMLClass;
import dk.runerne.plantuml.model.type.PlantUMLConstructor;

import java.util.ArrayList;
import java.util.List;

public class PlantUMLClassBuilder extends TypeBuilder<PlantUMLClassBuilder> {

    private Mutability mutability = Mutability.MUTABLE;
    private final List<PlantUMLConstructor> constructors = new ArrayList<>();

    public static PlantUMLClassBuilder newInstance(String name) {
        return new PlantUMLClassBuilder(name);
    }

    public PlantUMLClassBuilder isFinal() {
        this.mutability = Mutability.IMMUTABLE;
        return this;
    }

    public PlantUMLClassBuilder constructor(PlantUMLConstructorBuilder builder) {
        constructors.add(builder.build());
        return this;
    }

    public PlantUMLClass build() {
        return new PlantUMLClass(getName(), getAbstractionLevel(), getStaticness(), getAccessModifier(), mutability, getProperties(), constructors, getMethods());
    }

    private PlantUMLClassBuilder(String name) {
        super(name);
    }

}