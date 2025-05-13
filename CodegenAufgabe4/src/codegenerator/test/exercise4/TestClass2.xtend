package codegenerator.test.exercise4

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestClass2 {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * 
	 */
	@Test def testEmptyClassHeader() {
		val class = createClass => [
			name = "TestClass"
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			typedef struct TestModel_TestClass_struct {
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testEmptyClassHeader2() {
		val class = createClass => [
			name = "ClassWithInstance"
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			typedef struct Model_ClassWithInstance_struct {
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testEmptyClassImpl() {
		val class = createClass => [
			name = "TestClass"
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testEmptyClassImpl2() {
		val class = createClass => [
			name = "ClassWithInstance"
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributeHeader() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			typedef struct TestModel_TestClass_struct {
				int attr;
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributeHeader2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			typedef struct Model_ClassWithInstance_struct {
				float attr;
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributeImpl() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributeImpl2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributeAndSlotHeader() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralInteger => [value = 1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			typedef struct TestModel_TestClass_struct {
				int attr;
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributeAndSlotHeader2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralReal => [value = 42.0]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			typedef struct Model_ClassWithInstance_struct {
				float attr;
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributeAndSlotImpl() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralInteger => [value = 1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
				.attr = 1
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributeAndSlotImpl2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralReal => [value = 42.0]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
				.attr = 42.0
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributesAndSlotsHeader() {
		val attribute0 = createProperty => [
			name = "attr0"
			type = createPrimitiveType => [name = "int"]
		]
		val attribute1 = createProperty => [
			name = "attr1"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute0
			ownedAttributes += attribute1
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute0
					values += createLiteralInteger => [value = 1]
				]
				slots += createSlot => [
					definingFeature = attribute1
					values += createLiteralInteger => [value = 1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			typedef struct TestModel_TestClass_struct {
				int attr0;
				float attr1;
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributesAndSlotsHeader2() {
		val attribute0 = createProperty => [
			name = "attr0"
			type = createPrimitiveType => [name = "float"]
		]
		val attribute1 = createProperty => [
			name = "attr1"
			type = createPrimitiveType => [name = "int"]
		]
		val attribute2 = createProperty => [
			name = "attr2"
			type = createPrimitiveType => [name = "char"]
		]
		val attribute3 = createProperty => [
			name = "attr3"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute0
			ownedAttributes += attribute1
			ownedAttributes += attribute2
			ownedAttributes += attribute3
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute0
					values += createLiteralReal => [value = 42.0]
				]
				slots += createSlot => [
					definingFeature = attribute3
					values += createLiteralReal => [value = 32.0]
				]
				slots += createSlot => [
					definingFeature = attribute1
					values += createLiteralInteger => [value = 3]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			typedef struct Model_ClassWithInstance_struct {
				float attr0;
				int attr1;
				char attr2;
				float attr3;
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributesAndSlotsImpl() {
		val attribute0 = createProperty => [
			name = "attr0"
			type = createPrimitiveType => [name = "int"]
		]
		val attribute1 = createProperty => [
			name = "attr1"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute0
			ownedAttributes += attribute1
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute0
					values += createLiteralInteger => [value = 1]
				]
				slots += createSlot => [
					definingFeature = attribute1
					values += createLiteralReal => [value = 123.4]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
				.attr0 = 1,
				.attr1 = 123.4
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithAttributesAndSlotsImpl2() {
		val attribute0 = createProperty => [
			name = "attr0"
			type = createPrimitiveType => [name = "float"]
		]
		val attribute1 = createProperty => [
			name = "attr1"
			type = createPrimitiveType => [name = "int"]
		]
		val attribute2 = createProperty => [
			name = "attr2"
			type = createPrimitiveType => [name = "char"]
		]
		val attribute3 = createProperty => [
			name = "attr3"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute0
			ownedAttributes += attribute1
			ownedAttributes += attribute2
			ownedAttributes += attribute3
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute0
					values += createLiteralReal => [value = 42.0]
				]
				slots += createSlot => [
					definingFeature = attribute3
					values += createLiteralReal => [value = 32.0]
				]
				slots += createSlot => [
					definingFeature = attribute1
					values += createLiteralInteger => [value = 3]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
				.attr0 = 42.0,
				.attr3 = 32.0,
				.attr1 = 3
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithArrayAttributeAndSlotHeader() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
			upper = 4
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralInteger => [value = 1]
					values += createLiteralInteger => [value = 2]
					values += createLiteralInteger => [value = 3]
					values += createLiteralInteger => [value = 4]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			typedef struct TestModel_TestClass_struct {
				int attr[4];
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithArrayAttributeAndSlotHeader2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
			upper = 2
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralReal => [value = 42.0]
					values += createLiteralReal => [value = 79.1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			typedef struct Model_ClassWithInstance_struct {
				float attr[2];
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithArrayAttributeAndSlotImpl() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
			upper = 4
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralInteger => [value = 1]
					values += createLiteralInteger => [value = 2]
					values += createLiteralInteger => [value = 3]
					values += createLiteralInteger => [value = 4]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
				.attr = {
					1,
					2,
					3,
					4
				}
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithArrayAttributeAndSlotImpl2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
			upper = 2
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralReal => [value = 42.0]
					values += createLiteralReal => [value = 79.1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
				.attr = {
					42.0,
					79.1
				}
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithObjectAttributeAndSlotHeader() {
		val otherClass = createClass => [
			name = "OtherClass"
		]
		val attribute = createProperty => [
			name = "attr"
			type = otherClass
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		val otherInstance = createInstanceSpecification => [
			name = "otherInstance"
			classifiers += otherClass
		]
		createModel => [
			name = "TestModel"
			packagedElements += otherClass
			packagedElements += class
			packagedElements += otherInstance
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createInstanceValue => [instance = otherInstance]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			#include "OtherClass.h"
			
			typedef struct TestModel_TestClass_struct {
				TestModel_OtherClass* attr;
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithObjectAttributeAndSlotHeader2() {
		val otherClass = createClass => [
			name = "TestClass"
		]
		val attribute = createProperty => [
			name = "attr"
			type = otherClass
			upper = 2
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		val otherInstance0 = createInstanceSpecification => [
			name = "testInstance"
			classifiers += otherClass
		]
		val otherInstance1 = createInstanceSpecification => [
			name = "testInstance2"
			classifiers += otherClass
		]
		createModel => [
			name = "Model"
			packagedElements += otherClass
			packagedElements += class
			packagedElements += otherInstance0
			packagedElements += otherInstance1
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createInstanceValue => [instance = otherInstance0]
					values += createInstanceValue => [instance = otherInstance1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			#include "TestClass.h"
			
			typedef struct Model_ClassWithInstance_struct {
				Model_TestClass* attr[2];
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithObjectAttributeAndSlotImpl() {
		val otherClass = createClass => [
			name = "OtherClass"
		]
		val attribute = createProperty => [
			name = "attr"
			type = otherClass
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		val otherInstance = createInstanceSpecification => [
			name = "otherInstance"
			classifiers += otherClass
		]
		createModel => [
			name = "TestModel"
			packagedElements += otherClass
			packagedElements += class
			packagedElements += otherInstance
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createInstanceValue => [instance = otherInstance]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
				.attr = &TestModel_otherInstance
			};
		'''.toString, code)
	}

	/**
	 * 
	 */
	@Test def testClassWithObjectAttributeAndSlotImpl2() {
		val otherClass = createClass => [
			name = "TestClass"
		]
		val attribute = createProperty => [
			name = "attr"
			type = otherClass
			upper = 2
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		val otherInstance0 = createInstanceSpecification => [
			name = "testInstance"
			classifiers += otherClass
		]
		val otherInstance1 = createInstanceSpecification => [
			name = "testInstance2"
			classifiers += otherClass
		]
		createModel => [
			name = "Model"
			packagedElements += otherClass
			packagedElements += class
			packagedElements += otherInstance0
			packagedElements += otherInstance1
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createInstanceValue => [instance = otherInstance0]
					values += createInstanceValue => [instance = otherInstance1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
				.attr = {
					&Model_testInstance,
					&Model_testInstance2
				}
			};
		'''.toString, code)
	}
}
