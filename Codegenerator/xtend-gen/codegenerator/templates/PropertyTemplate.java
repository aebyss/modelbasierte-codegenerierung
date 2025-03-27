package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Property;

@SuppressWarnings("all")
public class PropertyTemplate implements Template<Property> {
  @Override
  public String generateCode(final CodegenInterface it, final Property umlProperty, final String context) {
    return null;
  }
}
