package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.Enumeration

class TypeTemplate implements Template<Type> {

	override generateCode(CodegenInterface it, Type umlType, String context) {
		if (umlType === null) {
			return "void*"
		}

		switch umlType {
			PrimitiveType:
				it.generate(umlType, "name")
			Class:
				'''«it.generate(umlType, "name")»*'''
			Enumeration:
				it.generate(umlType, "name")
			default:
				'''<codegen "«umlType.eClass.name»" not implemented>'''
		}
	}
}
