package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import java.nio.file.Paths
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.InstanceSpecification
import org.eclipse.uml2.uml.Model

class MainTemplate implements Template<Model> {

	override generateCode(CodegenInterface it, Model umlModel, String context) {
	val instances = umlModel.allOwnedElements
		.filter(typeof(InstanceSpecification))
		.filter[i |
			i.classifiers.size == 1 &&
			i.classifiers.head instanceof Class &&
			(i.classifiers.head as Class).classifierBehavior !== null
		]

	val includes = instances
		.map[inst | inst.classifiers.head as Class]
		.toSet
		.map[cls | it.getPath(cls, "declaration")]
		.filter[path | path !== null]

	return '''
		«FOR path : includes.sort»
		#include "«path.toString.replace("\\", "/")»"
		«ENDFOR»

		int main() {
			«FOR inst : instances»
				«generate((inst.classifiers.head as Class).classifierBehavior, "name")»(&«generate(inst, "name")»);
			«ENDFOR»
			return 0;
		}
	'''.toString
}	
	
	override getPath(Model object, String context) {
		return Paths.get("main.c")
	}

}
