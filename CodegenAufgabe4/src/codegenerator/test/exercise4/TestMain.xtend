package codegenerator.test.exercise4

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestMain {

	extension UMLFactory factory = UMLFactory.eINSTANCE
	
	@Test def void testMain() {
	val behavior = createOpaqueBehavior => [
		name = "main"
	]

	val cls = createClass => [
		name = "MyClass"
		classifierBehavior = behavior
	]
	
	val classWithBehavior = createClass => [
		name = "WithMain"
		classifierBehavior = behavior
	]
	
	val classWithoutBehavior = createClass => [
		name = "NoMain"
		// kein classifierBehavior
	]
		
	val instanceGood = createInstanceSpecification => [
		name = "goodInstance"
		classifiers += classWithBehavior
	]
	
	val instanceWrong = createInstanceSpecification => [
		name = "badInstance"
		classifiers += classWithoutBehavior
	]

	val inst = createInstanceSpecification => [
		name = "inst"
		classifiers += cls
	]
	
	val model = createModel => [
		name = "Model"
		packagedElements += classWithBehavior
		packagedElements += classWithoutBehavior
		packagedElements += instanceGood
		packagedElements += instanceWrong
		packagedElements += inst
	]


	val code = new Uml2C().generateCode(model, "main")
	println("TEST main with multiple ssinstances:\n" + code)

	Assert.assertTrue(code.contains("#include"))
	Assert.assertTrue(code.contains("main(&Model_inst);"))
	Assert.assertTrue(code.contains("int main()"))
	Assert.assertTrue(code.contains("main(&Model_goodInstance);"))
	Assert.assertTrue(code.contains("MyClass.h"))
	}
	

}
