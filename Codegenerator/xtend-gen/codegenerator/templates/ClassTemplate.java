package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ClassTemplate implements Template<org.eclipse.uml2.uml.Class> {
  @Override
  public String generateCode(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass, final String context) {
    return null;
  }
  
  public String generateIncludes(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass) {
    String _xblockexpression = null;
    {
      final HashSet<Type> types = new HashSet<Type>();
      EList<Property> _ownedAttributes = umlClass.getOwnedAttributes();
      for (final Property property : _ownedAttributes) {
        Type _type = property.getType();
        if ((_type instanceof org.eclipse.uml2.uml.Class)) {
          types.add(property.getType());
        }
      }
      EList<Operation> _ownedOperations = umlClass.getOwnedOperations();
      for (final Operation operation : _ownedOperations) {
        EList<Parameter> _ownedParameters = operation.getOwnedParameters();
        for (final Parameter parameter : _ownedParameters) {
          Type _type_1 = parameter.getType();
          if ((_type_1 instanceof org.eclipse.uml2.uml.Class)) {
            types.add(parameter.getType());
          }
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      {
        boolean _hasElements = false;
        for(final Type type : types) {
          if (!_hasElements) {
            _hasElements = true;
          }
          _builder.append("#include \"");
          String _generatePath = this.generatePath(it, umlClass, type);
          _builder.append(_generatePath);
          _builder.append("\"");
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements) {
          _builder.append("\n");
        }
      }
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public String generatePath(final CodegenInterface it, final NamedElement from, final NamedElement to) {
    final Path fromPath = it.getPath(from, "declaration");
    final Path toPath = it.getPath(to, "declaration");
    Path _elvis = null;
    Path _parent = fromPath.getParent();
    Path _relativize = null;
    if (_parent!=null) {
      _relativize=_parent.relativize(toPath);
    }
    if (_relativize != null) {
      _elvis = _relativize;
    } else {
      _elvis = toPath;
    }
    final Path relPath = _elvis;
    return IterableExtensions.join(relPath, "/");
  }
  
  @Override
  public Path getPath(final org.eclipse.uml2.uml.Class umlClass, final String context) {
    LinkedList<String> path = new LinkedList<String>();
    if (context != null) {
      switch (context) {
        case "declaration":
          String _name = umlClass.getName();
          String _plus = (_name + ".h");
          path.addFirst(_plus);
          break;
        case "implementation":
          String _name_1 = umlClass.getName();
          String _plus_1 = (_name_1 + ".c");
          path.addFirst(_plus_1);
          break;
        default:
          return null;
      }
    } else {
      return null;
    }
    Namespace parent = umlClass.getNamespace();
    while ((null != parent)) {
      {
        path.addFirst(parent.getName());
        parent = parent.getNamespace();
      }
    }
    return Paths.get(IterableExtensions.<String>head(path), ((String[])Conversions.unwrapArray(IterableExtensions.<String>tail(path), String.class)));
  }
}
