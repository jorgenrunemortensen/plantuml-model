package dk.runerne.plantuml.model.skinparameters;

public enum PlantUMLFontStyle {

    NORMAL("normal"),
    BOLD("bold"),
    ITALIC("italic"),
    BOLD_ITALIC("bold italic");

    private final String plantUmlName;

    PlantUMLFontStyle(String plantUmlName) {
        this.plantUmlName = plantUmlName;
    }

    public String getPlantUmlName() {
        return plantUmlName;
    }

}