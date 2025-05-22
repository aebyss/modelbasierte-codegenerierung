package codegenerator.test.exercise4

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestMain {

    extension UMLFactory factory = UMLFactory.eINSTANCE

    @Test def void testMainGeneratingWithValidBehavior() {
        val op = createOperation => [ name = "start" ]
        val behavior = createOpaqueBehavior => [ name = "start_impl"; specification = op ]
        val cls = createClass => [
            name = "Game"
            classifierBehavior = behavior
            ownedOperations += op
        ]
        val inst = createInstanceSpecification => [
            name = "gameInstance"
            classifiers += cls
        ]
        val model = createModel => [
            name = "Model"
            packagedElements += cls
            packagedElements += inst
        ]

        val generator = new Uml2C()
        val code = generator.generateCode(model, "main")
        println("--- Generierter main.c ---\n" + code)

        val includeLine = "#include \"Model/Game.h\""
        println("Include" + includeLine)
        val expected = '''
			«includeLine»

			int main(void) {
				Model_Game_start(&Model_gameInstance);
				return 0;
			}
		'''.toString

        val actual = code

        Assert.assertEquals(expected, actual)
    }

    @Test def void testMainIgnoresInstanceWithoutBehavior() {
        val cls = createClass => [ name = "NoBehavior" ]
        val inst = createInstanceSpecification => [
            name = "ignored"
            classifiers += cls
        ]
        val model = createModel => [
            name = "Model"
            packagedElements += cls
            packagedElements += inst
        ]

        val generator = new Uml2C()
        val code = generator.generateCode(model, "main")
        println("--- Generierter Code (ohne Behavior) ---\n" + code)

        // Nur main-Funktion mit return
        val expected = '''
			int main(void) {
				return 0;
			}
		'''.toString

        val actual = code
        Assert.assertTrue(actual.contains(expected)) // Kein include – keine Instanz verarbeitet
    }

    @Test def void testFallbackOnBehaviorNameIfNoOperation() {
        val behavior = createOpaqueBehavior => [ name = "do_something" ]
        val cls = createClass => [
            name = "Helper"
            classifierBehavior = behavior
        ]
        val inst = createInstanceSpecification => [
            name = "helperInstance"
            classifiers += cls
        ]
        val model = createModel => [
            name = "Model"
            packagedElements += cls
            packagedElements += inst
        ]

        val generator = new Uml2C()
        val code = generator.generateCode(model, "main")
        val includeLine = "#include \"Model/Helper.h\""
        println("--- Fallback-Fall ---\n" + code)

        val expected = '''
			«includeLine»

			int main(void) {
				Model_Helper_do_something(&Model_helperInstance);
				return 0;
			}
		'''.toString

        val actual = code

        Assert.assertEquals(expected, actual)
    }
}
