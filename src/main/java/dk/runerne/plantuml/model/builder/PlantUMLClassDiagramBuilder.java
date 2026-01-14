package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.PlantUMLClassDiagram;
import dk.runerne.plantuml.model.implemantation.PlantUMLClassDiagramImpl;
import dk.runerne.plantuml.model.implemantation.PlantUMLSkinParamImpl;
import dk.runerne.plantuml.model.skinparameters.PlantUMLSkinParam;
import dk.runerne.plantuml.model.type.PlantUMLType;

import java.util.ArrayList;
import java.util.List;

public class PlantUMLClassDiagramBuilder {

    private PlantUMLSkinParam skinParam = new PlantUMLSkinParamImpl();
    private final List<PlantUMLType> types = new ArrayList<>();

    public static PlantUMLClassDiagramBuilder newPlantUMLClassDiagram() {
        return new PlantUMLClassDiagramBuilder();
    }

    public PlantUMLClassDiagramBuilder skinParam(PlantUMLSkinParamBuilder builder) {
        this.skinParam = builder.build();
        return this;
    }

    public PlantUMLClassDiagramBuilder type(PlantUMLEnumBuilder builder) {
        types.add(builder.build());
        return this;
    }

    public PlantUMLClassDiagramBuilder type(PlantUMLInterfaceBuilder builder) {
        types.add(builder.build());
        return this;
    }

    public PlantUMLClassDiagramBuilder type(PlantUMLClassBuilder builder) {
        types.add(builder.build());
        return this;
    }

    public PlantUMLClassDiagram build() {
        return new PlantUMLClassDiagramImpl(skinParam, types);
    }

    private PlantUMLClassDiagramBuilder() {
    }

}