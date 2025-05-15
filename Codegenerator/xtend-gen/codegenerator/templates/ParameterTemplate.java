package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import java.util.Objects;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ParameterTemplate implements Template<Parameter> {
  private final String SEPARATOR = "_";

  @Override
  public String generateCode(final CodegenInterface it, final Parameter umlParameter, final String context) {
    String _xblockexpression = null;
    {
      final boolean isReturn = Objects.equals(context, "return");
      ParameterDirectionKind _direction = umlParameter.getDirection();
      final boolean isInout = Objects.equals(_direction, ParameterDirectionKind.INOUT_LITERAL);
      ParameterDirectionKind _direction_1 = umlParameter.getDirection();
      final boolean isOut = Objects.equals(_direction_1, ParameterDirectionKind.OUT_LITERAL);
      Type _type = umlParameter.getType();
      final boolean isClass = (_type instanceof org.eclipse.uml2.uml.Class);
      String _xifexpression = null;
      Type _type_1 = umlParameter.getType();
      boolean _tripleNotEquals = (_type_1 != null);
      if (_tripleNotEquals) {
        _xifexpression = it.generate(umlParameter.getType(), "typename");
      } else {
        _xifexpression = "void*";
      }
      String typeName = _xifexpression;
      if ((isClass && (isOut || isInout))) {
        String _typeName = typeName;
        typeName = (_typeName + "**");
      } else {
        if (((isClass || isOut) || isInout)) {
          String _typeName_1 = typeName;
          typeName = (_typeName_1 + "*");
        }
      }
      Type _type_2 = umlParameter.getType();
      final boolean isClassType = (_type_2 instanceof org.eclipse.uml2.uml.Class);
      final boolean isOutOrInout = (Objects.equals(umlParameter.getDirection(), ParameterDirectionKind.OUT_LITERAL) || 
        Objects.equals(umlParameter.getDirection(), ParameterDirectionKind.INOUT_LITERAL));
      if ((isClassType || isOutOrInout)) {
        boolean _endsWith = typeName.trim().endsWith("*");
        boolean _not = (!_endsWith);
        if (_not) {
          String _typeName_2 = typeName;
          typeName = (_typeName_2 + "*");
        }
      }
      String _xifexpression_1 = null;
      if ((!isReturn)) {
        String _elvis = null;
        String _name = umlParameter.getName();
        if (_name != null) {
          _elvis = _name;
        } else {
          _elvis = "unnamed";
        }
        _xifexpression_1 = _elvis;
      } else {
        _xifexpression_1 = "";
      }
      final String name = _xifexpression_1;
      String _xifexpression_2 = null;
      boolean _equals = Objects.equals(name, "");
      if (_equals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(typeName);
        _xifexpression_2 = _builder.toString();
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(typeName);
        _builder_1.append(" ");
        _builder_1.append(name);
        _xifexpression_2 = _builder_1.toString();
      }
      _xblockexpression = _xifexpression_2;
    }
    return _xblockexpression;
  }
}
