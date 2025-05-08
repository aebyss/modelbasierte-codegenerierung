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
      String name = "";
      String _xifexpression = null;
      Type _type = umlParameter.getType();
      boolean _tripleNotEquals = (_type != null);
      if (_tripleNotEquals) {
        _xifexpression = it.generate(umlParameter.getType(), "name");
      } else {
        _xifexpression = "void*";
      }
      String typeName = _xifexpression;
      Type _type_1 = umlParameter.getType();
      boolean _tripleNotEquals_1 = (_type_1 != null);
      if (_tripleNotEquals_1) {
        typeName = it.generate(umlParameter.getType(), "name");
      }
      Type _type_2 = umlParameter.getType();
      if ((_type_2 instanceof org.eclipse.uml2.uml.Class)) {
        typeName = (typeName + "*");
      }
      ParameterDirectionKind _direction = umlParameter.getDirection();
      if (_direction != null) {
        switch (_direction) {
          case RETURN_LITERAL:
            break;
          case INOUT_LITERAL:
            name = umlParameter.getName();
            typeName = (typeName + "*");
            break;
          case OUT_LITERAL:
            name = umlParameter.getName();
            typeName = (typeName + "*");
            break;
          default:
            name = umlParameter.getName();
            break;
        }
      } else {
        name = umlParameter.getName();
      }
      String _xifexpression_1 = null;
      boolean _equals = Objects.equals(name, "");
      if (_equals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(typeName);
        _xifexpression_1 = _builder.toString();
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(typeName);
        _builder_1.append(" ");
        _builder_1.append(name);
        _xifexpression_1 = _builder_1.toString();
      }
      _xblockexpression = _xifexpression_1;
    }
    return _xblockexpression;
  }
}
