package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class StructuredClassifierTemplate implements Template<StructuredClassifier> {
  @Override
  public String generateCode(final CodegenInterface it, final StructuredClassifier umlClassifier, final String context) {
    String _switchResult = null;
    if (context != null) {
      switch (context) {
        case "typedefinition":
          String _xblockexpression = null;
          {
            final String name = it.generate(umlClassifier, "name");
            final EList<Property> properties = umlClassifier.getOwnedAttributes();
            final Function1<Property, String> _function = (Property p) -> {
              String _generate = it.generate(p, "attribute");
              return ("\t" + _generate);
            };
            final String attributeStrings = IterableExtensions.join(ListExtensions.<Property, String>map(properties, _function), "\n");
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("typedef struct ");
            _builder.append(name);
            _builder.append("_struct {");
            _builder.newLineIfNotEmpty();
            {
              boolean _isEmpty = attributeStrings.isEmpty();
              if (_isEmpty) {
              } else {
                _builder.append(attributeStrings);
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("} ");
            _builder.append(name);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _xblockexpression = _builder.toString();
          }
          _switchResult = _xblockexpression;
          break;
        default:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("// diff context: ");
          _builder.append(context);
          _switchResult = _builder.toString();
          break;
      }
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("// diff context: ");
      _builder.append(context);
      _switchResult = _builder.toString();
    }
    return _switchResult;
  }
}
