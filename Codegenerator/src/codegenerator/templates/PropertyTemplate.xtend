package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.AggregationKind
import org.eclipse.uml2.uml.Property

class PropertyTemplate implements Template<Property> {

	override generateCode(CodegenInterface it, Property umlProperty, String context) {
		// Aufgabe 1
		var typeName = ""
		var name = ""

		if (context == "attribute") {
			name = generate(umlProperty, "name")
			if (umlProperty.type !== null) {
				typeName = generate(umlProperty.type, "name")
			} else {
				typeName = "void*"
			}
			
			//complex typed property, owned complex typed property
			if (umlProperty.type !== null && umlProperty.type.eClass.name == "Class" && !(umlProperty.aggregation == AggregationKind.COMPOSITE_LITERAL)){
				typeName = typeName + "*"
			}		
			
		} else {
			println("PropertyTemplate: unknown context")
		}

		// Rückgabe der Werte
		'''«typeName» «name»;'''
	}

}
