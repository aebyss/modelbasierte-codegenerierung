package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.collect.Iterables;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ClassTemplate implements Template<org.eclipse.uml2.uml.Class> {
  @Override
  public String generateCode(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass, final String context) {
    String _xifexpression = null;
    String _name = umlClass.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      _xifexpression = it.generate(umlClass, "name");
    } else {
      _xifexpression = "UnnamedClass";
    }
    final String name = _xifexpression;
    if (context != null) {
      switch (context) {
        case "declaration":
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("#ifndef ");
          String _upperCase = name.toUpperCase();
          _builder.append(_upperCase);
          _builder.append("_H");
          _builder.newLineIfNotEmpty();
          _builder.append("#define ");
          String _upperCase_1 = name.toUpperCase();
          _builder.append(_upperCase_1);
          _builder.append("_H");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          String _generateIncludes = this.generateIncludes(it, umlClass);
          _builder.append(_generateIncludes);
          _builder.newLineIfNotEmpty();
          String _generate = it.generate(umlClass, "typedefinition");
          _builder.append(_generate);
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          {
            EList<Operation> _ownedOperations = umlClass.getOwnedOperations();
            for(final Operation operation : _ownedOperations) {
              String _generate_1 = it.generate(operation, "declaration");
              _builder.append(_generate_1);
              _builder.newLineIfNotEmpty();
              _builder.newLine();
            }
          }
          _builder.append("#endif");
          _builder.newLine();
          return _builder.toString();
        case "implementation":
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("#include \"");
          String _name_1 = umlClass.getName();
          _builder_1.append(_name_1);
          _builder_1.append(".h\"");
          _builder_1.newLineIfNotEmpty();
          {
            EList<Operation> _ownedOperations_1 = umlClass.getOwnedOperations();
            for(final Operation operation_1 : _ownedOperations_1) {
              _builder_1.newLine();
              String _generate_2 = it.generate(operation_1, "implementation");
              _builder_1.append(_generate_2);
              _builder_1.newLineIfNotEmpty();
            }
          }
          return _builder_1.toString();
      }
    }
    return null;
  }

  public String generateIncludes(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass) {
    String _xblockexpression = null;
    {
      final HashSet<Type> types = new HashSet<Type>();
      EList<Property> _ownedAttributes = umlClass.getOwnedAttributes();
      for (final Property property : _ownedAttributes) {
        if (((property.getType() != null) && ((property.getType() instanceof org.eclipse.uml2.uml.Class) || (property.getType() instanceof Enumeration)))) {
          types.add(property.getType());
        }
      }
      EList<Operation> _ownedOperations = umlClass.getOwnedOperations();
      for (final Operation operation : _ownedOperations) {
        EList<Parameter> _ownedParameters = operation.getOwnedParameters();
        for (final Parameter parameter : _ownedParameters) {
          if (((parameter.getType() != null) && ((parameter.getType() instanceof org.eclipse.uml2.uml.Class) || (parameter.getType() instanceof Enumeration)))) {
            types.add(parameter.getType());
          }
        }
      }
      EList<Relationship> _relationships = umlClass.getRelationships();
      for (final Relationship rel : _relationships) {
        if ((rel instanceof Dependency)) {
          final Dependency dep = ((Dependency) rel);
          Iterables.<Type>addAll(types, Iterables.<Type>filter(dep.getSuppliers(), Type.class));
        }
      }
      final HashSet<String> includes = new HashSet<String>();
      for (final Type type : types) {
        if ((type != null)) {
          String _generatePath = this.generatePath(it, umlClass, type);
          String _plus = ("#include \"" + _generatePath);
          String _plus_1 = (_plus + "\"");
          includes.add(_plus_1);
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      {
        List<String> _sort = IterableExtensions.<String>sort(IterableExtensions.<String>toList(includes));
        boolean _hasElements = false;
        for(final String include : _sort) {
          if (!_hasElements) {
            _hasElements = true;
          }
          _builder.append(include);
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
    if (((fromPath == null) || (toPath == null))) {
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
      return relPath.toString().replace("\\", "/");
    }
    Path _elvis_1 = null;
    Path _parent_1 = fromPath.getParent();
    Path _relativize_1 = null;
    if (_parent_1!=null) {
      _relativize_1=_parent_1.relativize(toPath);
    }
    if (_relativize_1 != null) {
      _elvis_1 = _relativize_1;
    } else {
      _elvis_1 = toPath;
    }
    final Path relPath_1 = _elvis_1;
    return IterableExtensions.join(relPath_1, "/");
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
