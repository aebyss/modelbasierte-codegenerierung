//package codegenerator.templates
//
//import codegenerator.CodegenInterface
//import codegenerator.Template
//import org.eclipse.uml2.uml.Operation
//import org.eclipse.uml2.uml.Parameter
//import org.eclipse.uml2.uml.ParameterDirectionKind
//import org.eclipse.uml2.uml.VisibilityKind
//import org.eclipse.uml2.uml.OpaqueBehavior
//
//class OperationTemplate implements Template<Operation> {
//
//	override generateCode(CodegenInterface it, Operation umlOperation, String context) {
//	switch context {
//		
//		//Funktions deklaration
//		case "declaration": {
//			//TODO Aufgabe 2
//			//qualif. Klassenname mit Paket statt nur Klasse 
//			val className = umlOperation.class_.qualifiedName.replace("::", "_")
//			//wegen static funktionen 
//			val isStatic = umlOperation.isStatic
//			
//			//finde return Parameter
//			val returnParam = umlOperation.ownedParameters.findFirst[
//				direction == ParameterDirectionKind::RETURN_LITERAL
//			]
//
//			val returnType = if (returnParam !== null)
//				it.generate(returnParam, "return")
//			else
//				"void"
//			
//			//alle Parameter sammeln die nicht4 return_literal
//			val nonReturnParams = umlOperation.ownedParameters.filter[
//				direction == null || direction != ParameterDirectionKind::RETURN_LITERAL
//			]
//			//Parameter Strings generieren
//			val paramStrings = nonReturnParams.map[p | it.generate(p, "parameter")]
//			
//			//me parameter nur bei nicht statischen methoden
//			val baseParams = if (!isStatic)
//				#["%s* const me".format(className)]
//			else
//				#[]
//			val allParamsList = baseParams + paramStrings
//			val allParams = if (allParamsList.empty) "void" else allParamsList.join(", ")
//			//gibt die Signatur zurück
//			'''«returnType» «className»_«umlOperation.name»(«allParams»);'''
//		}
//
//		case "implementation": {
//			val className = umlOperation.class_.qualifiedName.replace("::", "_")
//			val isStatic = umlOperation.isStatic
//
//			val returnParam = umlOperation.ownedParameters.findFirst[
//				direction == ParameterDirectionKind::RETURN_LITERAL
//			]
//
//			val returnType = if (returnParam !== null)
//				it.generate(returnParam, "return")
//			else
//				"void"
//
//			val nonReturnParams = umlOperation.ownedParameters.filter[
//				direction == null || direction != ParameterDirectionKind::RETURN_LITERAL
//			]
//			val paramStrings = nonReturnParams.map[p | it.generate(p, "parameter")]
//
//			val baseParams = if (!isStatic)
//				#["%s* const me".format(className)]
//			else
//				#[]
//			val allParamsList = baseParams + paramStrings
//			val allParams = if (allParamsList.empty) "void" else allParamsList.join(", ")
//			
//			//OpaqueBehaviour für Verhalten also Funktionsimplementierung
//			val body = umlOperation.methods
//				.filter(typeof(OpaqueBehavior))
//				.map[it as OpaqueBehavior]
//				.findFirst[ it.languages.contains("C") ]
//				?.bodies?.head ?: "// no implementation found"
//
//			'''
//			«returnType» «className»_«umlOperation.name»(«allParams») {
//				«body»
//			}
//			'''.toString.trim
//		}
//
//		default: '''// Unknown context: «context»'''
//	}
//}
//		
//}

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
		val allParams = if(allParamsList.empty) "" else allParamsList.join(", ")
	
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
			default: '''// Unknown context: «context»'''
		}
	}
}