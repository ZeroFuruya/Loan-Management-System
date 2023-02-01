package e2p.icotp.layout.factory;

import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.LoanType;
import e2p.icotp.service.loader.ModalLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    public static HBox createHBox(Node children1, Node children2, Node children3, Button button) {
        HBox hbox = new HBox(children1, children2, children3, button);
        hbox.setBackground(Background.fill(Color.rgb(0, 0, 0, 0)));
        hbox.setPrefHeight(100.0f);
        hbox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(0.5))));
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setOnMouseEntered(e -> {
            if (e.getClickCount() == 1) {
                hbox.prefHeight(hbox.getHeight() * 2);
            }
        });

        // DropShadow dsfx = new DropShadow(BlurType.GAUSSIAN, Color.BLACK, 5, 0, 7, 7);
        // hbox.setEffect(dsfx);
        return hbox;
    }

    public static HBox createLabelContainer(Node children, VBox width, double value, Pos pos, double border) {
        HBox hbox = new HBox(children);
        hbox.setBackground(Background.fill(Color.rgb(0, 0, 0, 0)));
        hbox.prefWidthProperty().bind(width.widthProperty().multiply(value));
        hbox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3),
                        new BorderWidths(border))));
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setAlignment(pos);
        return hbox;
    }

    public static Label createLabel(String text, double font_size) {
        Label label = new Label(text);
        label.wrapTextProperty().set(true);
        label.setFont(Font.font("Arial", font_size));
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.BLACK);
        label.getStyleClass().add("label-dark");
        return label;
    }

    public static Button createButton(String text, Color color, LoanType loan_type, MainController mc, App app) {
        Button button = new Button(text);
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    ModalLoader.load_loan_type_update(app, loan_type, true, mc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        button.setPrefHeight(100.0f);
        button.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0),
                        new BorderWidths(0))));
        button.addEventHandler(ActionEvent.ACTION, eventHandler);
        button.setAlignment(Pos.CENTER);
        return button;
    }
}
