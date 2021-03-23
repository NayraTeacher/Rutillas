package com.mycompany.rutillas;

import java.io.IOException;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class SecondaryController {
    
    private ObservableList<Usuario> lista = FXCollections.observableArrayList();
    private ObservableList<ClaseE> clases = FXCollections.observableArrayList(Arrays.asList(ClaseE.values()));
    
    @FXML
    private ComboBox claseEficiencia;
    @FXML
    private ListView listaUsuarios;
    
    @FXML
    private void addUsuario(){
        lista.add(new Usuario("Usuario nombre", "usuario@email.com", "123"));
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    public void inicializaListados() {
        lista.add(new Usuario("Nayra", "ndeniz@email.com", "123"));
        lista.add(new Usuario("Lola", "ndeniz@email.com", "123"));
        lista.add(new Usuario("Pepe", "ndeniz@email.com", "123"));
        lista.add(new Usuario("Juan", "ndeniz@email.com", "123"));
        
        claseEficiencia.setItems(clases);
        listaUsuarios.setItems(lista);
    }
}