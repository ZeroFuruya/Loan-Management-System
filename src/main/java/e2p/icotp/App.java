package e2p.icotp;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import e2p.icotp.service.loader.AppLoader;

/**
 * JavaFX App
 */
public class App extends Application {
    private int temp;
    private StackPane mainScreen;
    private Stage mainStage;

    // STASH UPDATE COMMENT

    // Boolean

    @Override
    public void start(Stage stage) throws IOException {
        this.mainStage = stage;
        initializa_main();
    }

    public void initializa_main() throws IOException {
        AppLoader.load_main(this, mainStage);
    }

    public void setMainScreen(StackPane screen) {
        mainScreen = screen;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public StackPane getMainScreen() {
        return this.mainScreen;
    }

    public static void main(String[] args) {
        launch();
    }

}