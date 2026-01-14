package dk.runerne.plantuml.model

import dk.runerne.plantuml.model.builder.PlantUMLClassBuilder
import dk.runerne.plantuml.model.builder.PlantUMLClassDiagramBuilder
import dk.runerne.plantuml.model.builder.PlantUMLConstructorBuilder
import dk.runerne.plantuml.model.builder.PlantUMLEnumBuilder
import dk.runerne.plantuml.model.builder.PlantUMLInterfaceBuilder
import dk.runerne.plantuml.model.builder.PlantUMLMethodBuilder
import dk.runerne.plantuml.model.builder.PlantUMLParameterBuilder
import dk.runerne.plantuml.model.builder.PlantUMLPropertyBuilder
import dk.runerne.plantuml.model.builder.PlantUMLSkinParamBuilder
import dk.runerne.plantuml.model.color.HTMLColor
import dk.runerne.plantuml.model.color.PlantUMLColor
import dk.runerne.plantuml.model.skinparameters.Defaults
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

import static dk.runerne.plantuml.model.type.AccessModifier.PACKAGE_PRIVATE
import static dk.runerne.plantuml.model.type.AccessModifier.PRIVATE
import static dk.runerne.plantuml.model.type.AccessModifier.PROTECTED
import static dk.runerne.plantuml.model.type.AccessModifier.PUBLIC

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
        saveToFile('Undefined-skinparam.puml', stringWriter)

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
                        PlantUMLSkinParamBuilder.newInstance()
                                .backgroundColor(Defaults.DEFAULT_BACKGROUND_COLOR)
                )
                .build();
        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)
        saveToFile('Default-background-color.puml', stringWriter)

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
                        PlantUMLSkinParamBuilder.newInstance()
                                .backgroundColor(PlantUMLColor.byHTMLColor(HTMLColor.Blue))
                )
                .build()
        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)
        saveToFile('Blue-background-color.puml', stringWriter)

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
                        PlantUMLSkinParamBuilder.newInstance()
                                .borderColor(Defaults.DEFAULT_BORDER_COLOR)
                )
                .build();
        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)
        saveToFile('Default-border-color.puml', stringWriter)

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
                        PlantUMLSkinParamBuilder.newInstance()
                                .borderColor(PlantUMLColor.byHTMLColor(HTMLColor.Blue))
                )
                .build()
        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)
        saveToFile('Blue-border-color.puml', stringWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
skinparam bordercolor Blue
 
@enduml
""")
    }

    void "Types with values"() {
        given:
        PlantUMLWriter writer = new PlantUMLWriter()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .type(
                        PlantUMLEnumBuilder.newInstance('Status')
                                .isProtected().isAbstract()
                                .value("ACTIVE")
                                .value("INACTIVE")
                                .value("PENDING")
                                .property(PlantUMLPropertyBuilder.newInstance('codeA', 'int').accessModifier(PRIVATE).isAbstract().isStatic())
                                .property(PlantUMLPropertyBuilder.newInstance('codeB', 'int').isPublic().isAbstract())
                                .property(PlantUMLPropertyBuilder.newInstance('codeC', 'int').isProtected().isFinal())
                                .property(PlantUMLPropertyBuilder.newInstance('codeD', 'int').isPackagePrivate().isStatic())
                                .constructor(
                                        PlantUMLConstructorBuilder.newInstance()
                                                .isAbstract()
                                                .parameter(PlantUMLParameterBuilder.newInstance('code', 'int'))
                                )
                                .method(
                                        PlantUMLMethodBuilder.newInstance('getCode', 'int').isPublic().isAbstract()
                                                .parameter(PlantUMLParameterBuilder.newInstance('type', 'String').isFinal())
                                )
                                .method(
                                        PlantUMLMethodBuilder.newInstance('setCode').isPublic().isStatic()
                                                .parameter(PlantUMLParameterBuilder.newInstance('code', 'int'))
                                )
                                .method(
                                        PlantUMLMethodBuilder.newInstance('setCode').isPublic().isStatic()
                                                .parameter(PlantUMLParameterBuilder.newInstance('code', 'int'))
                                                .parameter(PlantUMLParameterBuilder.newInstance('name', 'String'))
                                                .isProtected()
                                )
                )
                .type(
                        PlantUMLInterfaceBuilder.newInstance('MyInterface')
                                .isPublic().isAbstract()
                                .property(PlantUMLPropertyBuilder.newInstance('interfaceProperty', 'String').isPublic().isFinal())
                                .method(
                                        PlantUMLMethodBuilder.newInstance('interfaceMethod', 'void').isPublic().isAbstract()
                                )
                )
                .type(
                        PlantUMLClassBuilder.newInstance('MyClass')
                                .isPublic().isAbstract()
                                .property(PlantUMLPropertyBuilder.newInstance('myProperty', 'int').isPrivate().isStatic())
                                .constructor(PlantUMLConstructorBuilder.newInstance().isPublic())
                                .method(
                                        PlantUMLMethodBuilder.newInstance('myMethod', 'void').isPublic()
                                                .parameter(PlantUMLParameterBuilder.newInstance('param1', 'String'))
                                                .parameter(PlantUMLParameterBuilder.newInstance('param2', 'int'))
                                ).isAbstract()
                )
                .build()
        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)

        when:
        writer.write(diagram, printWriter)
        saveToFile('Types-with-values.puml', stringWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml

enum Status {
  ACTIVE
  INACTIVE
  PENDING
  {abstract} {static} -codeA : int
  {abstract} +codeB : int
  #codeC : int
  {static} ~codeD : int
  {abstract} ~Status(code : int)
  {abstract} +getCode(type : String) : int
  {static} +setCode(code : int)
  {static} #setCode(code : int, name : String)
}

interface MyInterface {
  +interfaceProperty : String
  {abstract} +interfaceMethod() : void
}

class MyClass {
  {static} -myProperty : int
  +MyClass()
  +myMethod(param1 : String, param2 : int) : void
}

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

    private static void saveToFile(String filename, Writer writer) {
        Path path = Path.of("build", "plantUML-files", filename)
        Files.createDirectories(path.parent)
        Files.write(path, writer.toString().getBytes())
    }

}