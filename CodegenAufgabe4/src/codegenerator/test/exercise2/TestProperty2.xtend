package codegenerator.test.exercise2

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestProperty2 {

	extension UMLFactory factory = UMLFactory.eINSTANCE
	
    /**
	 * 
	 */
	@Test def testSimpleArrayParameter() {
		val property = createProperty => [
			name = "testFloat"
			type = createPrimitiveType => [name = "float"]
			upper = 2
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("float testFloat[2];", code)
	}

    /**
	 * 
	 */
	@Test def testSimpleArrayParameter2() {
		val property = createProperty => [
			name = "testIntArray"
			type = createPrimitiveType => [name = "uint32_t"]
			upper = 15
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("uint32_t testIntArray[15];", code)
	}

    /**
	 * 
	 */
	@Test def testObjectArrayParameter() {
		val property = createProperty => [
			name = "keyboard"
			type = createClass => [name = "Key"]
			upper = 81
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Key* keyboard[81];", code)
	}

    /**
	 * 
	 */
	@Test def testObjectArrayParameter2() {
		val property = createProperty => [
			name = "pacman"
			type = createClass => [name = "Ghost"]
			upper = 4
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Ghost* pacman[4];", code)
	}

    /**
	 * 
	 */
	@Test def testUnlimitedListParameter() {
		val property = createProperty => [
			name = "floatList"
			type = createPrimitiveType => [name = "float"]
			upper = -1
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("float* floatList;", code)
	}

    /**
	 * 
	 */
	@Test def testUnlimitedListParameter2() {
		val property = createProperty => [
			name = "listOfIteger"
			type = createPrimitiveType => [name = "uint32_t"]
			upper = -1
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("uint32_t* listOfIteger;", code)
	}

    /**
	 * 
	 */
	@Test def testUnlimitedObjectListParameter() {
		val property = createProperty => [
			name = "desktop"
			type = createClass => [name = "Icon"]
			upper = -1
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Icon** desktop;", code)
	}

    /**
	 * 
	 */
	@Test def testUnlimitedObjectListParameter2() {
		val property = createProperty => [
			name = "software"
			type = createClass => [name = "Bug"]
			upper = -1
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Bug** software;", code)
	}

    /**
	 * 
	 */
	@Test def testEnumProperty() {
		val property = createProperty => [
			name = "testEnum"
			type = createEnumeration => [name = "AnEnum"]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("AnEnum testEnum;", code)
	}

    /**
	 * 
	 */
	@Test def testArrayProperty2() {
		val property = createProperty => [
			name = "testEnumArray"
			type = createEnumeration => [name = "Aufzaehlung"]
			upper = 15
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Aufzaehlung testEnumArray[15];", code)
	}

    /**
	 * 
	 */
	@Test def testEnumInProperty() {
		val enumeration = createEnumeration => [name = "Aufzaehlung"]
		createPackage => [
			name = "pack"
			ownedTypes += enumeration
		]
		
		val property = createProperty => [
			name = "testEnumArray"
			type = enumeration
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("pack_Aufzaehlung testEnumArray;", code)
	}
}