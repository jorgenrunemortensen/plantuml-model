package dk.runerne.plantuml.model.type;

import java.util.List;

public interface PlantUMLCallPoint {

    AbstractionLevel getAbstractionLevel();
    AccessModifier getAccessModifier();
    List<PlantUMLParameter> getParameters();

}