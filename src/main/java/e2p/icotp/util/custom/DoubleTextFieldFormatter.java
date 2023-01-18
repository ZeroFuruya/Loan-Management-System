package e2p.icotp.util.custom;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public class DoubleTextFieldFormatter extends TextFormatter<Number> {

    private static String regex = "-?(([1-9][0-9]{0,4})|0)?(\\.[0-9]{0,2})?";

    private static UnaryOperator<Change> filter = change -> {
        String text = change.getControlNewText();
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(text).matches()) {
            return change;
        }
        if ("-".equals(change.getText())) {
            if (change.getControlText().startsWith("-")) {
                change.setText("");
                change.setRange(0, 1);
                change.setCaretPosition(change.getCaretPosition() - 2);
                change.setAnchor(change.getAnchor() - 2);
                return change;
            } else {
                change.setRange(0, 0);
                return change;
            }
        }
        return pattern.matcher(text).matches() ? change : null;
    };

    private static StringConverter<Number> converter = new StringConverter<Number>() {

        @Override
        public Number fromString(String s) {
            return s.isEmpty() ||
                    "-".equals(s) ||
                    ".".equals(s) ||
                    "-.".equals(s) ? 0.0 : Double.valueOf(s);
        }

        @Override
        public String toString(Number n) {
            return n.toString();
        }
    };

    public DoubleTextFieldFormatter() {
        super(converter, 0.0, filter);
    }
}
