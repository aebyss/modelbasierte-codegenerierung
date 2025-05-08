package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.Enumeration

class TypeTemplate implements Template<Type> {

	override generateCode(CodegenInterface it, Type umlType, String context) {
		switch umlType {
			PrimitiveType:
				it.generate(umlType, "name")
			Class: '''umlType.«generate(umlType, "name")»*'''
			Enumeration: '''umlType.«generate(umlType, "name")»'''
			
			case null:
				"void*"
			default: '''<codegenerator for type "«umlType.eClass.name»" is not yet implemented>'''
		}
	}

}
