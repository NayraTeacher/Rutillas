package com.mycompany.rutillas;

import com.mycompany.rutillas.dao.RutaDAO;
import com.mycompany.rutillas.modelos.Ruta;
import com.mycompany.rutillas.modelos.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RutaController {
    
    private static Usuario user;
    private static RutaDAO rdao;
    private ObservableList<NivelesDificultad> niveles = FXCollections.observableArrayList(Arrays.asList(NivelesDificultad.values()));
    
    @FXML
    private ComboBox nivel;
    @FXML
    private ListView listaRutas;
    @FXML
    private Label hellouser;
    
    
    @FXML
    private void addRuta(){
        //lista.add(new Usuario("Usuario nombre", "usuario@email.com", "123"));
    }

    public RutaController(Usuario u){
        user = u;
        conectarBD();
    }
    private static void conectarBD(){
        rdao = new RutaDAO();
        try {
            rdao.conectar();
        } catch (SQLException sqle) {
            AlertsUtil.mostrarError("Error al conectar con la base de datos" + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            AlertsUtil.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertsUtil.mostrarError("Error al cargar la configuración");
        }

    }
   
    
    public void initLists(){
        hellouser.setText("Bienvenido "+ user.getNombre());
        nivel.setItems(niveles);
        listaRutas.getItems().clear();
        try {
            List<Ruta> rutas = rdao.listRutas();
            listaRutas.setItems(FXCollections.observableList(rutas));


        } catch (SQLException sqle) {
            AlertsUtil.mostrarError("Error cargando los datos de la aplicación");
        }
        
    }
    
    public void disconnect(){
        try {
            rdao.desconectar();

        } catch (SQLException sqle) {
            AlertsUtil.mostrarError("Error cargando los datos de la aplicación");
        }
        
        
    }
}