package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Enumeration

class ParameterTemplate implements Template<Parameter> {

	val String SEPARATOR = "_"
	
	override generateCode(CodegenInterface it, Parameter umlParameter, String context) {
		// Aufgabe 1, 2

		// Parameter (sowie Type) ist ein NamedElement
		var name = ""
		var typeName = "void*"
		if (umlParameter.type !== null) {
			typeName = umlParameter.type.name
		}
		
		// Parameter hat keinen oder einen Type
		if (umlParameter.type instanceof Class){
			typeName = typeName + "*"
		} else if (umlParameter.type instanceof Enumeration){
			if (umlParameter.type.package !== null){
				typeName = umlParameter.type.package.name + SEPARATOR + typeName	
			}
			// ODER MIT
			// typeName = generate(umlParameter.type, "name")
			
		}
		
		// Parameter hat einen ParameterDirectionKind
		switch umlParameter.direction {
			case ParameterDirectionKind.RETURN_LITERAL: {
			}
			case ParameterDirectionKind.INOUT_LITERAL: {
				//name = generate(umlParameter, "name")
				name = umlParameter.name
				typeName = typeName + "*"
			}
			case ParameterDirectionKind.OUT_LITERAL: {
				name = umlParameter.name
				typeName = typeName + "*"
			}
			//case ParameterDirectionKind.IN_LITERAL
			default: {
				name = umlParameter.name
			}
		}
		
		// Rückgabe der Werte
		if (name == "") {
			'''«typeName»'''
		} else {
			'''«typeName» «name»'''
		}
		
	}
}
