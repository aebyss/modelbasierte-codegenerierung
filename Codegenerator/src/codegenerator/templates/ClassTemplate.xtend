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
import org.eclipse.uml2.uml.Dependency
import org.eclipse.uml2.uml.Artifact
import org.eclipse.uml2.uml.InstanceSpecification
import org.eclipse.uml2.uml.Slot

class ClassTemplate implements Template<Class> {

	override String generateCode(CodegenInterface it, Class umlClass, String context) {
		// TODO: Aufgabe 3
		val modelName = umlClass.nearestPackage?.name ?: "Model"
		switch (context) {
			case "declaration": {
			val className = umlClass.name ?: "UnnamedClass"
			val structName = modelName + "_" + className
				val model = umlClass.model
				val instances = if (model !== null)
					model.allOwnedElements
						.filter(typeof(InstanceSpecification))
						.filter[it.classifiers.contains(umlClass)]
				else emptyList

				val externs = instances.map[inst |
					"extern " + structName + " " + modelName + "_" + inst.name + ";"
				].join("\n")

				return '''
					#ifndef «modelName.toUpperCase»_«className.toUpperCase»_H
					#define «modelName.toUpperCase»_«className.toUpperCase»_H

					typedef struct «structName»_struct {
					} «structName»;

					«externs»

					#endif
				'''.toString
			}
						
			
			case "implementation": {
				val instances = if (umlClass.eResource !== null)
				umlClass.eResource.allContents.toIterable
					.filter(typeof(InstanceSpecification))
					.filter[it.classifiers.contains(umlClass)]
				else
					emptyList

				return '''
					#include "«umlClass.name».h"

					«FOR operation : umlClass.ownedOperations»
						«generate(operation, "implementation")»
					«ENDFOR»

					«FOR inst : instances»
						«generateInstanceCode(it, modelName, umlClass, inst)»
					«ENDFOR»
				'''.toString
			}
				
		}

	}
	
	
	
	def String generateInstanceCode(CodegenInterface it, String modelName, Class cls, InstanceSpecification inst) {
	val instanceName = modelName + "_" + inst.name
	val structName = modelName + "_" + cls.name

	if (inst.slots.empty) {
		return '''«structName» «instanceName» = {
		};
		'''
	} else {
		return '''
			«structName» «instanceName» = {
				«FOR slot : inst.slots SEPARATOR ",\n\t"»
					«IF slot.definingFeature !== null»
						.«slot.definingFeature.name» = …
					«ELSE»
						/* missing definingFeature */
					«ENDIF»
				«ENDFOR»
			};
		'''
	}
}
	
	

	// //////////////////////////////////////////////////////////////////
	// ab hier war teils schon gegeben /////////////////////////////////
	def String generateIncludes(CodegenInterface it, Class umlClass) {
		val types = new HashSet<Type>()
		
		for (property : umlClass.ownedAttributes) {
			if (property.type !== null && (property.type instanceof Class || property.type instanceof Enumeration)) {
				types.add(property.type)
			}
		}
		for (operation : umlClass.ownedOperations) {
			for (parameter : operation.ownedParameters) {
				if (parameter.type !== null && (parameter.type instanceof Class || parameter.type instanceof Enumeration)) {
					types.add(parameter.type)
				}
			}
		}
		
		//für dependencies	
		//alle beziehungen durchlaufen und und schauen ist es eine dependency
		for (rel : umlClass.relationships) {
			if (rel instanceof Dependency) {
				val dep = rel as Dependency
				types.addAll(dep.suppliers.filter(Type))
			}
		}
		
		

		val includes = new HashSet<String>()
		for (type : types) {
			if (type !== null) {
				includes += "#include \"" + generatePath(umlClass, type) + "\""
			}
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
