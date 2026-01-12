package dk.runerne.plantuml.model.implemantation;

import dk.runerne.plantuml.model.color.PlantUMLColor;
import dk.runerne.plantuml.model.skinparameters.Defaults;
import dk.runerne.plantuml.model.skinparameters.PlantUMLLinetype;
import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;
import lombok.Data;

@Data
public class PlantUMLSkinParamImpl implements PlantUMLSkinParam {

    private PlantUMLColor backgroundColor = Defaults.DEFAULT_BACKGROUND_COLOR;
    private PlantUMLColor borderColor = Defaults.DEFAULT_BORDER_COLOR;

    @Override
    public PlantUMLColor getFontColor() {
        return null;
    }

    @Override
    public PlantUMLLinetype getLinetype() {
        return null;
    }

    @Override
    public boolean getShadowing() {
        return false;
    }

    @Override
    public int getDpi() {
        return 0;
    }

    @Override
    public int getRoudedCornerRadius() {
        return 0;
    }

    @Override
    public int getPadding() {
        return 0;
    }

    @Override
    public String getDefaultFontName() {
        return "";
    }

    @Override
    public int getDefaultFontSize() {
        return 0;
    }

    @Override
    public PlantUMLColor getDefaultFontColor() {
        return null;
    }

}