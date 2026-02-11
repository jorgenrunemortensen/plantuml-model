package dk.runerne.plantuml.model.relation;

import dk.runerne.plantuml.model.type.PlantUMLType;
import lombok.Getter;

@Getter
public class PlantUMLCustomRelation extends AbstractPlantUMLRelation implements MetricSupplier {

    private final String fromConnector;
    private final String toConnector;
    private final char lineCharacter;

    public PlantUMLCustomRelation(
        PlantUMLType from,
        String fromText,
        String fromConnector,
        PlantUMLType to,
        String toText,
        String toConnector,
        String name,
        int length,
        char lineCharacter
    ) {
        super(from, fromText, to, toText, name, length);
        this.fromConnector = fromConnector;
        this.toConnector = toConnector;
        this.lineCharacter = lineCharacter;
    }

}