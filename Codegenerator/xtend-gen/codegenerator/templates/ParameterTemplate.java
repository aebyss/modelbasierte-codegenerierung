package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.base.Objects;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
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
              typeName = it.generate(umlParameter.getType(), "name");
              boolean _equals = Objects.equal(typeName, "");
              if (_equals) {
                typeName = "void*";
              }
            }
            break;
          default:
            {
              name = it.generate(umlParameter, "name");
              typeName = it.generate(umlParameter.getType(), "name");
              boolean _equals_1 = Objects.equal(typeName, "");
              if (_equals_1) {
                typeName = "void*";
              }
            }
            break;
        }
      } else {
        {
          name = it.generate(umlParameter, "name");
          typeName = it.generate(umlParameter.getType(), "name");
          boolean _equals_1 = Objects.equal(typeName, "");
          if (_equals_1) {
            typeName = "void*";
          }
        }
      }
      String _xifexpression = null;
      boolean _equals_1 = Objects.equal(name, "");
      if (_equals_1) {
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
