package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.AbstractionLevel;
import dk.runerne.plantuml.model.type.AccessModifier;
import dk.runerne.plantuml.model.type.Mutability;
import dk.runerne.plantuml.model.type.PlantUMLParameter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
abstract class CallPointBuilder<T> {

    private T baseBuilder() {
        return (T) this;
    }

    protected final String name;
    private AccessModifier accessModifier = AccessModifier.PACKAGE_PRIVATE;
    private Mutability mutability = Mutability.MUTABLE;
    private AbstractionLevel abstractionLevel = AbstractionLevel.CONCRETE;
    private final List<PlantUMLParameter> parameters = new ArrayList<>();

    public T accessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
        return baseBuilder();
    }

    public T isPublic() {
        this.accessModifier = AccessModifier.PUBLIC;
        return baseBuilder();
    }

    public T isPrivate() {
        this.accessModifier = AccessModifier.PRIVATE;
        return baseBuilder();
    }

    public T isProtected() {
        this.accessModifier = AccessModifier.PROTECTED;
        return baseBuilder();
    }

    public T isPackagePrivate() {
        this.accessModifier = AccessModifier.PACKAGE_PRIVATE;
        return baseBuilder();
    }

    public T isFinal() {
        this.mutability = Mutability.IMMUTABLE;
        return baseBuilder();
    }

    public T isAbstract() {
        this.abstractionLevel = AbstractionLevel.ABSTRACT;
        return baseBuilder();
    }

    public T parameter(PlantUMLParameterBuilder parameterBuilder) {
        this.parameters.add(parameterBuilder.build());
        return baseBuilder();
    }

    protected CallPointBuilder(String name) {
        this.name = name;
    }

}