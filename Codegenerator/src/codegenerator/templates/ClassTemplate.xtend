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
import org.eclipse.uml2.uml.Enumeration

class ClassTemplate implements Template<Class> {

	override String generateCode(CodegenInterface it, Class umlClass, String context) {
		// TODO: Aufgabe 3
		val name = generate(umlClass, "name")
		switch (context) {
			case "declaration": {
				return '''
					#ifndef «name.toUpperCase»_H
					#define «name.toUpperCase»_H
					
					«this.generateIncludes(it, umlClass)»
					«it.generate(umlClass, "typedefinition")»
					
					«FOR operation : umlClass.ownedOperations»
						«generate(operation, "declaration")»
						
					«ENDFOR»
					#endif
				'''.toString
			}
			case "implementation": {
				return '''
					#include "«umlClass.name».h"
					«FOR operation : umlClass.ownedOperations»
						
						«generate(operation, "implementation")»
					«ENDFOR»
				'''.toString
			}
		}

	}

	// //////////////////////////////////////////////////////////////////
	// ab hier war teils schon gegeben /////////////////////////////////
	def String generateIncludes(CodegenInterface it, Class umlClass) {
		val types = new HashSet<Type>()

		for (property : umlClass.ownedAttributes) {
			if (property.type instanceof Class || property.type instanceof Enumeration) {
				types.add(property.type)
		}
		}

		for (operation : umlClass.ownedOperations) {
			for (parameter : operation.ownedParameters) {
				if (parameter.type instanceof Class || parameter.type instanceof Enumeration) {
					types.add(parameter.type)
				}
			}
		}
		

		val includes = new HashSet<String>()
		for (type : types) {
			includes += "#include \"" + generatePath(umlClass, type) + "\""
		}

		'''
		«FOR include : includes.toList.sort AFTER '\n'»
		«include»
		«ENDFOR»
		'''
		
	}

	def generatePath(CodegenInterface it, NamedElement from, NamedElement to) {
		val fromPath = getPath(from, "declaration")
		val toPath = getPath(to, "declaration")
		
		if (fromPath === null || toPath === null) {
			val relPath = fromPath.parent?.relativize(toPath) ?: toPath
			return relPath.toString.replace("\\", "/")
		}
		
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
