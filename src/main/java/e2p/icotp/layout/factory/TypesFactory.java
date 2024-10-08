package e2p.icotp.layout.factory;

import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.LoanType;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.LoanTypeDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
import javafx.scene.text.FontWeight;

public class TypesFactory {
    public static HBox createHBox(Node children3, Node button, Node button1) {
        HBox hbox = new HBox(children3, button, button1);
        hbox.setBackground(Background.fill(Color.rgb(0, 0, 0, 0)));
        // hbox.setPrefHeight(100.0f);
        hbox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(0.5))));
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(10);
        // hbox.setOnMouseEntered(e -> {
        // if (e.getClickCount() == 1) {
        // hbox.prefHeight(hbox.getHeight() * 2);
        // }
        // });
        hbox.prefHeight(500.0f);
        hbox.prefWidth(50.0f);
        hbox.setPrefWidth(50.0f);

        // DropShadow dsfx = new DropShadow(null, Color.WHITE, 1, 1, 0, 0);
        // hbox.setEffect(dsfx);
        return hbox;
    }

    public static VBox createVBox(Node labelTitle, Node hbox) {
        VBox vbox = new VBox(labelTitle, hbox);
        vbox.setBackground(Background.fill(Color.rgb(0, 0, 0, 0)));
        vbox.setPrefHeight(200f);
        vbox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(0.5))));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setOnMouseEntered(e -> {
            if (e.getClickCount() == 1) {
                vbox.prefHeight(vbox.getHeight() * 2);
            }
        });

        vbox.setPrefHeight(500.0f);
        vbox.prefWidth(50.0f);
        vbox.setPrefWidth(50.0f);
        // DropShadow dsfx = new DropShadow(null, Color.WHITE, 1, 1, 0, 0);
        // vbox.setEffect(dsfx);
        return vbox;
    }

    public static HBox createLabelContainer(Node children, VBox width, double height, double value, Pos pos,
            double border) {
        HBox hbox = new HBox(children);
        hbox.setBackground(Background.fill(Color.rgb(0, 0, 0, 0)));
        hbox.prefWidthProperty().bind(width.widthProperty().multiply(value));
        hbox.prefHeightProperty().bind(width.heightProperty().multiply(height));
        hbox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3),
                        new BorderWidths(border))));
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setAlignment(pos);
        return hbox;
    }

    public static Label createLabel(String text, FontWeight ft, double font_size) {
        Label label = new Label(text);
        label.wrapTextProperty().set(true);
        label.setFont(Font.font(text, ft, font_size));
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.WHITE);
        label.getStyleClass().add("label-bright");
        return label;
    }

    public static Button createButton(String text, Color color, LoanType loan_type, MainController mc, App app,
            boolean isDelete) {
        Button button = new Button(text);
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if (isDelete) {
                        LoanTypeDAO.remove(loan_type.getId().get());
                        app.loanTypeMasterlist().remove(loan_type);
                    } else {
                        ModalLoader.load_loan_type_update(app, loan_type, true, mc);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        button.setPrefHeight(50.0f);
        button.setPrefWidth(100.0f * 2f);
        button.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0),
                        new BorderWidths(0))));
        button.addEventHandler(ActionEvent.ACTION, eventHandler);
        button.setAlignment(Pos.CENTER);
        button.setCursor(Cursor.HAND);
        return button;
    }
}
