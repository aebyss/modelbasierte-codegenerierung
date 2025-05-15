package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class OpaqueBehaviorTemplate implements Template<OpaqueBehavior> {
  @Override
  public String generateCode(final CodegenInterface it, final OpaqueBehavior umlBehavior, final String context) {
    if (context != null) {
      switch (context) {
        case "name":
          Element _owner = umlBehavior.getOwner();
          final org.eclipse.uml2.uml.Class class_ = ((org.eclipse.uml2.uml.Class) _owner);
          final String className = it.generate(class_, "name");
          String _name = umlBehavior.getName();
          final String result = ((className + "_") + _name);
          InputOutput.<String>println(("OpaqueBehaviorTemplate.name => " + result));
          return result;
        default:
          return IterableExtensions.<String>head(umlBehavior.getBodies());
      }
    } else {
      return IterableExtensions.<String>head(umlBehavior.getBodies());
    }
  }
}
