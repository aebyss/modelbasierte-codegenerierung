package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.OpaqueExpression


//sehr scuffed, in den UML ist OpaqueExpression für den .sly-color gesetzt abre wir haben
//keine tests dafür ich habe jetzt das so hardcodiert 
class OpaqueExpressionTemplate implements Template<OpaqueExpression> {
	
	override generateCode(CodegenInterface it, OpaqueExpression expr, String context) {
	val body = expr.bodies.head?.trim ?: "0"

	if (body.startsWith("RGB565(")) {
		val parts = body.substring(7, body.length - 1).split(",")
		if (parts.size == 3) {
			try {
				val r = Integer.decode(parts.get(0).trim).intValue
				val g = Integer.decode(parts.get(1).trim).intValue
				val b = Integer.decode(parts.get(2).trim).intValue
				
				//RGB565 ist 16 bit Farbformat
				//Rot 5 bits
				//Grün 6 bits
				//Blau 5 bits
				//werte reduzieren mit r / 8 usw. mal 2048 da 2¹¹ ist 2048 also << 11 b / 8 ist auf position 0 und
				//irgendwie ging nicht mit bitshift in xtend wie das im java erfolgt
				//am ende kommt das raus in .c
				/*	.sky_color = 51103,
					.floor_color = 38694,
					.fog_color = 57279, 
					* wass weiß enspricht
					* dank GPT
				 */
				val rgb565 = (r / 8) * 2048 + (g / 4) * 32 + (b / 8)
				val result = rgb565.toString

				return result
			} catch (Exception e) {
				println("️ RGB565 parse failed: " + e.message)
				return body
			}
		}
	}

	return body
}
	

	
}
