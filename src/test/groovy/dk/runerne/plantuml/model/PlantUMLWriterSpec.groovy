package dk.runerne.plantuml.model

import dk.runerne.plantuml.model.builder.PlantUMLAggregationBuilder
import dk.runerne.plantuml.model.builder.PlantUMLAssociationBuilder
import dk.runerne.plantuml.model.builder.PlantUMLClassBuilder
import dk.runerne.plantuml.model.builder.PlantUMLClassDiagramBuilder
import dk.runerne.plantuml.model.builder.PlantUMLCompositionBuilder
import dk.runerne.plantuml.model.builder.PlantUMLConstructorBuilder
import dk.runerne.plantuml.model.builder.PlantUMLCustomRelationBuilder
import dk.runerne.plantuml.model.builder.PlantUMLDependencyBuilder
import dk.runerne.plantuml.model.builder.PlantUMLEnumBuilder
import dk.runerne.plantuml.model.builder.PlantUMLImplementationBuilder
import dk.runerne.plantuml.model.builder.PlantUMLInheritanceBuilder
import dk.runerne.plantuml.model.builder.PlantUMLInterfaceBuilder
import dk.runerne.plantuml.model.builder.PlantUMLMethodBuilder
import dk.runerne.plantuml.model.builder.PlantUMLParameterBuilder
import dk.runerne.plantuml.model.builder.PlantUMLPropertyBuilder
import dk.runerne.plantuml.model.builder.PlantUMLSkinParamBuilder
import dk.runerne.plantuml.model.color.HTMLColor
import dk.runerne.plantuml.model.color.PlantUMLColor
import dk.runerne.plantuml.model.skinparameters.Defaults

import dk.runerne.plantuml.model.type.PlantUMLType
import groovy.transform.CompileDynamic
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

import static dk.runerne.plantuml.model.type.AccessModifier.PRIVATE

@CompileDynamic
class PlantUMLWriterSpec extends Specification {

    void "Undefined skinparam"() {
        given:
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .build();
        StringWriter stringWriter = new StringWriter()
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(new PrintWriter(stringWriter))

        when:
        plantUMLWriter.write(diagram)
        saveToFile('Undefined-skinparam.puml', stringWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
@enduml
""")
    }

    void "Default background color"() {
        given:
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .skinParam(
                        PlantUMLSkinParamBuilder.newInstance()
                                .backgroundColor(Defaults.DEFAULT_BACKGROUND_COLOR)
                )
                .build();
        StringWriter stringWriter = new StringWriter()
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(new PrintWriter(stringWriter))

        when:
        plantUMLWriter.write(diagram)
        saveToFile('Default-background-color.puml', stringWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
@enduml
""")
    }

    void "Blue background color"() {
        given:
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .skinParam(
                        PlantUMLSkinParamBuilder.newInstance()
                                .backgroundColor(PlantUMLColor.byHTMLColor(HTMLColor.Blue))
                )
                .build()
        StringWriter stringWriter = new StringWriter()
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(new PrintWriter(stringWriter))

        when:
        plantUMLWriter.write(diagram)
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
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .skinParam(
                        PlantUMLSkinParamBuilder.newInstance()
                                .borderColor(Defaults.DEFAULT_BORDER_COLOR)
                )
                .build();
        StringWriter stringWriter = new StringWriter()
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(new PrintWriter(stringWriter))

        when:
        plantUMLWriter.write(diagram)
        saveToFile('Default-border-color.puml', stringWriter)

        then:
        normalizeWhitespace(stringWriter.toString()) == normalizeWhitespace("""
@startuml
             
@enduml
""")
    }

