package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralNull;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ValueSpecTemplate implements Template<ValueSpecification> {
  @Override
  public String generateCode(final CodegenInterface it, final ValueSpecification umlLiteralSpec, final String context) {
    boolean _matched = false;
    if (umlLiteralSpec instanceof LiteralInteger) {
      _matched=true;
      return String.valueOf(((LiteralInteger)umlLiteralSpec).integerValue());
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralUnlimitedNatural) {
        _matched=true;
        return String.valueOf(((LiteralUnlimitedNatural)umlLiteralSpec).integerValue());
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralReal) {
        _matched=true;
        return String.valueOf(((LiteralReal)umlLiteralSpec).realValue());
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralString) {
        _matched=true;
        String _elvis = null;
        String _stringValue = ((LiteralString)umlLiteralSpec).stringValue();
        if (_stringValue != null) {
          _elvis = _stringValue;
        } else {
          _elvis = "";
        }
        final String string = _elvis;
        String _replace = string.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r").replace("\"", "\\\"");
        String _plus = ("\"" + _replace);
        return (_plus + "\"");
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralNull) {
        _matched=true;
        return "NULL";
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralBoolean) {
        _matched=true;
        String _xifexpression = null;
        boolean _booleanValue = ((LiteralBoolean)umlLiteralSpec).booleanValue();
        if (_booleanValue) {
          _xifexpression = "1";
        } else {
          _xifexpression = "0";
        }
        return _xifexpression;
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof InstanceValue) {
        _matched=true;
        final InstanceSpecification instance = ((InstanceValue)umlLiteralSpec).getInstance();
        if ((instance == null)) {
          return "0";
        }
        final Model model = instance.getModel();
        if ((model == null)) {
          return "0";
        }
        String _name = model.getName();
        String _plus = ("&" + _name);
        String _plus_1 = (_plus + "_");
        String _name_1 = instance.getName();
        return (_plus_1 + _name_1);
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof OpaqueExpression) {
        _matched=true;
        String _elvis = null;
        String _head = IterableExtensions.<String>head(((OpaqueExpression)umlLiteralSpec).getBodies());
        String _trim = null;
        if (_head!=null) {
          _trim=_head.trim();
        }
        if (_trim != null) {
          _elvis = _trim;
        } else {
          _elvis = "0";
        }
        final String body = _elvis;
        return body;
      }
    }
    String _name = umlLiteralSpec.getClass().getName();
    return ("// unsupported value type: " + _name);
  }
}
