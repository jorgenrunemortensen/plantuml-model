package dk.runerne.plantuml.model.builder;

import dk.runerne.plantuml.model.relation.PlantUMLCustomRelation;
import dk.runerne.plantuml.model.relation.PlantUMLRelation;
import dk.runerne.plantuml.model.type.PlantUMLType;
import lombok.Getter;

@Getter
public class PlantUMLCustomRelationBuilder extends PlantUMLRelationBuilder {

    private String fromConnector = "";
    private String toConnector = "";
    private final char lineCharacter;

    public static PlantUMLCustomRelationBuilder newInstance(PlantUMLType from, PlantUMLType to, char lineCharacter) {
        return new PlantUMLCustomRelationBuilder(from, to, lineCharacter);
    }

    public PlantUMLCustomRelationBuilder fromConnector(String fromConnector) {
        this.fromConnector = fromConnector;
        return this;
    }

    public PlantUMLCustomRelationBuilder toConnector(String toConnector) {
        this.toConnector = toConnector;
        return this;
    }

    @Override
    public PlantUMLRelation build() {
        return new PlantUMLCustomRelation(
            getFrom(),
            getFromText(),
            getFromConnector(),
            getTo(),
            getToText(),
            getToConnector(),
            getName(),
            getLength(),
            getLineCharacter()
        );
    }

    private PlantUMLCustomRelationBuilder(PlantUMLType from, PlantUMLType to, char lineCharacter) {
        super(from, to);
        this.lineCharacter = lineCharacter;
    }

}