package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import java.util.LinkedList;
import java.util.Objects;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

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
        _switchResult = this.mapPrimitive(((PrimitiveType)umlType).getName());
      }
      if (!_matched) {
        if (umlType instanceof org.eclipse.uml2.uml.Class) {
          _matched=true;
          final LinkedList<String> pathParts = new LinkedList<String>();
          Namespace parent = ((org.eclipse.uml2.uml.Class)umlType).getNamespace();
          while ((parent != null)) {
            {
              pathParts.addFirst(parent.getName());
              parent = parent.getNamespace();
            }
          }
          pathParts.add(((org.eclipse.uml2.uml.Class)umlType).getName());
          return IterableExtensions.join(pathParts, "_");
        }
      }
      if (!_matched) {
        if (umlType instanceof Enumeration) {
          _matched=true;
          final LinkedList<String> pathParts = new LinkedList<String>();
          Namespace parent = ((Enumeration)umlType).getNamespace();
          while ((parent != null)) {
            {
              pathParts.addFirst(parent.getName());
              parent = parent.getNamespace();
            }
          }
          pathParts.add(((Enumeration)umlType).getName());
          return IterableExtensions.join(pathParts, "_");
        }
      }
      if (!_matched) {
        if (umlType instanceof Artifact) {
          _matched=true;
          final String name = ((Artifact)umlType).getName();
          if (((!Objects.equals(name, null)) && name.endsWith(".h"))) {
            return name.replace(".h", "");
          } else {
            String _elvis = null;
            if (name != null) {
              _elvis = name;
            } else {
              _elvis = "artifact_type";
            }
            return _elvis;
          }
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

  public String mapPrimitive(final String name) {
    String _switchResult = null;
    if (name != null) {
      switch (name) {
        case "Integer":
          _switchResult = "int32_t";
          break;
        case "Real":
          _switchResult = "float";
          break;
        case "Boolean":
          _switchResult = "bool";
          break;
        case "String":
          _switchResult = "char*";
          break;
        default:
          _switchResult = name;
          break;
      }
    } else {
      _switchResult = name;
    }
    return _switchResult;
  }
}
