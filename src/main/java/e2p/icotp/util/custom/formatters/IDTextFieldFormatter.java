package e2p.icotp.util.custom.formatters;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.LongStringConverter;

public class IDTextFieldFormatter extends TextFormatter<Long> {

    private static String regex = "-?([0-9][0-9]*)?";

    private static UnaryOperator<Change> filter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches(regex)) {
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
        return null;
    };

    private static StringConverter<Long> converter = new LongStringConverter() {

        @Override
        public Long fromString(String s) {
            return s.isEmpty() ? -1 : super.fromString(s);
        }
    };

    public IDTextFieldFormatter() {
        super(converter, 0l, filter);
    }
}
