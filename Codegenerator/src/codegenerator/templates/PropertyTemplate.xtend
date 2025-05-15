package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.AggregationKind
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Enumeration

class PropertyTemplate implements Template<Property> {

	val String SEPARATOR = "_"
	
	override generateCode(CodegenInterface it, Property umlProperty, String context) {
        // Aufgabe 1, 2
                
        
        // Parameter (sowie Type) ist ein NamedElement
		var name = umlProperty.name
		var typeName = "void*"
		if (umlProperty.type !== null) {
			typeName = it.generate(umlProperty.type, "typename")
		}
		var pointer = ""
		var upperBound = ""
		
		// Parameter hat keinen oder einen Type
		if (umlProperty.type instanceof Class){
			typeName = typeName
			pointer = "*"
		} 
		else if (umlProperty.type instanceof Enumeration){
			// bisher keine extra Behandlung
		}
		else /* ist PrimitiveType */{
			// bisher keine extra Behandlung
		}
		
		// Property hat einen AggregationKind (COMPOSITE oder NONE)
		if ((umlProperty.aggregation == AggregationKind.COMPOSITE_LITERAL)){
			pointer = ""
		} else {
			// Wenn Array -> Überprüfen des upper-Bounds
			if (umlProperty.upper > 1) {
				upperBound = "[" + umlProperty.upper + "]"
			} else if (umlProperty.upper == -1){
				// Ein Upper-Bound von -1 unbegrenzt
				pointer += "*"
			}
		}
        
        '''«typeName»«pointer» «name»«upperBound»;'''
    }

}