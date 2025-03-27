package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Operation;

@SuppressWarnings("all")
public class OperationTemplate implements Template<Operation> {
  @Override
  public String generateCode(final CodegenInterface it, final Operation umlOperation, final String context) {
    return null;
  }
}
