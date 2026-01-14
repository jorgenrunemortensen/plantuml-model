package dk.runerne.plantuml.model;

import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;
import dk.runerne.plantuml.model.type.PlantUMLType;

import java.util.List;

public interface PlantUMLClassDiagram {

    PlantUMLSkinParam getSkinParam();
    List<PlantUMLType> getTypes();

}