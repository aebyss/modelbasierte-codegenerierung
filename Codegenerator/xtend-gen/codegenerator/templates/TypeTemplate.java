package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TypeTemplate implements Template<Type> {
  @Override
  public String generateCode(final CodegenInterface it, final Type umlType, final String context) {
    String _xblockexpression = null;
    {
      if ((umlType == null)) {
        return "void*";
      }
      String _switchResult = null;
      boolean _matched = false;
      if (umlType instanceof PrimitiveType) {
        _matched=true;
        _switchResult = it.generate(umlType, "name");
      }
      if (!_matched) {
        if (umlType instanceof org.eclipse.uml2.uml.Class) {
          _matched=true;
          StringConcatenation _builder = new StringConcatenation();
          String _generate = it.generate(umlType, "name");
          _builder.append(_generate);
          _builder.append("*");
          _switchResult = _builder.toString();
        }
      }
      if (!_matched) {
        if (umlType instanceof Enumeration) {
          _matched=true;
          _switchResult = it.generate(umlType, "name");
        }
      }
      if (!_matched) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<codegen \"");
        String _name = umlType.eClass().getName();
        _builder.append(_name);
        _builder.append("\" not implemented>");
        _switchResult = _builder.toString();
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
}
