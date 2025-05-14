package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Artifact

class ArtifactTemplate implements Template<Artifact> {

    override generateCode(CodegenInterface it, Artifact artifact, String context) {
        switch (context) {
            case "type":
                return artifact.name
            default:
                return "// Unknown context: " + context
        }
    }
}
