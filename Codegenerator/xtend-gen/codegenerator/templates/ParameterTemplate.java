package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.base.Objects;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ParameterTemplate implements Template<Parameter> {
  @Override
  public String generateCode(final CodegenInterface it, final Parameter umlParameter, final String context) {
    String _xblockexpression = null;
    {
      String typeName = "";
      String name = "";
      if (context != null) {
        switch (context) {
          case "return":
            ParameterDirectionKind _direction = umlParameter.getDirection();
            boolean _tripleEquals = (_direction == ParameterDirectionKind.RETURN_LITERAL);
            if (_tripleEquals) {
              Type _type = umlParameter.getType();
              boolean _tripleNotEquals = (_type != null);
              if (_tripleNotEquals) {
                typeName = it.generate(umlParameter.getType(), "name");
              } else {
                typeName = "void*";
              }
            }
            break;
          default:
            {
              name = it.generate(umlParameter, "name");
              Type _type_1 = umlParameter.getType();
              boolean _tripleNotEquals_1 = (_type_1 != null);
              if (_tripleNotEquals_1) {
                typeName = it.generate(umlParameter.getType(), "name");
              } else {
                typeName = "void*";
              }
            }
            break;
        }
      } else {
        {
          name = it.generate(umlParameter, "name");
          Type _type_1 = umlParameter.getType();
          boolean _tripleNotEquals_1 = (_type_1 != null);
          if (_tripleNotEquals_1) {
            typeName = it.generate(umlParameter.getType(), "name");
          } else {
            typeName = "void*";
          }
        }
      }
      if ((((umlParameter.getType() instanceof org.eclipse.uml2.uml.Class) || 
        Objects.equal(umlParameter.getDirection(), ParameterDirectionKind.INOUT_LITERAL)) || 
        Objects.equal(umlParameter.getDirection(), ParameterDirectionKind.OUT_LITERAL))) {
        typeName = (typeName + "*");
      }
      if (((umlParameter.getType() instanceof org.eclipse.uml2.uml.Class) && 
        (Objects.equal(umlParameter.getDirection(), ParameterDirectionKind.INOUT_LITERAL) || 
          Objects.equal(umlParameter.getDirection(), ParameterDirectionKind.OUT_LITERAL)))) {
        typeName = (typeName + "*");
      }
      String _xifexpression = null;
      boolean _equals = Objects.equal(name, "");
      if (_equals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(typeName);
        _xifexpression = _builder.toString();
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(typeName);
        _builder_1.append(" ");
        _builder_1.append(name);
        _xifexpression = _builder_1.toString();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
