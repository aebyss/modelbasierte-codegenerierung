package codegenerator.test.exercise2

import codegenerator.Uml2C
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestOperation {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * 
	 */
	@Test def testSimpleOperation() {
		val operation = createOperation => [
			name = "simpleOperation"
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_simpleOperation(TestClass* const me);", code)
	}

	/**
	 * 
	 */
	@Test def testSimpleOperation2() {
		val operation = createOperation => [
			name = "someFunction"
		]
		createClass => [
			name = "MyClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void MyClass_someFunction(MyClass* const me);", code)
	}

	/**
	 * 
	 */
	@Test def testReturningOperation() {
		val operation = createOperation => [
			name = "returningOperation"
			ownedParameters += createParameter => [
				name = "returning"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "uint32"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("uint32 TestClass_returningOperation(TestClass* const me);", code)
	}


	/**
	 * 
	 */
	@Test def testReturningOperation2() {
		val operation = createOperation => [
			name = "returningFunction"
			ownedParameters += createParameter => [
				name = "ret"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "float"]
			]
		]
		createClass => [
			name = "SomeClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("float SomeClass_returningFunction(SomeClass* const me);", code)
	}

	/**
	 * 
	 */
	@Test def testOneParameterOperation() {
		val operation = createOperation => [
			name = "oneParameterOperation"
			ownedParameters += createParameter => [
				name = "param1"
				type = createPrimitiveType => [name = "uint32"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_oneParameterOperation(TestClass* const me, uint32 param1);", code)
	}

	/**
	 * 
	 */
	@Test def testOneParameterOperation2() {
		val operation = createOperation => [
			name = "singleParameterOperation"
			ownedParameters += createParameter => [
				name = "arg"
				type = createPrimitiveType => [name = "char"]
			]
		]
		createClass => [
			name = "SomeType"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void SomeType_singleParameterOperation(SomeType* const me, char arg);", code)
	}

	/**
	 * 
	 */
	@Test def testTwoParameterOperation() {
		val operation = createOperation => [
			name = "twoParameterOperation"
			ownedParameters += createParameter => [
				name = "param1"
				type = createPrimitiveType => [name = "uint32"]
			]
			ownedParameters += createParameter => [
				name = "param2"
				type = createPrimitiveType => [name = "uint8"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_twoParameterOperation(TestClass* const me, uint32 param1, uint8 param2);",
			code)
	}

	/**
	 * 
	 */
	@Test def testTwoParameterOperation2() {
		val operation = createOperation => [
			name = "twoArgsOperation"
			ownedParameters += createParameter => [
				name = "arg1"
				type = createPrimitiveType => [name = "char"]
			]
			ownedParameters += createParameter => [
				name = "arg2"
				direction = ParameterDirectionKind.INOUT_LITERAL
				type = createPrimitiveType => [name = "int"]
			]
		]
		createClass => [
			name = "TestClass2"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass2_twoArgsOperation(TestClass2* const me, char arg1, int* arg2);",
			code)
	}

	/**
	 * 
	 */
	@Test def testStaticOperation() {
		val operation = createOperation => [
			name = "staticOperation"
			isStatic = true
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_staticOperation();", code)
	}

	/**
	 * 
	 */
	@Test def testStaticOperation2() {
		val operation = createOperation => [
			name = "staticFunction"
			isStatic = true
		]
		createClass => [
			name = "TestType"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestType_staticFunction();", code)
	}

	/**
	 * 
	 */
	@Test def testStaticOperationWithParam() {
		val operation = createOperation => [
			name = "staticOperation"
			isStatic = true
			ownedParameters += createParameter => [
				name = "param1"
				type = createPrimitiveType => [name = "uint32"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_staticOperation(uint32 param1);", code)
	}

	/**
	 * 
	 */
	@Test def testStaticOperationWithParam2() {
		val operation = createOperation => [
			name = "staticOperation"
			isStatic = true
			ownedParameters += createParameter => [
				name = "arg"
				type = createClass => [name = "OtherClass"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_staticOperation(OtherClass* arg);", code)
	}

	/**
	 * 
	 */
	@Test def testImplementedOperation() {
		val operation = createOperation => [
			name = "implementedOperation"
			methods += createOpaqueBehavior => [
				languages += "C"
				bodies += "/* hier koennte Ihre Werbung stehen */"
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "implementation")

		Assert.assertEquals('''
		void TestClass_implementedOperation(TestClass* const me) {
			/* hier koennte Ihre Werbung stehen */
		}'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testImplementedOperation2() {
		val operation = createOperation => [
			name = "fibonacci"
			methods += createOpaqueBehavior => [
				languages += "C"
				bodies += "return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;"
			]
			ownedParameters += createParameter => [
				name = "arg"
				type = createPrimitiveType => [name = "int"]
			]
			ownedParameters += createParameter => [
				name = "ret"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "int"]
			]
		]
		createClass => [
			name = "C"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "implementation")

		Assert.assertEquals('''
		int C_fibonacci(C* const me, int arg) {
			return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;
		}'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testDeclaredOperation() {
		val operation = createOperation => [
			name = "implementedOperation"
			methods += createOpaqueBehavior => [
				languages += "C"
				bodies += "/* hier koennte Ihre Werbung stehen */"
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals('''void TestClass_implementedOperation(TestClass* const me);'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testDeclaredOperation2() {
		val operation = createOperation => [
			name = "fibonacci"
			methods += createOpaqueBehavior => [
				languages += "C"
				bodies += "return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;"
			]
			ownedParameters += createParameter => [
				name = "arg"
				type = createPrimitiveType => [name = "int"]
			]
			ownedParameters += createParameter => [
				name = "ret"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "int"]
			]
		]
		createClass => [
			name = "C"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals('''int C_fibonacci(C* const me, int arg);'''.toString, code)
	}
}
