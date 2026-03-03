package ca.ucalgary.jack.chidlaw.group24finalsubmission;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("User-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        MainController cont = fxmlLoader.getController();
        stage.setTitle("Calorie Tracker");
        stage.setScene(scene);
        stage.show();
        if(file != null) {
            cont.load(file);
        }
    }

    private static File file = null;

    public static void main(String[] args) {
        if (args.length > 2){
            System.err.println("Expected one command line argument for filename to load from");
        }
        if(args.length == 1){
            String filename = args[0];
            file = new File(args[0]);
            if(!file.exists()||!file.canRead()){
                System.err.println("Can not load from the file "+filename);
                System.exit(1);
            }
        }
        launch();
    }
}