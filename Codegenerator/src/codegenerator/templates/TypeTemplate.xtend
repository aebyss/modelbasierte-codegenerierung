package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.Artifact
import java.util.LinkedList

class TypeTemplate implements Template<Type> {

override generateCode(CodegenInterface it, Type umlType, String context) {
	if (umlType === null) return "void*"

	switch umlType {
		PrimitiveType:
			mapPrimitive(umlType.name)

		Class: {
			val pathParts = new LinkedList<String>()
			var parent = umlType.namespace
			while (parent !== null) {
				pathParts.addFirst(parent.name)
				parent = parent.namespace
			}
			pathParts.add(umlType.name)
			return pathParts.join("_")
		}

		Enumeration: {
			val pathParts = new LinkedList<String>()
			var parent = umlType.namespace
			while (parent !== null) {
				pathParts.addFirst(parent.name)
				parent = parent.namespace
			}
			pathParts.add(umlType.name)
			return pathParts.join("_")
		}

		Artifact: {
			val name = umlType.name
			if (name != null && name.endsWith(".h"))
				return name.replace(".h", "") // e.g., "stdint.h" → "stdint"
			else
				return name ?: "artifact_type"
		}

		default:
			'''<codegen "«umlType.eClass.name»" not implemented>'''
	}
}

def String mapPrimitive(String name) {
	switch name {
		case "Integer": "int32_t"
		case "Real": "float"
		case "Boolean": "bool"
		case "String": "char*"
		default: name
	}
}
	
	}