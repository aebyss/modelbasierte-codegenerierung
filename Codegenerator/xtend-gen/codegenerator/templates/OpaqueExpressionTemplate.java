package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import java.util.List;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class OpaqueExpressionTemplate implements Template<OpaqueExpression> {
  @Override
  public String generateCode(final CodegenInterface it, final OpaqueExpression expr, final String context) {
    String _elvis = null;
    String _head = IterableExtensions.<String>head(expr.getBodies());
    String _trim = null;
    if (_head!=null) {
      _trim=_head.trim();
    }
    if (_trim != null) {
      _elvis = _trim;
    } else {
      _elvis = "0";
    }
    final String body = _elvis;
    boolean _startsWith = body.startsWith("RGB565(");
    if (_startsWith) {
      int _length = body.length();
      int _minus = (_length - 1);
      final String[] parts = body.substring(7, _minus).split(",");
      int _size = ((List<String>)Conversions.doWrapArray(parts)).size();
      boolean _equals = (_size == 3);
      if (_equals) {
        try {
          final int r = Integer.decode((parts[0]).trim()).intValue();
          final int g = Integer.decode((parts[1]).trim()).intValue();
          final int b = Integer.decode((parts[2]).trim()).intValue();
          final int rgb565 = ((((r / 8) * 2048) + ((g / 4) * 32)) + (b / 8));
          final String result = Integer.valueOf(rgb565).toString();
          return result;
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            final Exception e = (Exception)_t;
            String _message = e.getMessage();
            String _plus = ("️ RGB565 parse failed: " + _message);
            InputOutput.<String>println(_plus);
            return body;
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
      }
    }
    return body;
  }
}
