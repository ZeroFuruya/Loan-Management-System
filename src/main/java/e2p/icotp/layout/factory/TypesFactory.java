package e2p.icotp.layout.factory;

import java.awt.Paint;

import com.itextpdf.text.Font.FontFamily;

import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TypesFactory {
    public static HBox createHBox(Node children1, Node children2, Node children3) {
        HBox hbox = new HBox(children1, children2, children3);
        hbox.setBackground(Background.fill(Color.WHITESMOKE));
        hbox.setPrefHeight(100.0f);
        hbox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setOnMouseEntered(e -> {
            if (e.getClickCount() == 1) {
                hbox.prefHeight(hbox.getHeight() * 2);
            }
        });
        return hbox;
    }

    public static HBox createLabelContainer(Node children, VBox width, double value, Pos pos) {
        HBox hbox = new HBox(children);
        hbox.setBackground(Background.fill(Color.WHITESMOKE));
        hbox.prefWidthProperty().bind(width.widthProperty().multiply(value));
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setAlignment(pos);
        return hbox;
    }

    public static Label createLabel(String text, double font_size) {
        Label label = new Label(text);
        label.wrapTextProperty().set(true);
        label.setFont(Font.font("Arial", font_size));
        label.setAlignment(Pos.CENTER);
        return label;
    }
}
