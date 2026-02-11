package dk.runerne.plantuml.model;

import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;
import dk.runerne.plantuml.model.type.PlantUMLType;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;

import java.util.List;

public interface PlantUMLClassDiagram {

    PlantUMLSkinParam getSkinParam();
    List<PlantUMLType> getTypes();
    List<PlantUMLRelation> getRelations();

}