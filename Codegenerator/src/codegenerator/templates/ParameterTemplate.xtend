package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.ParameterDirectionKind

class ParameterTemplate implements Template<Parameter> {
	
	override generateCode(CodegenInterface it, Parameter umlParameter, String context) {
		// TODO: Aufgabe 1
		
		var typeName = ""
		var name = ""
		
		switch context {
			case "return": {
				if (umlParameter.direction === ParameterDirectionKind.RETURN_LITERAL) {
					typeName = generate(umlParameter.type, "name")
					if (typeName == "") {
						typeName = "void*"
					}
				}
			}
			default: {
				name = generate(umlParameter, "name")
				typeName = generate(umlParameter.type, "name")
				if (typeName == "") {
					typeName = "void*"
				}	
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