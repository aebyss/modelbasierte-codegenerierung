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
		val isReturn = context == "return"
		val isInout = umlParameter.direction == ParameterDirectionKind.INOUT_LITERAL
		val isOut = umlParameter.direction == ParameterDirectionKind.OUT_LITERAL
		val isClass = umlParameter.type instanceof Class

		// Basis-Typname float, MyClass
		var typeName = if (umlParameter.type !== null)
			it.generate(umlParameter.type, "typename")
		else
			"void*"
		
		//angepasst	
		if (isClass && (isOut || isInout)) {
			typeName += "**"
		} else if (isClass || isOut || isInout) {
			typeName += "*"
		}

		val isClassType = umlParameter.type instanceof Class
		val isOutOrInout = umlParameter.direction == ParameterDirectionKind.OUT_LITERAL ||
		                   umlParameter.direction == ParameterDirectionKind.INOUT_LITERAL

		if (isClassType || isOutOrInout) {
			if (!typeName.trim.endsWith("*")) {
				typeName += "*"
			}
		}
		

		val name = if (!isReturn) umlParameter.name ?: "unnamed" else ""

		if (name == "") {
			'''«typeName»'''
		} else {
			'''«typeName» «name»'''
		}
	}
}
	