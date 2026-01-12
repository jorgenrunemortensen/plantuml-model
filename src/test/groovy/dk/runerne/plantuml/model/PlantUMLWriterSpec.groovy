package dk.runerne.plantuml.model

import dk.runerne.plantuml.model.builder.PlantUMLClassDiagramBuilder
import dk.runerne.plantuml.model.builder.PlantUMLSkinParamBuilder
import dk.runerne.plantuml.model.color.HTMLColor
import dk.runerne.plantuml.model.color.PlantUMLColor
import dk.runerne.plantuml.model.skinparameters.Defaults
import spock.lang.Specification

class PlantUMLWriterSpec extends Specification {

    void "Undefined skinparam"() {
        given:
        PlantUMLWriter writer = new PlantUMLWriter()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .build();
        StringWriter stringWriter = new StringWriter()

        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
@enduml
""")
    }

    void "Default background color"() {
        given:
        PlantUMLWriter writer = new PlantUMLWriter()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .skinParam(
                        PlantUMLSkinParamBuilder.newPlantUMLSkinParam()
                                .backgroundColor(Defaults.DEFAULT_BACKGROUND_COLOR)
                                .build()
                )
                .build();
        StringWriter stringWriter = new StringWriter()

        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
@enduml
""")
    }

    void "Blue background color"() {
        given:
        PlantUMLWriter writer = new PlantUMLWriter()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .skinParam(
                        PlantUMLSkinParamBuilder.newPlantUMLSkinParam()
                                .backgroundColor(PlantUMLColor.byHTMLColor(HTMLColor.Blue))
                                .build()
                )
                .build()
        StringWriter stringWriter = new StringWriter()

        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
skinparam backgroundcolor Blue
 
@enduml
""")
    }

    void "Default border color"() {
        given:
        PlantUMLWriter writer = new PlantUMLWriter()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .skinParam(
                        PlantUMLSkinParamBuilder.newPlantUMLSkinParam()
                                .borderColor(Defaults.DEFAULT_BORDER_COLOR)
                                .build()
                )
                .build();
        StringWriter stringWriter = new StringWriter()

        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
@enduml
""")
    }

    void "Blue border color"() {
        given:
        PlantUMLWriter writer = new PlantUMLWriter()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .skinParam(
                        PlantUMLSkinParamBuilder.newPlantUMLSkinParam()
                                .borderColor(PlantUMLColor.byHTMLColor(HTMLColor.Blue))
                                .build()
                )
                .build()
        StringWriter stringWriter = new StringWriter()

        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
skinparam bordercolor Blue
 
@enduml
""")
    }

    private static String normalizeWhitespace(String input) {
        input
                ?.replaceAll(/(?m)^[ \t]*\r?\n/, '')   // fjern tomme linjer
                ?.replaceAll(/[\r\n]+/, ' ')           // linjeskift → mellemrum
                ?.replaceAll(/\s+/, ' ')               // gentagne whitespace → ét space
                ?.trim()
    }

}