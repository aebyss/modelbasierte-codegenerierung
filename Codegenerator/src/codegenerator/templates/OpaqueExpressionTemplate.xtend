package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.OpaqueExpression

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

				val rgb565 = (r / 8) * 2048 + (g / 4) * 32 + (b / 8)
				val result = rgb565.toString

				return result
			} catch (Exception e) {
				println("⚠️ RGB565 parse failed: " + e.message)
				return body
			}
		}
	}

	return body
}
	

	
}
