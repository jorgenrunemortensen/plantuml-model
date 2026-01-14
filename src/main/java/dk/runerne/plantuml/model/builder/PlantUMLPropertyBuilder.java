package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.AbstractionLevel;
import dk.runerne.plantuml.model.type.AccessModifier;
import dk.runerne.plantuml.model.type.Mutability;
import dk.runerne.plantuml.model.type.PlantUMLProperty;
import dk.runerne.plantuml.model.type.Staticness;

public class PlantUMLPropertyBuilder {

    private final String name;
    private AbstractionLevel abstractionLevel = AbstractionLevel.CONCRETE;
    private AccessModifier accessModifier;
    private Mutability mutability = Mutability.MUTABLE;
    private Staticness staticness = Staticness.NON_STATIC;
    private final String typeName;

    public static PlantUMLPropertyBuilder newInstance(String name, String typeName) {
        return new PlantUMLPropertyBuilder(name, typeName);
    }

    public PlantUMLPropertyBuilder accessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
        return this;
    }

    public PlantUMLPropertyBuilder isAbstract() {
        this.abstractionLevel = AbstractionLevel.ABSTRACT;
        return this;
    }

    public PlantUMLPropertyBuilder isPublic() {
        this.accessModifier = AccessModifier.PUBLIC;
        return this;
    }

    public PlantUMLPropertyBuilder isPrivate() {
        this.accessModifier = AccessModifier.PRIVATE;
        return this;
    }

    public PlantUMLPropertyBuilder isProtected() {
        this.accessModifier = AccessModifier.PROTECTED;
        return this;
    }

    public PlantUMLPropertyBuilder isPackagePrivate() {
        this.accessModifier = AccessModifier.PACKAGE_PRIVATE;
        return this;
    }

    public PlantUMLPropertyBuilder isFinal() {
        this.mutability = Mutability.IMMUTABLE;
        return this;
    }

    public PlantUMLPropertyBuilder isStatic() {
        this.staticness = Staticness.STATIC;
        return this;
    }

    public PlantUMLProperty build() {
        return new PlantUMLProperty(name, typeName, abstractionLevel, accessModifier, mutability, staticness);
    }

    private PlantUMLPropertyBuilder(String name, String typeName) {
        this.name = name;
        this.typeName = typeName;
    }

}