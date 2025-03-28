package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import java.nio.file.Path
import java.nio.file.Paths
import java.util.HashSet
import java.util.LinkedList
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.Type

class ClassTemplate implements Template<Class> {

	override String generateCode(CodegenInterface it, Class umlClass, String context) {
		// TODO: Aufgabe 3
	}

	def String generateIncludes(CodegenInterface it, Class umlClass) {
		val types = new HashSet<Type>()

		for (property : umlClass.ownedAttributes) {
			if (property.type instanceof Class) {
				types.add(property.type)
			}
		}

		for (operation : umlClass.ownedOperations) {
			for (parameter : operation.ownedParameters) {
				if (parameter.type instanceof Class) {
					types.add(parameter.type)
				}
			}
		}

		'''
			«FOR type : types AFTER '\n'»
				#include "«generatePath(umlClass, type)»"
			«ENDFOR»
		'''
	}

	def generatePath(CodegenInterface it, NamedElement from, NamedElement to) {
		val fromPath = getPath(from, "declaration")
		val toPath = getPath(to, "declaration")
		
		val relPath = fromPath.parent?.relativize(toPath) ?: toPath
		return relPath.join("/")
	}

	override Path getPath(Class umlClass, String context) {
		var path = new LinkedList<String>()
		switch context {
			case "declaration": {
				path.addFirst(umlClass.name + ".h")
			}
			case "implementation": {
				path.addFirst(umlClass.name + ".c")
			}
			default:
				return null
		}
		var parent = umlClass.namespace
		while (null !== parent) {
			path.addFirst(parent.name)
			parent = parent.namespace
		}
		return Paths.get(path.head, path.tail)
	}
}
