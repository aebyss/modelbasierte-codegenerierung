package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.collect.Iterables;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ClassTemplate implements Template<org.eclipse.uml2.uml.Class> {
  @Override
  public String generateCode(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass, final String context) {
    if (context != null) {
      switch (context) {
        case "declaration":
          final String className = umlClass.getName();
          String _elvis = null;
          Model _model = umlClass.getModel();
          String _name = null;
          if (_model!=null) {
            _name=_model.getName();
          }
          if (_name != null) {
            _elvis = _name;
          } else {
            _elvis = "Model";
          }
          final String modelName = _elvis;
          final String structName = ((modelName + "_") + className);
          final String guard = it.generate(umlClass, "name").toUpperCase();
          final Model model = umlClass.getModel();
          Iterable<InstanceSpecification> _xifexpression = null;
          if ((model != null)) {
            final Function1<InstanceSpecification, Boolean> _function = (InstanceSpecification it_1) -> {
              return Boolean.valueOf(it_1.getClassifiers().contains(umlClass));
            };
            _xifexpression = IterableExtensions.<InstanceSpecification>filter(Iterables.<InstanceSpecification>filter(model.allOwnedElements(), InstanceSpecification.class), _function);
          } else {
            _xifexpression = CollectionLiterals.<InstanceSpecification>emptyList();
          }
          final Iterable<InstanceSpecification> instances = _xifexpression;
          final String includes = this.generateIncludes(it, umlClass);
          final String typedef = it.generate(umlClass, "typedefinition");
          final Function1<InstanceSpecification, String> _function_1 = (InstanceSpecification inst) -> {
            String _name_1 = inst.getName();
            String _plus = ((((("extern " + structName) + " ") + modelName) + "_") + _name_1);
            return (_plus + ";");
          };
          final String externs = IterableExtensions.join(IterableExtensions.<InstanceSpecification, String>map(instances, _function_1), "\n");
          final Function1<Operation, String> _function_2 = (Operation op) -> {
            return it.generate(op, "declaration").trim();
          };
          final String operationDecls = IterableExtensions.join(ListExtensions.<Operation, String>map(umlClass.getOwnedOperations(), _function_2), "\n\n");
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("#ifndef ");
          _builder.append(guard);
          _builder.append("_H");
          _builder.newLineIfNotEmpty();
          _builder.append("#define ");
          _builder.append(guard);
          _builder.append("_H");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append(includes);
          _builder.newLineIfNotEmpty();
          _builder.append(typedef);
          _builder.newLineIfNotEmpty();
          {
            boolean _isEmpty = operationDecls.isEmpty();
            boolean _not = (!_isEmpty);
            if (_not) {
              _builder.newLine();
              _builder.append(operationDecls);
              _builder.newLineIfNotEmpty();
            }
          }
          {
            boolean _isEmpty_1 = externs.isEmpty();
            boolean _not_1 = (!_isEmpty_1);
            if (_not_1) {
              _builder.newLine();
              _builder.append(externs);
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.newLine();
          _builder.append("#endif");
          _builder.newLine();
          return _builder.toString();
        case "implementation":
          final Model model_1 = umlClass.getModel();
          String _elvis_1 = null;
          String _elvis_2 = null;
          org.eclipse.uml2.uml.Package _nearestPackage = umlClass.getNearestPackage();
          String _name_1 = null;
          if (_nearestPackage!=null) {
            _name_1=_nearestPackage.getName();
          }
          if (_name_1 != null) {
            _elvis_2 = _name_1;
          } else {
            Model _model_1 = umlClass.getModel();
            String _name_2 = null;
            if (_model_1!=null) {
              _name_2=_model_1.getName();
            }
            _elvis_2 = _name_2;
          }
          if (_elvis_2 != null) {
            _elvis_1 = _elvis_2;
          } else {
            _elvis_1 = "Model";
          }
          final String modelName_1 = _elvis_1;
          Iterable<InstanceSpecification> _xifexpression_1 = null;
          if ((model_1 != null)) {
            final Function1<InstanceSpecification, Boolean> _function_3 = (InstanceSpecification it_1) -> {
              return Boolean.valueOf(it_1.getClassifiers().contains(umlClass));
            };
            _xifexpression_1 = IterableExtensions.<InstanceSpecification>filter(Iterables.<InstanceSpecification>filter(model_1.allOwnedElements(), InstanceSpecification.class), _function_3);
          } else {
            _xifexpression_1 = CollectionLiterals.<InstanceSpecification>emptyList();
          }
          final Iterable<InstanceSpecification> instances_1 = _xifexpression_1;
          String _name_3 = umlClass.getName();
          final String structName_1 = ((modelName_1 + "_") + _name_3);
          String _name_4 = umlClass.getName();
          final String headerPath = (_name_4 + ".h");
          final Function1<Operation, String> _function_4 = (Operation op) -> {
            return it.generate(op, "implementation").trim();
          };
          final String operationImpls = IterableExtensions.join(ListExtensions.<Operation, String>map(umlClass.getOwnedOperations(), _function_4), "\n\n").trim();
          final Function1<InstanceSpecification, String> _function_5 = (InstanceSpecification inst) -> {
            return this.generateInstanceCode(it, modelName_1, umlClass, inst);
          };
          final String instanceCode = IterableExtensions.join(IterableExtensions.<InstanceSpecification, String>map(instances_1, _function_5), "\n");
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("#include \"");
          _builder_1.append(headerPath);
          _builder_1.append("\"");
          _builder_1.newLineIfNotEmpty();
          {
            boolean _isEmpty_2 = operationImpls.isEmpty();
            boolean _not_2 = (!_isEmpty_2);
            if (_not_2) {
              _builder_1.newLine();
              _builder_1.append(operationImpls);
              _builder_1.newLineIfNotEmpty();
            }
          }
          {
            boolean _isEmpty_3 = instanceCode.isEmpty();
            boolean _not_3 = (!_isEmpty_3);
            if (_not_3) {
              _builder_1.newLine();
              _builder_1.append(instanceCode);
              _builder_1.newLineIfNotEmpty();
            }
          }
          return _builder_1.toString();
      }
    }
    return null;
  }

  public String generateInstanceCode(final CodegenInterface it, final String modelName, final org.eclipse.uml2.uml.Class cls, final InstanceSpecification inst) {
    String _name = inst.getName();
    final String instanceName = ((modelName + "_") + _name);
    String _name_1 = cls.getName();
    final String structName = ((modelName + "_") + _name_1);
    boolean _isEmpty = inst.getSlots().isEmpty();
    if (_isEmpty) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(structName);
      _builder.append(" ");
      _builder.append(instanceName);
      _builder.append(" = {");
      _builder.newLineIfNotEmpty();
      _builder.append("};");
      _builder.newLine();
      return _builder.toString();
    }
    final Function1<Slot, String> _function = (Slot slot) -> {
      String _xblockexpression = null;
      {
        String _elvis = null;
        StructuralFeature _definingFeature = slot.getDefiningFeature();
        String _name_2 = null;
        if (_definingFeature!=null) {
          _name_2=_definingFeature.getName();
        }
        if (_name_2 != null) {
          _elvis = _name_2;
        } else {
          _elvis = "UNKNOWN";
        }
        final String attrName = _elvis;
        final EList<ValueSpecification> values = slot.getValues();
        String _xifexpression = null;
        int _size = values.size();
        boolean _greaterThan = (_size > 1);
        if (_greaterThan) {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("{");
          _builder_1.newLine();
          _builder_1.append("\t");
          final Function1<ValueSpecification, String> _function_1 = (ValueSpecification v) -> {
            return it.generate(v, "value");
          };
          String _join = IterableExtensions.join(ListExtensions.<ValueSpecification, String>map(values, _function_1), ",\n");
          _builder_1.append(_join, "\t");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("}");
          _xifexpression = _builder_1.toString();
        } else {
          String _xifexpression_1 = null;
          if ((values.isEmpty() || Objects.equals(IterableExtensions.<ValueSpecification>head(values), null))) {
            _xifexpression_1 = "0";
          } else {
            _xifexpression_1 = it.generate(IterableExtensions.<ValueSpecification>head(values), "value");
          }
          _xifexpression = _xifexpression_1;
        }
        final String valueText = _xifexpression;
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append(".");
        _builder_2.append(attrName);
        _builder_2.append(" = ");
        _builder_2.append(valueText);
        _xblockexpression = _builder_2.toString();
      }
      return _xblockexpression;
    };
    final String assignments = IterableExtensions.join(ListExtensions.<Slot, String>map(inst.getSlots(), _function), ",\n");
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(structName);
    _builder_1.append(" ");
    _builder_1.append(instanceName);
    _builder_1.append(" = {");
    _builder_1.newLineIfNotEmpty();
    _builder_1.append("\t");
    _builder_1.append(assignments, "\t");
    _builder_1.newLineIfNotEmpty();
    _builder_1.append("};");
    _builder_1.newLine();
    return _builder_1.toString();
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
