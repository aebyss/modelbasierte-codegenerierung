package codegenerator.test.exercise2

import org.junit.Test
import org.eclipse.uml2.uml.UMLFactory
import codegenerator.Uml2C
import org.junit.Assert

class TestStructuredClassifier {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * 
	 */
	@Test def testEmptyClassStruct() {
		val class = createClass => [
			name = "TestClass"
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct TestClass_struct {
			} TestClass;
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testEmptyClassStruct2() {
		val class = createClass => [
			name = "ClassTest"
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct ClassTest_struct {
			} ClassTest;
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithPropertyStruct() {
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createPrimitiveType => [name = "int"]
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct TestClass_struct {
				int testProperty;
			} TestClass;
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithProperty2Struct() {
		val class = createClass => [
			name = "PropertyClass"
			ownedAttributes += createProperty => [
				name = "floatAttribute"
				type = createPrimitiveType => [name = "float"]
				upper = 2
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct PropertyClass_struct {
				float floatAttribute[2];
			} PropertyClass;
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithMorePropertiesStruct() {
		val class = createClass => [
			name = "Vector3"
			ownedAttributes += createProperty => [
				name = "x"
				type = createPrimitiveType => [name = "float"]
			]
			ownedAttributes += createProperty => [
				name = "y"
				type = createPrimitiveType => [name = "float"]
			]
			ownedAttributes += createProperty => [
				name = "z"
				type = createPrimitiveType => [name = "float"]
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct Vector3_struct {
				float x;
				float y;
				float z;
			} Vector3;
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithMoreProperties2Struct() {
		val class = createClass => [
			name = "Texture"
			ownedAttributes += createProperty => [
				name = "width"
				type = createPrimitiveType => [name = "int"]
			]
			ownedAttributes += createProperty => [
				name = "height"
				type = createPrimitiveType => [name = "int"]
			]
			ownedAttributes += createProperty => [
				name = "data"
				type = createPrimitiveType => [name = "color_t"]
				upper = -1
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct Texture_struct {
				int width;
				int height;
				color_t* data;
			} Texture;
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithOperationStruct() {
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createPrimitiveType => [name = "int"]
			]
			ownedOperations += createOperation => [
				name = "testOperation"
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "/* hier koennte Ihre Werbung stehen */"
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct TestClass_struct {
				int testProperty;
			} TestClass;
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithOperationStruct2() {
		val otherClass = createClass => [
			name = "OtherClass"
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createPrimitiveType => [name = "int"]
			]
			ownedAttributes += createProperty => [
				name = "classProperty"
				type = otherClass
			]
			ownedOperations += createOperation => [
				name = "testOperation"
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "/* hier koennte Ihre Werbung stehen */"
				]
			]
		]
		createPackage => [
			name = "SomePacket"
			packagedElements += class
			packagedElements += otherClass
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")
		print(code)

		Assert.assertEquals('''
			typedef struct SomePacket_TestClass_struct {
				int testProperty;
				SomePacket_OtherClass* classProperty;
			} SomePacket_TestClass;
		'''.toString, code)
	}

}
