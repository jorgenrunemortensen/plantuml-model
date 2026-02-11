package dk.runerne.plantuml.model.skinparameters;

import dk.runerne.plantuml.model.color.PlantUMLColor;

public interface PlantUMLSkinParam {

    /* General skin parameters */
    PlantUMLColor getBackgroundColor();
    PlantUMLColor getBorderColor();
    PlantUMLColor getFontColor();

    PlantUMLLinetype getLinetype();

    boolean getShadowing();
    int getDpi();
    int getRoudedCornerRadius();
    int getPadding();

    /* Fonts and Texts */
    String getDefaultFontName();
    int getDefaultFontSize();
    PlantUMLColor getDefaultFontColor();

}