    void "Blue border color"() {
        given:
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .skinParam(
                        PlantUMLSkinParamBuilder.newInstance()
                                .borderColor(PlantUMLColor.byHTMLColor(HTMLColor.Blue))
                )
                .build()
        StringWriter stringWriter = new StringWriter()
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(new PrintWriter(stringWriter))

        when:
        plantUMLWriter.write(diagram)
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
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(printWriter)

        when:
        plantUMLWriter.write(diagram)
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

    void "Types with aggregation"() {
        given:
        PlantUMLType classA = PlantUMLClassBuilder.newInstance('ClassA').isPublic().build()
        PlantUMLType classB = PlantUMLClassBuilder.newInstance('ClassB').isPublic().build()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .type(classA)
                .type(classB)
                .relation(
                        PlantUMLAggregationBuilder.newInstance(classA, classB)
                        .length(4)
                        .fromText("from\nhere")
                        .toText('to\nnowhere')
                        .name('MyAggregation\nRelation')
                )
                .build()

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(printWriter)

        when:
        plantUMLWriter.write(diagram)

        then:
        saveToFile("Types-with-aggregation-type.puml", stringWriter)
    }

    void "Types with association"() {
        given:
        PlantUMLType classA = PlantUMLClassBuilder.newInstance('ClassA').isPublic().build()
        PlantUMLType classB = PlantUMLClassBuilder.newInstance('ClassB').isPublic().build()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .type(classA)
                .type(classB)
                .relation(
                        PlantUMLAssociationBuilder.newInstance(classA, classB)
                )
                .build()

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(printWriter)

        when:
        plantUMLWriter.write(diagram)

        then:
        saveToFile("Types-with-association-type.puml", stringWriter)
    }

    void "Types with composition"() {
        given:
        PlantUMLType classA = PlantUMLClassBuilder.newInstance('ClassA').isPublic().build()
        PlantUMLType classB = PlantUMLClassBuilder.newInstance('ClassB').isPublic().build()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .type(classA)
                .type(classB)
                .relation(
                        PlantUMLCompositionBuilder.newInstance(classA, classB)
                )
                .build()

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(printWriter)

        when:
        plantUMLWriter.write(diagram)

        then:
        saveToFile("Types-with-composition-type.puml", stringWriter)
    }

    void "Types with dependency"() {
        given:
        PlantUMLType classA = PlantUMLClassBuilder.newInstance('ClassA').isPublic().build()
        PlantUMLType classB = PlantUMLClassBuilder.newInstance('ClassB').isPublic().build()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .type(classA)
                .type(classB)
                .relation(
                        PlantUMLDependencyBuilder.newInstance(classA, classB)
                )
                .build()

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(printWriter)

        when:
        plantUMLWriter.write(diagram)

        then:
        saveToFile("Types-with-dependency-type.puml", stringWriter)
    }

    void "Types with implementation"() {
        given:
        PlantUMLType classA = PlantUMLClassBuilder.newInstance('ClassA').isPublic().build()
        PlantUMLType classB = PlantUMLClassBuilder.newInstance('ClassB').isPublic().build()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .type(classA)
                .type(classB)
                .relation(
                        PlantUMLImplementationBuilder.newInstance(classA, classB)
                )
                .build()

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(printWriter)

        when:
        plantUMLWriter.write(diagram)

        then:
        saveToFile("Types-with-implementation-type.puml", stringWriter)
    }

    void "Types with inheritance"() {
        given:
        PlantUMLType classA = PlantUMLClassBuilder.newInstance('ClassA').isPublic().build()
        PlantUMLType classB = PlantUMLClassBuilder.newInstance('ClassB').isPublic().build()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .type(classA)
                .type(classB)
                .relation(
                        PlantUMLInheritanceBuilder.newInstance(classA, classB)
                )
                .build()

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(printWriter)

        when:
        plantUMLWriter.write(diagram)

        then:
        saveToFile("Types-with-inheritance-type.puml", stringWriter)
    }

    void "Types with custom relation"() {
        given:
        PlantUMLType classA = PlantUMLClassBuilder.newInstance('ClassA').isPublic().build()
        PlantUMLType classB = PlantUMLClassBuilder.newInstance('ClassB').isPublic().build()
        PlantUMLClassDiagram diagram = PlantUMLClassDiagramBuilder
                .newPlantUMLClassDiagram()
                .type(classA)
                .type(classB)
                .relation(
                        PlantUMLCustomRelationBuilder.newInstance(classA, classB, (char)'=')
                        .fromConnector("<|")
                        .toConnector("|>")
                )
                .build()

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)
        PlantUMLWriter plantUMLWriter = new PlantUMLWriter(printWriter)

        when:
        plantUMLWriter.write(diagram)

        then:
        saveToFile("Types-with-custom-relation-type.puml", stringWriter)
    }

    private static String normalizeWhitespace(String input) {
        return input
                ?.replaceAll(/(?m)^[ \t]*\r?\n/, '')   // remove empty lines
                ?.replaceAll(/[\r\n]+/, ' ')           // newlines → one space
                ?.replaceAll(/\s+/, ' ')               // repeated whitespaces → one space
                ?.trim()
    }

    private static void saveToFile(String filename, Writer writer) {
        Path path = Path.of("build", "plantUML-files", filename)
        Files.createDirectories(path.parent)
        Files.write(path, writer.toString().getBytes())
    }

}