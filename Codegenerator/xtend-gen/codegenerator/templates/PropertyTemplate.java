package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.base.Objects;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class PropertyTemplate implements Template<Property> {
  @Override
  public String generateCode(final CodegenInterface it, final Property umlProperty, final String context) {
    String _xblockexpression = null;
    {
      String typeName = "";
      String name = "";
      boolean _equals = Objects.equal(context, "attribute");
      if (_equals) {
        name = it.generate(umlProperty, "name");
        Type _type = umlProperty.getType();
        boolean _tripleNotEquals = (_type != null);
        if (_tripleNotEquals) {
          typeName = it.generate(umlProperty.getType(), "name");
        } else {
          typeName = "void*";
        }
        if ((((umlProperty.getType() != null) && Objects.equal(umlProperty.getType().eClass().getName(), "Class")) && (!Objects.equal(umlProperty.getAggregation(), AggregationKind.COMPOSITE_LITERAL)))) {
          typeName = (typeName + "*");
        }
      } else {
        InputOutput.<String>println("PropertyTemplate: unknown context");
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(typeName);
      _builder.append(" ");
      _builder.append(name);
      _builder.append(";");
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
}
