package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Class

class ParameterTemplate implements Template<Parameter> {

	override generateCode(CodegenInterface it, Parameter umlParameter, String context) {
		// Aufgabe 1
		var typeName = ""
		var name = ""

		switch context {
			case "return": {
				if (umlParameter.direction === ParameterDirectionKind.RETURN_LITERAL) {
					if (umlParameter.type !== null) {
						typeName = generate(umlParameter.type, "name")
					} else {
						typeName = "void*"
					}
				}
			}
			default: {
				name = generate(umlParameter, "name")
				if (umlParameter.type !== null) {
					typeName = generate(umlParameter.type, "name")
				} else {
					typeName = "void*"
				}
			}
		}
		
		// pointer (*) for: complext type + INOUT / OUT
		if (umlParameter.type instanceof Class ||
			umlParameter.direction == ParameterDirectionKind.INOUT_LITERAL ||
			umlParameter.direction == ParameterDirectionKind.OUT_LITERAL
			) {
			typeName = typeName + "*"
		}

		// double pointer (**) for: complex type + INOUT / OUT
		if (umlParameter.type instanceof Class && (
			umlParameter.direction == ParameterDirectionKind.INOUT_LITERAL ||
			umlParameter.direction == ParameterDirectionKind.OUT_LITERAL
			)) {
			typeName = typeName + "*"
		}

		// Rückgabe der Werte
		if (name == "") {
			'''«typeName»'''
		} else {
			'''«typeName» «name»'''
		}
	}
}
