package codegenerator.test.exercise2

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestParameter2 {

	extension UMLFactory factory = UMLFactory.eINSTANCE
	
    /**
	 * 
	 */
	@Test def testEnumParameter() {
		val parameter = createParameter => [
			name = "testEnum"
			type = createEnumeration => [name = "AnEnum"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("AnEnum testEnum", code)
	}

    /**
	 * 
	 */
	@Test def testArrayParameter2() {
		val parameter = createParameter => [
			name = "testEnumArray"
			type = createEnumeration => [name = "Aufzaehlung"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("Aufzaehlung testEnumArray", code)
	}

    /**
	 * 
	 */
	@Test def testEnumInPackage() {
		val enumeration = createEnumeration => [name = "Aufzaehlung"]
		createPackage => [
			name = "pack"
			ownedTypes += enumeration
		]
		
		val parameter = createParameter => [
			name = "testEnumArray"
			type = enumeration
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("pack_Aufzaehlung testEnumArray", code)
	}

}