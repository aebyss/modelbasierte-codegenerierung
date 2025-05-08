package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import java.util.Objects;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class PropertyTemplate implements Template<Property> {
  private final String SEPARATOR = "_";

  @Override
  public String generateCode(final CodegenInterface it, final Property umlProperty, final String context) {
    String _xblockexpression = null;
    {
      String name = umlProperty.getName();
      String typeName = "void*";
      Type _type = umlProperty.getType();
      boolean _tripleNotEquals = (_type != null);
      if (_tripleNotEquals) {
        typeName = it.generate(umlProperty.getType(), "name");
      }
      String pointer = "";
      String upperBound = "";
      Type _type_1 = umlProperty.getType();
      if ((_type_1 instanceof org.eclipse.uml2.uml.Class)) {
        typeName = typeName;
        pointer = "*";
      } else {
        Type _type_2 = umlProperty.getType();
        if ((_type_2 instanceof Enumeration)) {
        } else {
        }
      }
      AggregationKind _aggregation = umlProperty.getAggregation();
      boolean _equals = Objects.equals(_aggregation, AggregationKind.COMPOSITE_LITERAL);
      if (_equals) {
        pointer = "";
      } else {
        int _upper = umlProperty.getUpper();
        boolean _greaterThan = (_upper > 1);
        if (_greaterThan) {
          int _upper_1 = umlProperty.getUpper();
          String _plus = ("[" + Integer.valueOf(_upper_1));
          String _plus_1 = (_plus + "]");
          upperBound = _plus_1;
        } else {
          int _upper_2 = umlProperty.getUpper();
          boolean _equals_1 = (_upper_2 == (-1));
          if (_equals_1) {
            String _pointer = pointer;
            pointer = (_pointer + "*");
          }
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(typeName);
      _builder.append(pointer);
      _builder.append(" ");
      _builder.append(name);
      _builder.append(upperBound);
      _builder.append(";");
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
}
