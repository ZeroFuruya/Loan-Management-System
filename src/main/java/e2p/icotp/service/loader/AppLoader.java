package e2p.icotp.service.loader;

import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppLoader {

    private static FXMLLoader load(String filename) {
        return new FXMLLoader(App.class.getResource("layout/" + filename + ".fxml"));
    }

    static Scene scene;

    public static void load_main(App app, Stage mainStage) throws IOException {
        mainStage.setTitle("Loan Management System");
        mainStage.getIcons().add(new Image(App.class.getResourceAsStream("assets/images/ZephyrLMS.png")));

        FXMLLoader loader = load("MAIN");
        StackPane root = loader.load();
        root.setOnMousePressed(e -> root.requestFocus());

        scene = new Scene(root);
        change_dark();

        app.setMainScreen(root);

        mainStage.setScene(scene);
        mainStage.setMinHeight(720);
        mainStage.setMinWidth(1300);
        mainStage.setMaximized(false);

        mainStage.show();

        MainController controller = loader.getController();
        controller.load(app);
    }

    public static void change_light() {
        scene.getStylesheets().remove(App.class.getResource("themes/Def_Theme.css").toExternalForm());
        scene.getStylesheets().add(App.class.getResource("themes/Def_Theme1.css").toExternalForm());
    }

    public static void change_dark() {
        scene.getStylesheets().remove(App.class.getResource("themes/Def_Theme1.css").toExternalForm());
        scene.getStylesheets().add(App.class.getResource("themes/Def_Theme.css").toExternalForm());
    }
}