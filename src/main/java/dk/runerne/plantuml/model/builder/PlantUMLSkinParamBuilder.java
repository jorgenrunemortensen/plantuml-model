package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.color.PlantUMLColor;
import dk.runerne.plantuml.model.implemantation.PlantUMLSkinParamImpl;
import dk.runerne.plantuml.model.skinparameters.Defaults;
import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;

public class PlantUMLSkinParamBuilder {

    private PlantUMLColor backgroundColor = Defaults.DEFAULT_BACKGROUND_COLOR;
    private PlantUMLColor borderColor = Defaults.DEFAULT_BORDER_COLOR;

    public static PlantUMLSkinParamBuilder newInstance() {
        return new PlantUMLSkinParamBuilder();
    }

    public PlantUMLSkinParamBuilder backgroundColor(PlantUMLColor backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public PlantUMLSkinParamBuilder borderColor(PlantUMLColor borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public PlantUMLSkinParam build() {
        PlantUMLSkinParamImpl skinParam = new PlantUMLSkinParamImpl();
        skinParam.setBackgroundColor(backgroundColor);
        skinParam.setBorderColor(borderColor);
        return skinParam;
    }

    private PlantUMLSkinParamBuilder() {
    }

}