package com.mycompany.rutillas;

import com.mycompany.rutillas.modelos.Usuario;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    
    @FXML
    private Label resultado;
    
    @FXML
    private TextField user;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField pass;
    

    @FXML
    private void login() {
        // App.setRoot("secondary");
        String usuario = user.getText();
        Usuario u = new Usuario(-1, user.getText(), email.getText(), pass.getText());
        //TODO: comprobaciones nombre, email y password
        boolean ok = u.checkNombre(usuario);
        if (ok){
            resultado.setText("Boton de login pulsado por el usuario " + usuario);
            App.setUsuario(u);
            try{
                App.loadRutasWindow();
            }catch (IOException e){
                AlertsUtil.mostrarError(e.getMessage());
            }
        }
        else
            resultado.setText("Nombre de usuario incorrecto");
        
        
    }
    
    @FXML
    private void register(){
        AlertsUtil.mostrarError("Registro aun no implementado");
    }
    
}