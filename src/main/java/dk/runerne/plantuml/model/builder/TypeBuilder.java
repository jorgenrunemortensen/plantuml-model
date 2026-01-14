package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.type.AbstractionLevel;
import dk.runerne.plantuml.model.type.AccessModifier;
import dk.runerne.plantuml.model.type.PlantUMLMethod;
import dk.runerne.plantuml.model.type.PlantUMLProperty;
import dk.runerne.plantuml.model.type.Staticness;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
abstract class TypeBuilder<T> {

    private T baseBuilder() {
        return (T) this;
    }

    private final String name;
    private AccessModifier accessModifier;
    private Staticness staticness = Staticness.NON_STATIC;
    private AbstractionLevel abstractionLevel = AbstractionLevel.CONCRETE;
    private final List<PlantUMLProperty> properties = new ArrayList<>();
    private final List<PlantUMLMethod> methods = new ArrayList<>();

    protected TypeBuilder(String name) {
        this.name = name;
    }

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

    public T isStatic() {
        this.staticness = Staticness.STATIC;
        return baseBuilder();
    }

    public T abstractionLevel(AbstractionLevel abstractionLevel) {
        this.abstractionLevel = abstractionLevel;
        return baseBuilder();
    }

    public T isAbstract() {
        this.abstractionLevel = AbstractionLevel.ABSTRACT;
        return baseBuilder();
    }

    public T property(PlantUMLPropertyBuilder builder) {
        properties.add(builder.build());
        return baseBuilder();
    }

    public T method(PlantUMLMethodBuilder builder) {
        methods.add(builder.build());
        return baseBuilder();
    }

}