package codegenerator.test.exercise4;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;
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
  public void testMain() {
    OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
    final Procedure1<OpaqueBehavior> _function = (OpaqueBehavior it) -> {
      it.setName("main");
    };
    final OpaqueBehavior behavior = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("MyClass");
      it.setClassifierBehavior(behavior);
    };
    final org.eclipse.uml2.uml.Class cls = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("WithMain");
      it.setClassifierBehavior(behavior);
    };
    final org.eclipse.uml2.uml.Class classWithBehavior = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_2);
    org.eclipse.uml2.uml.Class _createClass_2 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_3 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("NoMain");
    };
    final org.eclipse.uml2.uml.Class classWithoutBehavior = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_2, _function_3);
    InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_4 = (InstanceSpecification it) -> {
      it.setName("goodInstance");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(classWithBehavior);
    };
    final InstanceSpecification instanceGood = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_4);
    InstanceSpecification _createInstanceSpecification_1 = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_5 = (InstanceSpecification it) -> {
      it.setName("badInstance");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(classWithoutBehavior);
    };
    final InstanceSpecification instanceWrong = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification_1, _function_5);
    InstanceSpecification _createInstanceSpecification_2 = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_6 = (InstanceSpecification it) -> {
      it.setName("inst");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(cls);
    };
    final InstanceSpecification inst = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification_2, _function_6);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_7 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(classWithBehavior);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      _packagedElements_1.add(classWithoutBehavior);
      EList<PackageableElement> _packagedElements_2 = it.getPackagedElements();
      _packagedElements_2.add(instanceGood);
      EList<PackageableElement> _packagedElements_3 = it.getPackagedElements();
      _packagedElements_3.add(instanceWrong);
      EList<PackageableElement> _packagedElements_4 = it.getPackagedElements();
      _packagedElements_4.add(inst);
    };
    final Model model = ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_7);
    final String code = new Uml2C().generateCode(model, "main");
    InputOutput.<String>println(("TEST main with multiple ssinstances:\n" + code));
    Assert.assertTrue(code.contains("#include"));
    Assert.assertTrue(code.contains("main(&Model_inst);"));
    Assert.assertTrue(code.contains("int main()"));
    Assert.assertTrue(code.contains("main(&Model_goodInstance);"));
    Assert.assertTrue(code.contains("MyClass.h"));
  }
}
