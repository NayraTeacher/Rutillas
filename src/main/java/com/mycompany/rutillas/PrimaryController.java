package com.mycompany.rutillas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
    
    @FXML
    private Label resultado;
    
    @FXML
    private TextField user;
    
    @FXML
    private TextField pass;
    

    @FXML
    private void login() {
        // App.setRoot("secondary");
        String usuario = user.getText();
        Usuario u = new Usuario();
        boolean ok = u.checkNombre(usuario);
        if (ok)
            resultado.setText("Boton de login pulsado por el usuario " + usuario);
        else
            resultado.setText("Nombre de usuario incorrecto");
    }
    
    @FXML
    private void loQueHaceMiBoton(){
        resultado.setText("Prueba otro boton");
    }
    
}
