package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import java.nio.file.Paths
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.InstanceSpecification
import org.eclipse.uml2.uml.Model

class MainTemplate implements Template<Model> {

	override generateCode(CodegenInterface it, Model umlModel, String context) {
		val instances = umlModel.allOwnedElements.filter(InstanceSpecification).filter [ i |
			i.classifiers.size == 1 && i.classifiers.head instanceof Class && null !== (i.classifiers.head as Class).classifierBehavior
		]
		val includes = instances.flatMap[classifiers].filter(Class).toSet.map[cls | it.getPath(cls, "declaration")?.toString?.replace("\\", "/")].filterNull.toList
		//addiert (void) warrning beim bauen gefixed
		'''	
			«FOR path : includes.sort»
				#include "«path»"
			«ENDFOR»
			
			int main(void) {
				«FOR inst : instances»
					«it.generate((inst.classifiers.head as Class).classifierBehavior, "name")»(&«it.generate(inst, "name")»);
				«ENDFOR»
				
				return 0;
			}
		'''
		
	}
	
	override getPath(Model object, String context) {
		return Paths.get("main.c")
	}

}
