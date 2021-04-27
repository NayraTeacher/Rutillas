package com.mycompany.rutillas;

import com.mycompany.rutillas.modelos.Usuario;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ImageView imagen;

    @FXML
    private void login() {
        // App.setRoot("secondary");
        //IMPORTANTE: este metodo no consulta a base de datos y está sin terminar.
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
    
    public void loadImage(){
        Image img = new Image(getClass().getResourceAsStream("/images/P1020615.jpg"));
        imagen.setImage(img);
    }
    
}
