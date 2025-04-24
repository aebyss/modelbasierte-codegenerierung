package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.StructuredClassifier

class StructuredClassifierTemplate implements Template<StructuredClassifier> {

	override generateCode(CodegenInterface it, StructuredClassifier umlClassifier, String context) {
		// TODO: Aufgabe 2
		
		switch context {
			case "typedefinition": {
				val name = umlClassifier.qualifiedName.replace("::", "_") ?: umlClassifier.name
				
				//properties der klasse holen
				val properties = umlClassifier.ownedAttributes
				
				val attributeStrings = properties.map[p |
					"\t" + it.generate(p, "attribute")
				].join("\n")
				
				'''
				typedef struct «name»_struct {
				«IF attributeStrings.empty»
				«ELSE»
					«attributeStrings»
				«ENDIF»
				} «name»;
				'''.toString
			}
			default: '''// diff context: «context»'''
		}
		
		
	}

}
