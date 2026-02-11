package dk.runerne.plantuml.model.type;

import java.util.List;

public interface PlantUMLType {

    String getName();
    AbstractionLevel getAbstractionLevel();
    Staticness getStaticness();
    AccessModifier getAccessModifier();
    List<PlantUMLProperty> getProperties();
    List<PlantUMLMethod> getMethods();

}