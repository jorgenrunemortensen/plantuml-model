package dk.runerne.plantuml.model.implemantation;

import dk.runerne.plantuml.model.PlantUMLClassDiagram;
import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;
import dk.runerne.plantuml.model.type.PlantUMLType;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import lombok.Data;

import java.util.List;

@Data
public class PlantUMLClassDiagramImpl implements PlantUMLClassDiagram {

    public final PlantUMLSkinParam skinParam;
    public final List<PlantUMLType> types;
    public final List<PlantUMLRelation> relations;

    public PlantUMLClassDiagram build() {
        return this;
    }

}