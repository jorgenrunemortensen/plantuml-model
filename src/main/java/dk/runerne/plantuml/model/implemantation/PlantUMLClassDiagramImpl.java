package dk.runerne.plantuml.model.implemantation;

import dk.runerne.plantuml.model.PlantUMLClassDiagram;
import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;
import lombok.Data;

@Data
public class PlantUMLClassDiagramImpl implements PlantUMLClassDiagram {

    public final PlantUMLSkinParam skinParam;

    public PlantUMLClassDiagram build() {
        return this;
    }

}