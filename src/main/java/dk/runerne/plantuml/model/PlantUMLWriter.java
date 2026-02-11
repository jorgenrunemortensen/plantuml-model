package dk.runerne.plantuml.model;

import dk.runerne.plantuml.model.color.PlantUMLColor;
import dk.runerne.plantuml.model.relation.MetricSupplier;
import dk.runerne.plantuml.model.relation.PlantUMLAggregation;
import dk.runerne.plantuml.model.relation.PlantUMLComposition;
import dk.runerne.plantuml.model.relation.PlantUMLDependency;
import dk.runerne.plantuml.model.relation.PlantUMLImplementation;
import dk.runerne.plantuml.model.relation.PlantUMLInheritance;
import dk.runerne.plantuml.model.skinparameters.Defaults;
import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;
import dk.runerne.plantuml.model.type.AbstractionLevel;
import dk.runerne.plantuml.model.type.AccessModifier;
import dk.runerne.plantuml.model.type.PlantUMLClass;
import dk.runerne.plantuml.model.type.PlantUMLConstructor;
import dk.runerne.plantuml.model.type.PlantUMLEnum;
import dk.runerne.plantuml.model.type.PlantUMLInterface;
import dk.runerne.plantuml.model.type.PlantUMLMethod;
import dk.runerne.plantuml.model.type.PlantUMLParameter;
import dk.runerne.plantuml.model.type.PlantUMLProperty;
import dk.runerne.plantuml.model.type.PlantUMLType;
import dk.runerne.plantuml.model.type.Staticness;
import dk.runerne.plantuml.model.relation.PlantUMLAssociation;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Closeable;
import java.io.PrintWriter;
import java.util.function.Function;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class PlantUMLWriter implements Closeable {

    private final PrintWriter printWriter;

    public void write(final PlantUMLClassDiagram diagram) {
        printWriter.println("@startuml");
        printWriter.println();
        write(diagram.getSkinParam(), printWriter);
        printWriter.println();
        diagram.getTypes().forEach(type -> write(type, printWriter));
        printWriter.println();
        diagram.getRelations().forEach(relation -> write(relation, printWriter));
        printWriter.println("@enduml");
    }

    @Override
    public void close() {
        if (printWriter != null) {
            printWriter.close();
        }
    }

    private static void write(final PlantUMLSkinParam skinParam, PrintWriter printWriter) {
        printlnIfNotDefault("skinparam backgroundcolor", skinParam.getBackgroundColor(), Defaults::isDefaultBackgroundColor, PlantUMLColor::getPlantUMLName, printWriter);
        printlnIfNotDefault("skinparam bordercolor", skinParam.getBorderColor(), Defaults::isDefaultBorderColor, PlantUMLColor::getPlantUMLName, printWriter);
    }
    private static void write(final PlantUMLType type, PrintWriter printWriter) {
        if (type instanceof PlantUMLEnum enumType) {
            write(enumType, printWriter);
            return;
        }

        if (type instanceof PlantUMLInterface interfaceType) {
            write(interfaceType, printWriter);
            return;
        }

        if (type instanceof PlantUMLClass classType) {
            write(classType, printWriter);
            return;
        }

        throw new IllegalArgumentException("Unsupported type: " + type.getClass().getName());
    }

    private static void write(final PlantUMLClass plantUMLClass, PrintWriter printWriter) {
        printWriter.printf("class %s {%n", plantUMLClass.getName());
        plantUMLClass.getProperties().forEach(property -> write(property, printWriter));
        plantUMLClass.getConstructors().forEach(constructor -> write(constructor, plantUMLClass.getName(), printWriter));
        plantUMLClass.getMethods().forEach(method -> write(method, printWriter));
        printWriter.println("}");
        printWriter.println();
    }

    private static void write(final PlantUMLInterface plantUMLInterface, PrintWriter printWriter) {
        printWriter.printf("interface %s {%n", plantUMLInterface.getName());
        plantUMLInterface.getProperties().forEach(property -> write(property, printWriter));
        plantUMLInterface.getMethods().forEach(method -> write(method, printWriter));
        printWriter.println("}");
        printWriter.println();
    }

    private static void write(final PlantUMLEnum plantUMLEnum, PrintWriter printWriter) {
        printWriter.printf("enum %s {%n", plantUMLEnum.getName());
        plantUMLEnum.getValues().forEach(value -> printWriter.printf("  %s%n", value));
        plantUMLEnum.getProperties().forEach(property -> write(property, printWriter));
        plantUMLEnum.getConstructors().forEach(constructor -> write(constructor, plantUMLEnum.getName(), printWriter));
        plantUMLEnum.getMethods().forEach(method -> write(method, printWriter));
        printWriter.println("}");
        printWriter.println();
    }

    private static void write(final PlantUMLProperty property, PrintWriter printWriter) {
        printWriter.print("  ");
        printAbstractionLevel(property.getAbstractionLevel(), printWriter);
        printStaticness(property.getStaticness(), printWriter);
        printAccessModifier(property.getAccessModifier(), printWriter);
        printWriter.printf("%s : %s%n", property.getName(), property.getTypeName());
    }

    private static void write(final PlantUMLConstructor constructor, String typeName, PrintWriter printWriter) {
        printWriter.print("  ");
        printAbstractionLevel(constructor.getAbstractionLevel(), printWriter);
        printAccessModifier(constructor.getAccessModifier(), printWriter);
        printWriter.printf("%s", typeName);
        write(constructor.getParameters(), printWriter);
        printWriter.println();
    }

    private static void write(final PlantUMLMethod method, PrintWriter printWriter) {
        printWriter.print("  ");
        printAbstractionLevel(method.getAbstractionLevel(), printWriter);
        printStaticness(method.getStaticness(), printWriter);
        printAccessModifier(method.getAccessModifier(), printWriter);
        printWriter.printf("%s", method.getName());
        write(method.getParameters(), printWriter);
        if (method.getReturnTypeName() != null) {
            printWriter.printf(" : %s", method.getReturnTypeName());
        }
        printWriter.println();
    }

    private static void write(List<PlantUMLParameter> parameters, PrintWriter printWriter) {
        printWriter.print("(");
        for (int i = 0; i < parameters.size(); i++) {
            PlantUMLParameter parameter = parameters.get(i);
            write(parameter, printWriter);
            if (i < parameters.size() - 1) {
                printWriter.print(", ");
            }
        }
        printWriter.print(")");
    }

    private static void write(PlantUMLParameter parameter, PrintWriter printWriter) {
        printWriter.printf("%s : %s", parameter.getName(), parameter.getTypeName());
    }

    private static <T> void printlnIfNotDefault(String key, T value, Function<T, Boolean> defaultChecker, Function<T, String> valueExtractor, PrintWriter printWriter) {
        if (defaultChecker.apply(value))
            return;
        printWriter.printf("%s %s%n", key, valueExtractor.apply(value));
    }

    private static void printAbstractionLevel(AbstractionLevel abstractionLevel, PrintWriter printWriter) {
        if (abstractionLevel == null)
            return;

        switch (abstractionLevel) {
            case ABSTRACT -> printWriter.print("{abstract} ");
            case CONCRETE -> {
                // Do nothing
            }
        }
    }

    private static void printStaticness(Staticness staticness, PrintWriter printWriter) {
        if (staticness == null)
            return;

        switch (staticness) {
            case STATIC -> printWriter.print("{static} ");
            case NON_STATIC -> {
                // Do nothing
            }
        }
    }

    private static void printAccessModifier(AccessModifier accessModifier, PrintWriter printWriter) {
        if (accessModifier == null)
            return;

        switch (accessModifier) {
            case PUBLIC -> printWriter.print("+");
            case PRIVATE -> printWriter.print("-");
            case PROTECTED -> printWriter.print("#");
            case PACKAGE_PRIVATE -> printWriter.print("~");
        }
    }

    private static void write(PlantUMLRelation relation, PrintWriter printWriter) {
        var metrics = getRelationMetrics(relation);
        var line = String.valueOf(metrics.lineCharacter).repeat(relation.getLength());
        printWriter.printf(
            "%s %s%s%s %s",
            relation.getFrom().getName(),
            getFromConnector(metrics, relation.getFromText()),
            line,
            getToConnector(metrics, relation.getToText()),
            relation.getTo().getName()
        );
        if (relation.getName() != null && !relation.getName().isEmpty()) {
            printWriter.printf(": %s%n", convertNewLines(relation.getName()));
        } else {
            printWriter.println();
        }
    }

    @Data
    private static class RelationMetrics implements MetricSupplier {

        private final String fromConnector;
        private final String toConnector;
        private final char lineCharacter;

    }

    private static RelationMetrics getRelationMetrics(PlantUMLRelation relation) {
        if (relation instanceof MetricSupplier supplier) {
            return new RelationMetrics(
                supplier.getFromConnector(),
                supplier.getToConnector(),
                supplier.getLineCharacter()
            );
        }

        var relationClass = relation.getClass();
        var metrics = relationMetricsMap.get(relationClass);
        if (metrics == null) {
            throw new IllegalArgumentException("Unsupported relation type: " + relationClass.getName());
        }

        return metrics;
    }

    private static final Map<Class, RelationMetrics> relationMetricsMap = Map.of(
        PlantUMLAggregation.class, new RelationMetrics("o", ">", '-'),
        PlantUMLAssociation.class, new RelationMetrics("", ">", '-'),
        PlantUMLComposition.class, new RelationMetrics("*", ">", '-'),
        PlantUMLDependency.class, new RelationMetrics("", ">", '.'),
        PlantUMLImplementation.class, new RelationMetrics("", "|>", '.'),
        PlantUMLInheritance.class, new RelationMetrics("", "|>", '-')
    );

    private static String getFromConnector(RelationMetrics metrics, String fromText) {
        if (fromText == null || fromText.isEmpty()) {
            return metrics.getFromConnector();
        }
        return "\"" + convertNewLines(fromText) + "\" " + metrics.getFromConnector();
    }

    private static String getToConnector(RelationMetrics metrics, String toText) {
        if (toText == null || toText.isEmpty()) {
            return metrics.getToConnector();
        }
        return metrics.getToConnector() + " \"" + convertNewLines(toText) + "\"";
    }

    private static String convertNewLines(String text) {
        return text.replace("\n", "\\n");
    }

}