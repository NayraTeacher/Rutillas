package com.mycompany.rutillas;

import com.mycompany.rutillas.dao.RutaDAO;
import com.mycompany.rutillas.modelos.Ruta;
import com.mycompany.rutillas.modelos.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class RutaController {
    
    private static Usuario user;
    private static RutaDAO rdao;
    private Ruta rutaSel;
    private ObservableList<NivelesDificultad> niveles = FXCollections.observableArrayList(Arrays.asList(NivelesDificultad.values()));
    
    @FXML
    private ComboBox nivel;
    @FXML
    private ListView listaRutas;
    @FXML
    private Label hellouser;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField nombre;
    @FXML
    private TextField distancia;
    @FXML
    private TextField desnivel;

    
    
    
    @FXML
    private void addRuta(){
        
        initLists();
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
    private void cargarRuta(Ruta ruta) {
        nombre.setText(ruta.getNombre());
        fecha.setValue(ruta.getFecha().toLocalDate());
        desnivel.setText(String.valueOf(ruta.getDesnivel()));
        distancia.setText(String.valueOf(ruta.getDistancia()));
        
    }
    
    private void limpiarRuta(Ruta ruta) {
        nombre.setText("");
        fecha.setValue(java.time.LocalDate.now());
        desnivel.setText("");
        distancia.setText("");
        
    }

    @FXML
    public void seleccionarRuta(Event event) {
        rutaSel = (Ruta)listaRutas.getSelectionModel().getSelectedItem();
        cargarRuta(rutaSel);
    }
    public void disconnect(){
        try {
            rdao.desconectar();

        } catch (SQLException sqle) {
            AlertsUtil.mostrarError("Error cargando los datos de la aplicación");
        }
        
        
    }
}