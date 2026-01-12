package dk.runerne.plantuml.model.skinparameters;

import dk.runerne.plantuml.model.color.HTMLColor;
import dk.runerne.plantuml.model.color.PlantUMLColor;

public class Defaults {

    public static final PlantUMLColor DEFAULT_BACKGROUND_COLOR = PlantUMLColor.byHTMLColor(HTMLColor.White);
    public static final PlantUMLColor DEFAULT_BORDER_COLOR = PlantUMLColor.byHTMLColor(HTMLColor.Black);

    public static boolean isDefaultBackgroundColor(final PlantUMLColor backgroundColor) {
        return DEFAULT_BACKGROUND_COLOR == backgroundColor;
    }

    public static boolean isDefaultBorderColor(final PlantUMLColor borderColor) {
        return DEFAULT_BORDER_COLOR == borderColor;
    }

}