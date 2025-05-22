package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.collect.Iterables;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class MainTemplate implements Template<Model> {
  @Override
  public String generateCode(final CodegenInterface it, final Model umlModel, final String context) {
    String _xblockexpression = null;
    {
      final Function1<InstanceSpecification, Boolean> _function = (InstanceSpecification i) -> {
        return Boolean.valueOf((((i.getClassifiers().size() == 1) && (IterableExtensions.<Classifier>head(i.getClassifiers()) instanceof org.eclipse.uml2.uml.Class)) && (null != ((org.eclipse.uml2.uml.Class) IterableExtensions.<Classifier>head(i.getClassifiers())).getClassifierBehavior())));
      };
      final Iterable<InstanceSpecification> instances = IterableExtensions.<InstanceSpecification>filter(Iterables.<InstanceSpecification>filter(umlModel.allOwnedElements(), InstanceSpecification.class), _function);
      final Function1<InstanceSpecification, EList<Classifier>> _function_1 = (InstanceSpecification it_1) -> {
        return it_1.getClassifiers();
      };
      final Function1<org.eclipse.uml2.uml.Class, String> _function_2 = (org.eclipse.uml2.uml.Class cls) -> {
        Path _path = it.getPath(cls, "declaration");
        String _string = null;
        if (_path!=null) {
          _string=_path.toString();
        }
        String _replace = null;
        if (_string!=null) {
          _replace=_string.replace("\\", "/");
        }
        return _replace;
      };
      final List<String> includes = IterableExtensions.<String>toList(IterableExtensions.<String>filterNull(IterableExtensions.<org.eclipse.uml2.uml.Class, String>map(IterableExtensions.<org.eclipse.uml2.uml.Class>toSet(Iterables.<org.eclipse.uml2.uml.Class>filter(IterableExtensions.<InstanceSpecification, Classifier>flatMap(instances, _function_1), org.eclipse.uml2.uml.Class.class)), _function_2)));
      StringConcatenation _builder = new StringConcatenation();
      {
        List<String> _sort = IterableExtensions.<String>sort(includes);
        for(final String path : _sort) {
          _builder.append("#include \"");
          _builder.append(path);
          _builder.append("\"");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("int main(void) {");
      _builder.newLine();
      {
        for(final InstanceSpecification inst : instances) {
          _builder.append("\t");
          Classifier _head = IterableExtensions.<Classifier>head(inst.getClassifiers());
          final org.eclipse.uml2.uml.Class cls = ((org.eclipse.uml2.uml.Class) _head);
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          final Behavior behavior = cls.getClassifierBehavior();
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          BehavioralFeature _specification = null;
          if (behavior!=null) {
            _specification=behavior.getSpecification();
          }
          final BehavioralFeature operation = _specification;
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _xifexpression = null;
          if ((operation != null)) {
            _xifexpression = it.generate(operation, "name");
          } else {
            _xifexpression = it.generate(behavior, "name");
          }
          final String funcName = _xifexpression;
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(funcName, "\t");
          _builder.append("(&");
          String _generate = it.generate(inst, "name");
          _builder.append(_generate, "\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("return 0;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }

  @Override
  public Path getPath(final Model object, final String context) {
    return Paths.get("main.c");
  }
}
