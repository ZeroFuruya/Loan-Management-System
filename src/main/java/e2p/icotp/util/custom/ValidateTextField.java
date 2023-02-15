package e2p.icotp.util.custom;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ValidateTextField {

    static final int BACK_SCPACE = 8;
    static final int DEL = 123;
    static final int ENTER = 13;
    public static final int NOT_DOT = 9999;

    private boolean error = false;

    public boolean isError() {
        return error;
    }

    public void validateDigit(TextField textField, KeyEvent event, int dot) {

        char currentKeyTyped = event.getCharacter().charAt(0);

        final Boolean INVALID_KEY = (!Character.isDigit(currentKeyTyped)) &&
                (currentKeyTyped != BACK_SCPACE) &&
                (currentKeyTyped != DEL) &&
                (currentKeyTyped != ENTER) &&
                (currentKeyTyped != dot);

        try {

            if (INVALID_KEY) {
                String replaceText = textField.getText().replace(Character.toString(currentKeyTyped), "");
                error = true;
                textField.setText(replaceText);
                textField.positionCaret(textField.getText().length());
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
