package e2p.icotp.service.loader;

import java.io.File;
import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.service.RegistryService;
import e2p.icotp.service.XMLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppLoader {

    private static FXMLLoader load(String filename) {
        return new FXMLLoader(App.class.getResource("layout/" + filename + ".fxml"));
    }

    public static void load_main(App app, Stage mainStage) throws IOException {
        mainStage.setTitle("Loan Management System");
        // mainStage.getIcons().add(new
        // Image(App.class.getResourceAsStream("assets/icons/ThesisManager.png")));

        FXMLLoader loader = load("MAIN");
        StackPane root = loader.load();
        root.setOnMousePressed(e -> root.requestFocus());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(App.class.getResource("themes/Def_Theme.css").toExternalForm());

        app.setMainScreen(root);

        mainStage.setScene(scene);
        mainStage.setMinHeight(720);
        mainStage.setMinWidth(1300);
        mainStage.setMaximized(false);

        mainStage.show();

        MainController controller = loader.getController();
        controller.load(app);

        // Gets and Unwraps XML file
        File xml = RegistryService.getXML_FromRegistry();
        if(xml != null){
            app.getSignUpList().setAll(XMLService.unwrap_signUpXML(app, xml));
            // app.getLoginList().setAll(XMLService.unwrap_loginXML(app, xml));
        }
        
        LogInLoader.load_sign_up(app);
        // LogInLoader.load_log_in(app);
    }
}