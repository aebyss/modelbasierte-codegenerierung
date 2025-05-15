package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.Class

class OpaqueBehaviorTemplate implements Template<OpaqueBehavior> {
	
	override generateCode(CodegenInterface it, OpaqueBehavior umlBehavior, String context) {
		switch (context) {
			case "name": {
				val class_ = umlBehavior.owner as Class
				val className = it.generate(class_, "name")
				val result = className + "_" + umlBehavior.name
				println("OpaqueBehaviorTemplate.name => " + result)
				return result
			}
			default: {
				return umlBehavior.bodies.head
			}
		}
	}	
	
}