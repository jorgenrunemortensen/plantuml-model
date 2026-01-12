package dk.runerne.plantuml.model.color;

import lombok.Data;

import java.awt.*;

@Data
public class PlantUMLColor {

    private final String plantUMLName;

    public static PlantUMLColor byColor(Color color) {
        return new PlantUMLColor(color);
    }

    public static PlantUMLColor byHTMLColor(HTMLColor htmlColor) {
        return new PlantUMLColor(htmlColor);
    }

    private PlantUMLColor(Color color) {
        plantUMLName = String.format("#%06X", color.getRGB() & 0xFFFFFF);
    }

    private PlantUMLColor(HTMLColor htmlColor) {
        this.plantUMLName = htmlColor.getPlantUmlName();
    }

}