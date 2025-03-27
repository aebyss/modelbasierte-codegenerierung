package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Parameter;

@SuppressWarnings("all")
public class ParameterTemplate implements Template<Parameter> {
  @Override
  public String generateCode(final CodegenInterface it, final Parameter umlParameter, final String context) {
    return null;
  }
}
