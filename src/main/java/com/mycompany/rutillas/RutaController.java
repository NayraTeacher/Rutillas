package com.mycompany.rutillas;

import com.mycompany.rutillas.dao.RutaDAO;
import com.mycompany.rutillas.modelos.Ruta;
import com.mycompany.rutillas.modelos.Usuario;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
    private TextField localizacion;

    
    
    @FXML
    private void addRoute(){
        try {
            //int id, String nombre, float distancia, int desnivel, java.sql.Date fecha, String localizacion, int dificultad, int usuario
            rdao.guardarRuta(new Ruta(-1, nombre.getText(), Float.parseFloat(distancia.getText()), Integer.parseInt(desnivel.getText()),
                    Date.valueOf(fecha.getValue()), localizacion.getText(), niveles.indexOf(nivel.getValue())+1, user.getId()));
            initLists();
        } catch (SQLException ex) {
            AlertsUtil.mostrarError("Error al crear la ruta. " + ex.getMessage());
        }
        
    }
    
    @FXML
    private void saveRoute(){
        rutaSel = (Ruta)listaRutas.getSelectionModel().getSelectedItem();
        if (rutaSel == null) {
            AlertsUtil.mostrarError("No se ha seleccionado ninguna ruta");
            return;
        }
        try {
            rdao.modificarRuta(new Ruta(rutaSel.getId(), nombre.getText(), Float.parseFloat(distancia.getText()), Integer.parseInt(desnivel.getText()),
                    Date.valueOf(fecha.getValue()), localizacion.getText(), niveles.indexOf(nivel.getValue())+1, user.getId()));
            initLists();
        } catch (SQLException ex) {
            AlertsUtil.mostrarError("Error al modificar la ruta seleccionada. " + ex.getMessage());
        }
    }
    
    @FXML
    private void deleteRoute(){
        rutaSel = (Ruta)listaRutas.getSelectionModel().getSelectedItem();
        if (rutaSel == null) {
            AlertsUtil.mostrarError("No se ha seleccionado ninguna ruta");
            return;
        }
        try {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Eliminar Ruta");
            confirmacion.setContentText("¿Estás seguro de querer eliminar esta ruta?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
                return;
            rdao.deleteRuta(rutaSel);
            initLists();
        } catch (SQLException ex) {
            AlertsUtil.mostrarError("Error al eliminar la ruta seleccionada. " + ex.getMessage());
        }
        
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
        nivel.setValue(niveles.get(ruta.getDificultad()-1));
        localizacion.setText(ruta.getLocalizacion());
        
    }
    
    private void limpiarRuta() {
        nombre.setText("");
        fecha.setValue(java.time.LocalDate.now());
        desnivel.setText("");
        distancia.setText("");
        localizacion.setText("");
        nivel.setValue(0);
    }

    @FXML
    public void seleccionarRuta(Event event) {
        rutaSel = (Ruta)listaRutas.getSelectionModel().getSelectedItem();
        cargarRuta(rutaSel);
    }

    @FXML
    public void disconnect(){
        try {
            rdao.desconectar();
            Platform.exit();

        } catch (SQLException sqle) {
            AlertsUtil.mostrarError("Error desconectando y cerrando la aplicación");
        }
        
        
    }
}