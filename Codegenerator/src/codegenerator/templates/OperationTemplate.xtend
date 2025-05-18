package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.OpaqueBehavior

class OperationTemplate implements Template<Operation> {

	override generateCode(CodegenInterface it, Operation umlOperation, String context) {
		val class_ = umlOperation.class_
		val className = if (class_ !== null) it.generate(class_, "name") else "UnknownClass"
		val isStatic = umlOperation.isStatic

		// Rückgabetyp
		val returnParam = umlOperation.ownedParameters.findFirst [
			direction == ParameterDirectionKind::RETURN_LITERAL
		]
		val returnType = returnParam !== null ? it.generate(returnParam, "return") : "void"

		// Nicht-Return-Parameter
		val nonReturnParams = umlOperation.ownedParameters.filter [
			direction === null || direction != ParameterDirectionKind::RETURN_LITERAL
		]
		val paramStrings = nonReturnParams.map[p|it.generate(p, "parameter")]

		// me-Parameter bei nicht-statischen Methoden
		val baseParams = if (!isStatic)
				#["%s* const me".format(className)]
			else
				#[]
		val allParamsList = baseParams + paramStrings
		val allParams = if(allParamsList.empty) "void" else allParamsList.join(", ")
	
		// Signatur
		val signature = '''«returnType» «className»_«umlOperation.name»(«allParams»)'''

		switch context {
			case "declaration":
				signature + ";"
			case "implementation": {
				val body = umlOperation.methods.filter(typeof(OpaqueBehavior)).map[it as OpaqueBehavior].findFirst [
					it.languages.contains("C")
				]?.bodies?.head ?: "// no implementation found"

				'''
				«signature» {
					«body»
				}
				'''.toString.trim
			}
			case "name": {
				val name = className + "_" + umlOperation.name
				println("️OperationTemplate.name => " + name)
				return name
			}
			default: '''// Unknown context: «context»'''
		}
	}
}