package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.StructuredClassifier

class StructuredClassifierTemplate implements Template<StructuredClassifier> {

	override generateCode(CodegenInterface it, StructuredClassifier umlClassifier, String context) {
		// TODO: Aufgabe 2
		
		switch context {
			case "typedefinition": {
				
				//generate(umlEnum, "name")
				val name = umlClassifier.qualifiedName.replace("::", "_") ?: umlClassifier.name
				
				
				//alle attribute sammeln	
				val properties = umlClassifier.ownedAttributes
				
				
				//attribut zeilen bauen	
				val attributeStrings = properties.map[p |
					"\t" + it.generate(p, "attribute")
				].join("\n")
				
				//struct block bauen	
				
				//kompakter machen und im enumTemplate 
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
