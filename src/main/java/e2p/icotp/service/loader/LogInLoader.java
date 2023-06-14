package e2p.icotp.service.loader;

import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.layout.accounts.ForgotPasswordController;
import e2p.icotp.layout.accounts.LogInController;
// import e2p.icotp.layout.accounts.LogInController;
import e2p.icotp.layout.accounts.SignUpController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class LogInLoader {
    static StackPane modal;

    private static FXMLLoader load_modal(App app, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("layout/" + fxml + ".fxml"));

        StackPane pane = loader.load();

        modal = new StackPane(pane);
        modal.getStyleClass().add("login-bg");
        modal.setPadding(new Insets(0, 0, 50, 0));
        change_dark();

        app.getMainScreen().getChildren().add(modal);

        StackPane.setAlignment(pane, Pos.TOP_CENTER);
        StackPane.setMargin(pane, new Insets(75, 0, 0, 0));

        modal.setOnMouseClicked(e -> {
            if (e.getTarget() != modal) {
                e.consume();
            } else {
                modal_close(app);
            }
        });

        return loader;
    }

    public static void modal_close(App app) {
        int lastIdx = app.getMainScreen().getChildren().size() - 1;
        app.getMainScreen().getChildren().remove(lastIdx);
    }

    public static void load_log_in(App app, boolean isLoggedIn, MainController mc) throws IOException {
        FXMLLoader loader = load_modal(app, "accounts/LOGIN");

        LogInController controller = loader.getController();
        controller.load(app, isLoggedIn, mc);
    }

    public static void load_sign_up(App app, MainController mc) throws IOException {
        FXMLLoader loader = load_modal(app, "accounts/SIGNUP");

        SignUpController controller = loader.getController();
        controller.load(app, mc);
    }

    public static void load_forgot_pass(App app) throws IOException {
        FXMLLoader loader = load_modal(app, "accounts/FORGOT");

        ForgotPasswordController controller = loader.getController();
        controller.load(app);
    }

    public static void change_light() {
        modal.getStylesheets().remove(App.class.getResource("themes/Def_Theme.css").toExternalForm());
        modal.getStylesheets().add(App.class.getResource("themes/Def_Theme1.css").toExternalForm());
    }

    public static void change_dark() {
        modal.getStylesheets().remove(App.class.getResource("themes/Def_Theme1.css").toExternalForm());
        modal.getStylesheets().add(App.class.getResource("themes/Def_Theme.css").toExternalForm());
    }
}
