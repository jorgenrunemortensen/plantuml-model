package dk.runerne.plantuml.model.relation;

public interface MetricSupplier {

    String getFromConnector();
    String getToConnector();
    char getLineCharacter();

}