package codegenerator.test.exercise4;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestMain {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  @Test
  public void testMainGeneratingWithValidBehavior() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("start");
    };
    final Operation op = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
    final Procedure1<OpaqueBehavior> _function_1 = (OpaqueBehavior it) -> {
      it.setName("start_impl");
      it.setSpecification(op);
    };
    final OpaqueBehavior behavior = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_1);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("Game");
      it.setClassifierBehavior(behavior);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(op);
    };
    final org.eclipse.uml2.uml.Class cls = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_2);
    InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it) -> {
      it.setName("gameInstance");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(cls);
    };
    final InstanceSpecification inst = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_4 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(cls);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      _packagedElements_1.add(inst);
    };
    final Model model = ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_4);
    final Uml2C generator = new Uml2C();
    final String code = generator.generateCode(model, "main");
    InputOutput.<String>println(("--- Generierter main.c ---\n" + code));
    final String includeLine = "#include \"Game.h\"";
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(includeLine);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("int main(void) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Model_Game_start(&Model_gameInstance);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return 0;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final String expected = _builder.toString().trim();
    final String actual = code;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMainIgnoresInstanceWithoutBehavior() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("NoBehavior");
    };
    final org.eclipse.uml2.uml.Class cls = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_1 = (InstanceSpecification it) -> {
      it.setName("ignored");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(cls);
    };
    final InstanceSpecification inst = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(cls);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      _packagedElements_1.add(inst);
    };
    final Model model = ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final Uml2C generator = new Uml2C();
    final String code = generator.generateCode(model, "main");
    InputOutput.<String>println(("--- Generierter Code (ohne Behavior) ---\n" + code));
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("int main(void) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return 0;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final String expected = _builder.toString();
    final String actual = code;
    Assert.assertTrue(actual.contains(expected));
  }

  @Test
  public void testFallbackOnBehaviorNameIfNoOperation() {
    OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
    final Procedure1<OpaqueBehavior> _function = (OpaqueBehavior it) -> {
      it.setName("do_something");
    };
    final OpaqueBehavior behavior = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("Helper");
      it.setClassifierBehavior(behavior);
    };
    final org.eclipse.uml2.uml.Class cls = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_2 = (InstanceSpecification it) -> {
      it.setName("helperInstance");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(cls);
    };
    final InstanceSpecification inst = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_2);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_3 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(cls);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      _packagedElements_1.add(inst);
    };
    final Model model = ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_3);
    final Uml2C generator = new Uml2C();
    final String code = generator.generateCode(model, "main");
    InputOutput.<String>println(("--- Fallback-Fall ---\n" + code));
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"Helper.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("int main(void) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Helper_do_something(&Model_helperInstance);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return 0;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final String expected = _builder.toString();
    final String actual = code;
    Assert.assertEquals(expected, actual);
  }
}
