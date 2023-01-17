package e2p.icotp.service.loader;

import java.io.IOException;

import e2p.icotp.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppLoader {

    private static FXMLLoader load(String filename) {
        return new FXMLLoader(App.class.getResource("layout/" + filename + ".fxml"));
    }

    public static void load_main(App app, Stage mainStage) throws IOException {
        mainStage.setTitle("Thesis Manager");
        mainStage.getIcons().add(new Image(App.class.getResourceAsStream("assets/icons/ThesisManager.png")));

        FXMLLoader loader = load("MAIN");
        StackPane root = loader.load();
        root.setOnMousePressed(e -> root.requestFocus());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(App.class.getResource("themes/Def_Theme.css").toExternalForm());

        app.setMainScreen(root);

        mainStage.setScene(scene);
        mainStage.setMinHeight(768);
        mainStage.setMinWidth(1000);
        mainStage.setMaximized(false);

        mainStage.show();

        // MainController controller = loader.getController();
        // controller.load(app);

        // // Gets and Unwraps XML file
        // File xml = RegistryService.getXML_FromRegistry();
        // if (xml != null) {
        // app.getAccountList().setAll(XMLService.unwrap_accountXML(app, xml));
        // }

        // LogInLoader.load_log_in(app);
    }
}