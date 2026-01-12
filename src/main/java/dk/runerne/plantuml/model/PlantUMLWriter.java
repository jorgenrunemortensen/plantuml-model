package dk.runerne.plantuml.model;

import dk.runerne.plantuml.model.color.PlantUMLColor;
import dk.runerne.plantuml.model.skinparameters.Defaults;
import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;
import lombok.Data;

import java.io.PrintWriter;
import java.util.function.Function;

@Data
public class PlantUMLWriter {

    public void write(final PlantUMLClassDiagram diagram, PrintWriter printWriter) {
        printWriter.println("@startuml");
        printWriter.println();
        write(diagram.getSkinParam(), printWriter);
        printWriter.println();
        printWriter.println("@enduml");
    }

    private static void write(final PlantUMLSkinParam skinParam, PrintWriter printWriter) {
        printlnIfNotDefault("skinparam backgroundcolor", skinParam.getBackgroundColor(), Defaults::isDefaultBackgroundColor, PlantUMLColor::getPlantUMLName, printWriter);
        printlnIfNotDefault("skinparam bordercolor", skinParam.getBorderColor(), Defaults::isDefaultBorderColor, PlantUMLColor::getPlantUMLName, printWriter);
    }

    private static <T> void printlnIfNotDefault(String key, T value, Function<T, Boolean> defaultChecker, Function<T, String> valueExtractor, PrintWriter printWriter) {
        if (defaultChecker.apply(value))
            return;
        printWriter.printf("%s %s%n", key, valueExtractor.apply(value));
    }

}