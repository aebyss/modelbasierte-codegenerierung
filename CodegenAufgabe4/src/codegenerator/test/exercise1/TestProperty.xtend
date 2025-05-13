package codegenerator.test.exercise1

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test
import org.eclipse.uml2.uml.AggregationKind

class TestProperty {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * 
	 */
	@Test def testSimpleTypedProperty() {
		val property = createProperty => [
			name = "testProperty"
			type = createPrimitiveType => [name = "uint32"]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("uint32 testProperty;", code)
	}

	/**
	 * 
	 */
	@Test def testSimpleTypedProperty2() {
		val property = createProperty => [
			name = "testFloat"
			type = createPrimitiveType => [name = "float"]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("float testFloat;", code)
	}

	/**
	 * 
	 */
	@Test def testComplexTypedProperty() {
		val property = createProperty => [
			name = "propertyTest"
			type = createClass => [
				name = "TestClass"
			]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("TestClass* propertyTest;", code)
	}

	/**
	 * 
	 */
	@Test def testComplexTypedProperty2() {
		val property = createProperty => [
			name = "complexTestProperty"
			type = createClass => [
				name = "AnotherClass"
			]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("AnotherClass* complexTestProperty;", code)
	}

	/**
	 * 
	 */
	@Test def testNamespacedComplexTypedProperty() {
		
		val property = createProperty => [
			name = "propertyTest"
			type = createClass => [
				name = "TestClass"
			]
		]
		createPackage => [
			name = "testPackage"
			packagedElements += property.type
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("testPackage_TestClass* propertyTest;", code)
	}

	/**
	 * 
	 */
	@Test def testNamespacedComplexTypedProperty2() {
		
		val property = createProperty => [
			name = "someAttribute"
			type = createClass => [
				name = "TestType"
			]
		]
		createPackage => [
			name = "aPackage"
			packagedElements += property.type
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("aPackage_TestType* someAttribute;", code)
	}

	/**
	 * 
	 */
	@Test def testUntypedProperty() {
		val property = createProperty => [
			name = "untypedTestProperty"
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("void* untypedTestProperty;", code)
	}

	/**
	 * 
	 */
	@Test def testOwnedComplexTypedProperty() {
		val property = createProperty => [
			name = "testAttribute"
			type = createClass => [
				name = "testClass"
			]
			aggregation = AggregationKind.COMPOSITE_LITERAL
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("testClass testAttribute;", code)
	}

	/**
	 * 
	 */
	@Test def testOwnedComplexTypedProperty2() {
		val property = createProperty => [
			name = "ownedAttributeTest"
			type = createClass => [
				name = "ClassTest"
			]
			aggregation = AggregationKind.COMPOSITE_LITERAL
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("ClassTest ownedAttributeTest;", code)
	}
}
