package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.Mutability;
import dk.runerne.plantuml.model.type.PlantUMLInterface;

public class PlantUMLInterfaceBuilder extends TypeBuilder<PlantUMLInterfaceBuilder> {

    private Mutability mutability = Mutability.MUTABLE;

    public static PlantUMLInterfaceBuilder newInstance(String name) {
        return new PlantUMLInterfaceBuilder(name);
    }

    public PlantUMLInterfaceBuilder isFinal() {
        this.mutability = Mutability.IMMUTABLE;
        return this;
    }

    public PlantUMLInterface build() {
        return new PlantUMLInterface(getName(), getAbstractionLevel(), getStaticness(), getAccessModifier(), mutability, getProperties(), getMethods());
    }

    private PlantUMLInterfaceBuilder(String name) {
        super(name);
    }

}