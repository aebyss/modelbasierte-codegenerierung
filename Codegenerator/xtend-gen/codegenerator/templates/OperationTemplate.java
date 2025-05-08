package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class OperationTemplate implements Template<Operation> {
  @Override
  public String generateCode(final CodegenInterface it, final Operation umlOperation, final String context) {
    String _xblockexpression = null;
    {
      final String className = umlOperation.getClass_().getQualifiedName().replace("::", "_");
      final boolean isStatic = umlOperation.isStatic();
      final Function1<Parameter, Boolean> _function = (Parameter it_1) -> {
        ParameterDirectionKind _direction = it_1.getDirection();
        return Boolean.valueOf(Objects.equals(_direction, ParameterDirectionKind.RETURN_LITERAL));
      };
      final Parameter returnParam = IterableExtensions.<Parameter>findFirst(umlOperation.getOwnedParameters(), _function);
      String _xifexpression = null;
      if ((returnParam != null)) {
        _xifexpression = it.generate(returnParam, "return");
      } else {
        _xifexpression = "void";
      }
      final String returnType = _xifexpression;
      final Function1<Parameter, Boolean> _function_1 = (Parameter it_1) -> {
        return Boolean.valueOf((Objects.equals(it_1.getDirection(), null) || (!Objects.equals(it_1.getDirection(), ParameterDirectionKind.RETURN_LITERAL))));
      };
      final Iterable<Parameter> nonReturnParams = IterableExtensions.<Parameter>filter(umlOperation.getOwnedParameters(), _function_1);
      final Function1<Parameter, String> _function_2 = (Parameter p) -> {
        return it.generate(p, "parameter");
      };
      final Iterable<String> paramStrings = IterableExtensions.<Parameter, String>map(nonReturnParams, _function_2);
      List<String> _xifexpression_1 = null;
      if ((!isStatic)) {
        String _format = String.format("%s* const me", className);
        _xifexpression_1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_format));
      } else {
        _xifexpression_1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList());
      }
      final List<String> baseParams = _xifexpression_1;
      final Iterable<String> allParamsList = Iterables.<String>concat(baseParams, paramStrings);
      String _xifexpression_2 = null;
      boolean _isEmpty = IterableExtensions.isEmpty(allParamsList);
      if (_isEmpty) {
        _xifexpression_2 = "void";
      } else {
        _xifexpression_2 = IterableExtensions.join(allParamsList, ", ");
      }
      final String allParams = _xifexpression_2;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(returnType);
      _builder.append(" ");
      _builder.append(className);
      _builder.append("_");
      String _name = umlOperation.getName();
      _builder.append(_name);
      _builder.append("(");
      _builder.append(allParams);
      _builder.append(")");
      final String signature = _builder.toString();
      String _switchResult = null;
      if (context != null) {
        switch (context) {
          case "declaration":
            _switchResult = (signature + ";");
            break;
          case "implementation":
            String _xblockexpression_1 = null;
            {
              String _elvis = null;
              final Function1<OpaqueBehavior, OpaqueBehavior> _function_3 = (OpaqueBehavior it_1) -> {
                return ((OpaqueBehavior) it_1);
              };
              final Function1<OpaqueBehavior, Boolean> _function_4 = (OpaqueBehavior it_1) -> {
                return Boolean.valueOf(it_1.getLanguages().contains("C"));
              };
              OpaqueBehavior _findFirst = IterableExtensions.<OpaqueBehavior>findFirst(IterableExtensions.<OpaqueBehavior, OpaqueBehavior>map(Iterables.<OpaqueBehavior>filter(umlOperation.getMethods(), OpaqueBehavior.class), _function_3), _function_4);
              EList<String> _bodies = null;
              if (_findFirst!=null) {
                _bodies=_findFirst.getBodies();
              }
              String _head = null;
              if (_bodies!=null) {
                _head=IterableExtensions.<String>head(_bodies);
              }
              if (_head != null) {
                _elvis = _head;
              } else {
                _elvis = "// no implementation found";
              }
              final String body = _elvis;
              StringConcatenation _builder_1 = new StringConcatenation();
              _builder_1.append(signature);
              _builder_1.append(" {");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("\t");
              _builder_1.append(body, "\t");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("}");
              _builder_1.newLine();
              _xblockexpression_1 = _builder_1.toString().trim();
            }
            _switchResult = _xblockexpression_1;
            break;
          default:
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append("// Unknown context: ");
            _builder_1.append(context);
            _switchResult = _builder_1.toString();
            break;
        }
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("// Unknown context: ");
        _builder_1.append(context);
        _switchResult = _builder_1.toString();
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
}
