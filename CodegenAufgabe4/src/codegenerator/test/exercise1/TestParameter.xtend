package codegenerator.test.exercise1

import codegenerator.Uml2C
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestParameter {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * 
	 */
	@Test def testSimpleTypedParameter() {
		val parameter = createParameter => [
			name = "testParameter"
			type = createPrimitiveType => [name = "uint32"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("uint32 testParameter", code)
	}

	/**
	 * 
	 */
	@Test def testSimpleTypedParameter2() {
		val parameter = createParameter => [
			name = "simpleFloatParameter"
			type = createPrimitiveType => [name = "float"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("float simpleFloatParameter", code)
	}

	/**
	 * 
	 */
	@Test def testUntypedParameter() {
		val parameter = createParameter => [
			name = "untypedParameter"
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("void* untypedParameter", code)
	}

	/**
	 * 
	 */
	@Test def testUntypedParameter2() {
		val parameter = createParameter => [
			name = "typelessParameter"
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("void* typelessParameter", code)
	}

	/**
	 * 
	 */
	@Test def testPrimitiveReturnParameter() {
		val parameter = createParameter => [
			name = "primitiveReturnParameter"
			direction = ParameterDirectionKind.RETURN_LITERAL
			type = createPrimitiveType => [name = "uint32"]
		]

		val code = (new Uml2C).generateCode(parameter, "return")

		Assert.assertEquals("uint32", code)
	}

	/**
	 * 
	 */
	@Test def testPrimitiveReturnParameter2() {
		val parameter = createParameter => [
			name = "primitiveReturnParameter"
			direction = ParameterDirectionKind.RETURN_LITERAL
			type = createPrimitiveType => [name = "string"]
		]

		val code = (new Uml2C).generateCode(parameter, "return")

		Assert.assertEquals("string", code)
	}

	/**
	 * 
	 */
	@Test def testComplexTypedParameter() {
		val parameter = createParameter => [
			name = "complexParameter"
			type = createClass => [name = "ComplexType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexType* complexParameter", code)
	}

	/**
	 * 
	 */
	@Test def testComplexTypedParameter2() {
		val parameter = createParameter => [
			name = "evenMoreComplexParameter"
			type = createClass => [name = "ComplexestType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexestType* evenMoreComplexParameter", code)
	}

	/**
	 * 
	 */
	@Test def testComplexReturnParameter() {
		val parameter = createParameter => [
			name = "complexReturnParameter"
			direction = ParameterDirectionKind.RETURN_LITERAL
			type = createClass => [name = "ComplexType"]
		]

		val code = (new Uml2C).generateCode(parameter, "return")

		Assert.assertEquals("ComplexType*", code)
	}

	/**
	 * 
	 */
	@Test def testComplexReturnParameter2() {
		val parameter = createParameter => [
			name = "ReturnOfTheComplexParameter"
			direction = ParameterDirectionKind.RETURN_LITERAL
			type = createClass => [name = "ComplexClass"]
		]

		val code = (new Uml2C).generateCode(parameter, "return")

		Assert.assertEquals("ComplexClass*", code)
	}

	/**
	 * 
	 */
	@Test def testPrimitiveInOutParameter() {
		val parameter = createParameter => [
			name = "primitiveInOutParameter"
			direction = ParameterDirectionKind.INOUT_LITERAL
			type = createPrimitiveType => [name = "uint32"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("uint32* primitiveInOutParameter", code)
	}

	/**
	 * 
	 */
	@Test def testPrimitiveInOutParameter2() {
		val parameter = createParameter => [
			name = "primitiveOutInParameter"
			direction = ParameterDirectionKind.INOUT_LITERAL
			type = createPrimitiveType => [name = "f32"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("f32* primitiveOutInParameter", code)
	}

	/**
	 * 
	 */
	@Test def testComplexInOutParameter() {
		val parameter = createParameter => [
			name = "complexInOutParameter"
			direction = ParameterDirectionKind.INOUT_LITERAL
			type = createClass => [name = "ComplexType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexType** complexInOutParameter", code)
	}

	/**
	 * 
	 */
	@Test def testComplexInOutParameter2() {
		val parameter = createParameter => [
			name = "complexOutInParameter"
			direction = ParameterDirectionKind.INOUT_LITERAL
			type = createClass => [name = "ClassType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ClassType** complexOutInParameter", code)
	}

	/**
	 * 
	 */
	@Test def testPrimitiveOutParameter() {
		val parameter = createParameter => [
			name = "primitiveOutParameter"
			direction = ParameterDirectionKind.OUT_LITERAL
			type = createPrimitiveType => [name = "int16_t"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("int16_t* primitiveOutParameter", code)
	}

	/**
	 * 
	 */
	@Test def testPrimitiveOutParameter2() {
		val parameter = createParameter => [
			name = "primitiveOutArgument"
			direction = ParameterDirectionKind.OUT_LITERAL
			type = createPrimitiveType => [name = "f64"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("f64* primitiveOutArgument", code)
	}

	/**
	 * 
	 */
	@Test def testComplexOutParameter() {
		val parameter = createParameter => [
			name = "complexOutParameter"
			direction = ParameterDirectionKind.OUT_LITERAL
			type = createClass => [name = "ComplexType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexType** complexOutParameter", code)
	}

	/**
	 * 
	 */
	@Test def testComplexOutParameter2() {
		val parameter = createParameter => [
			name = "complexOutArgument"
			direction = ParameterDirectionKind.OUT_LITERAL
			type = createClass => [name = "ComplexClassType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexClassType** complexOutArgument", code)
	}
}
