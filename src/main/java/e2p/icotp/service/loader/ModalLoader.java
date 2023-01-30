package e2p.icotp.service.loader;

import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.layout.modal.LoanerController;
import e2p.icotp.model.Loaner;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class ModalLoader {
    private static FXMLLoader load_modal(App app, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("layout/" + fxml + ".fxml"));

        StackPane pane = loader.load();

        StackPane modal = new StackPane(pane);
        modal.getStyleClass().add("modal-bg");
        modal.setPadding(new Insets(0, 0, 50, 0));
        modal.getStylesheets().add(App.class.getResource("themes/Def_Theme.css").toExternalForm());

        app.getMainScreen().getChildren().add(modal);

        StackPane.setAlignment(pane, Pos.TOP_CENTER);
        StackPane.setMargin(pane, new Insets(75, 0, 0, 0));

        modal.setOnMouseClicked(e -> {
            if (e.getTarget() == modal) {
                e.consume();
            } else {
                e.consume();
            }
        });

        return loader;
    }

    public static void modal_close(App app) {
        int lastIdx = app.getMainScreen().getChildren().size() - 1;
        app.getMainScreen().getChildren().remove(lastIdx);
    }

    public static void load_loaner_update(App app, Loaner loaner, boolean isEdit) throws IOException {
        FXMLLoader loader = load_modal(app, "modal/LOANER");

        LoanerController controller = loader.getController();
        controller.load(app, loaner, isEdit);
    }

    // public static void load_degree_update(App app) throws IOException {
    // FXMLLoader loader = load_modal(app, "modals/DEGREE_UPDATE");

    // DegreeUpdate controller = loader.getController();
    // controller.load(app);
    // }

    // public static void load_error(App app) throws IOException {
    // FXMLLoader loader = load_modal(app, "login/INVALIDACC");
    // }
}
