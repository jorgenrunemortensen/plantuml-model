package dk.runerne.plantuml.model.skinparameters;

public enum PlantUMLLinetype {

    POLYLINE("polyline"),
    ORTHOGONAL("ortho"),
    CURVED("curved");

    private final String plantUmlName;

    PlantUMLLinetype(String plantUmlName) {
        this.plantUmlName = plantUmlName;
    }

    public String getPlantUmlName() {
        return plantUmlName;
    }

}