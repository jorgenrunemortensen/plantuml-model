package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.PlantUMLClassDiagram;
import dk.runerne.plantuml.model.implemantation.PlantUMLClassDiagramImpl;
import dk.runerne.plantuml.model.implemantation.PlantUMLSkinParamImpl;
import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;

public class PlantUMLClassDiagramBuilder {

    private PlantUMLSkinParam skinParam = new PlantUMLSkinParamImpl();

    public static PlantUMLClassDiagramBuilder newPlantUMLClassDiagram() {
        return new PlantUMLClassDiagramBuilder();
    }

    public PlantUMLClassDiagramBuilder skinParam(PlantUMLSkinParam skinParam) {
        this.skinParam = skinParam;
        return this;
    }

    public PlantUMLClassDiagram build() {
        return new PlantUMLClassDiagramImpl(skinParam);
    }

}