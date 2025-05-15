package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Artifact;

@SuppressWarnings("all")
public class ArtifactTemplate implements Template<Artifact> {
  @Override
  public String generateCode(final CodegenInterface it, final Artifact artifact, final String context) {
    if (context != null) {
      switch (context) {
        case "type":
          return artifact.getName();
        default:
          return ("// Unknown context: " + context);
      }
    } else {
      return ("// Unknown context: " + context);
    }
  }
}
