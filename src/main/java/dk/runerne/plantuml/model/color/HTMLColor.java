package dk.runerne.plantuml.model.color;

public enum HTMLColor {

    Black("Black"),
    White("White"),
    Gray("Gray"),
    LightGray("LightGray"),
    DarkGray("DarkGray"),
    Red("Red"),
    DarkRed("DarkRed"),
    LightRed("LightRed"),
    Green("Green"),
    DarkGreen("DarkGreen"),
    LightGreen("LightGreen"),
    Blue("Blue"),
    DarkBlue("DarkBlue"),
    LightBlue("LightBlue"),
    Yellow("Yellow"),
    LightYellow("LightYellow"),
    Orange("Orange"),
    Pink("Pink"),
    Purple("Purple"),
    Brown("Brown"),
    Cyan("Cyan"),
    Magenta("Magenta");

    private final String plantUmlName;

    HTMLColor(String plantUmlName) {
        this.plantUmlName = plantUmlName;
    }

    public String getPlantUmlName() {
        return plantUmlName;
    }

}