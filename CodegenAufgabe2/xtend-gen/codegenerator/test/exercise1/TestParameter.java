package codegenerator.test.exercise1;

import codegenerator.Uml2C;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestParameter {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Ein einfacher Parameter mit einem Namen und Datentyp.
   */
  @Test
  public void test00_SimpleTypedParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("testParameter");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("uint32");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    InputOutput.<String>print(code);
    Assert.assertEquals("uint32 testParameter", code);
  }

  /**
   * Ein einfacher Parameter mit einem anderen Namen und Datentyp als vorher.
   */
  @Test
  public void test01_SimpleTypedParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("simpleFloatParameter");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("float simpleFloatParameter", code);
  }

  /**
   * Wenn ein Parameter keinen Datentyp hat, soll "void*" generiert werden.
   */
  @Test
  public void test02_UntypedParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("untypedParameter");
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("void* untypedParameter", code);
  }

  /**
   * Gleiches wie vorher, mit anderem Namen.
   */
  @Test
  public void test03_UntypedParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("typelessParameter");
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("void* typelessParameter", code);
  }

  /**
   * Return-Parameter können durch das "direction" Attribut utnerschieden werden.
   * Der Standardwert für direction ist "ParameterDirectionKind.IN_LITERAL"
   * Für einen Return-Parameter soll kein Name generiert werden, nur der Datentyp.
   */
  @Test
  public void test04_PrimitiveReturnParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("primitiveReturnParameter");
      it.setDirection(ParameterDirectionKind.RETURN_LITERAL);
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("uint32");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "return");
    Assert.assertEquals("uint32", code);
  }

  /**
   * Wie vorher, mit geändertem Datentyp.
   */
  @Test
  public void test05_PrimitiveReturnParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("primitiveReturnParameter");
      it.setDirection(ParameterDirectionKind.RETURN_LITERAL);
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("string");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "return");
    Assert.assertEquals("string", code);
  }

  /**
   * Diesmal ist der Datentyp eine Klasse statt ein primitiver Typ.
   * Es soll ein Pointer auf den Typ generiert werden.
   */
  @Test
  public void test06_ComplexTypedParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("complexParameter");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ComplexType");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("ComplexType* complexParameter", code);
  }

  /**
   * Wie vorher, mit geändertem Namen und Datentyp.
   */
  @Test
  public void test07_ComplexTypedParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("evenMoreComplexParameter");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ComplexestType");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("ComplexestType* evenMoreComplexParameter", code);
  }

  /**
   * Für Klassen sollen auch bei Return-Parametern ein Pointer generiert werden.
   */
  @Test
  public void test08_ComplexReturnParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("complexReturnParameter");
      it.setDirection(ParameterDirectionKind.RETURN_LITERAL);
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ComplexType");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "return");
    Assert.assertEquals("ComplexType*", code);
  }

  /**
   * Wie vorher, mit geändertem Datentyp.
   */
  @Test
  public void test09_ComplexReturnParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("ReturnOfTheComplexParameter");
      it.setDirection(ParameterDirectionKind.RETURN_LITERAL);
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ComplexClass");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "return");
    Assert.assertEquals("ComplexClass*", code);
  }

  /**
   * Bei In-Out Paramertern soll auch für primitive Datentypen ein Pointer generiert werden.
   */
  @Test
  public void test10_PrimitiveInOutParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("primitiveInOutParameter");
      it.setDirection(ParameterDirectionKind.INOUT_LITERAL);
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("uint32");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("uint32* primitiveInOutParameter", code);
  }

  /**
   * Wie vorher, mit geändertem Namen und Datentyp.
   */
  @Test
  public void test11_PrimitiveInOutParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("primitiveOutInParameter");
      it.setDirection(ParameterDirectionKind.INOUT_LITERAL);
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("f32");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("f32* primitiveOutInParameter", code);
  }

  /**
   * Für Klassen soll bei In-Out Paramatern ein doppelter Pointer generiert werden.
   */
  @Test
  public void test12_ComplexInOutParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("complexInOutParameter");
      it.setDirection(ParameterDirectionKind.INOUT_LITERAL);
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ComplexType");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("ComplexType** complexInOutParameter", code);
  }

  /**
   * Wie vorher, mit geändertem Namen und Datentyp.
   */
  @Test
  public void test13_ComplexInOutParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("complexOutInParameter");
      it.setDirection(ParameterDirectionKind.INOUT_LITERAL);
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ClassType");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("ClassType** complexOutInParameter", code);
  }

  /**
   * Bei Out Parameter soll auch für primitive Datentypen ein Pointer generiert werden.
   */
  @Test
  public void test14_PrimitiveOutParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("primitiveOutParameter");
      it.setDirection(ParameterDirectionKind.OUT_LITERAL);
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int16_t");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("int16_t* primitiveOutParameter", code);
  }

  /**
   * Wie vorher, mit geändertem Namen und Datentyp.
   */
  @Test
  public void test15_PrimitiveOutParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("primitiveOutArgument");
      it.setDirection(ParameterDirectionKind.OUT_LITERAL);
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("f64");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("f64* primitiveOutArgument", code);
  }

  /**
   * Bei Out Parametern soll für Klassen ein doppelter Pointer generiert werden.
   */
  @Test
  public void test16_ComplexOutParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("complexOutParameter");
      it.setDirection(ParameterDirectionKind.OUT_LITERAL);
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ComplexType");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("ComplexType** complexOutParameter", code);
  }

  /**
   * Wie vorher, mit geändertem Namen und Datentyp.
   */
  @Test
  public void test17_ComplexOutParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("complexOutArgument");
      it.setDirection(ParameterDirectionKind.OUT_LITERAL);
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ComplexClassType");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("ComplexClassType** complexOutArgument", code);
  }
}
