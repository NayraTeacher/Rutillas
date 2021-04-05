package com.mycompany.rutillas;

import com.mycompany.rutillas.modelos.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Usuario user = new Usuario();

    @Override
    public void start(Stage stage) throws IOException {
        String fxml = "login";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        // Give the controller access to the main app.
        //RutaController controller = fxmlLoader.getController();
        //controller.inicializaListados();
    }

    static void loadRutasWindow() throws IOException {
        String fxml = "rutas";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        // Give the controller access to the main app.
        RutaController controller = new RutaController(user);
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
        controller.initLists();

    }
    
    static void setUsuario(Usuario u) {       
        user = u;

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}