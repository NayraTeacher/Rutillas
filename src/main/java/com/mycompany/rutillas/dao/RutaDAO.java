/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rutillas.dao;

import com.mycompany.rutillas.App;
import com.mycompany.rutillas.modelos.Ruta;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;

/**
 *
 * @author Nayra
 */
public class RutaDAO {
    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        
        Properties configuration = new Properties();
        configuration.load(new FileInputStream(new File(App.class.getResource("connectionDB.properties").getPath())));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        conexion = DriverManager.getConnection("jdbc:mariadb://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void guardarRuta(Ruta ruta) throws SQLException {
        String sql = "INSERT INTO rutas.rutas (NOMBRE,DISTANCIA,DESNIVEL,FECHA,LOCALIZACION,DIFICULTAD,USUARIO) VALUES(?,?,?,?,?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, ruta.getNombre());
        sentencia.setFloat(2, ruta.getDistancia());
        sentencia.setInt(3, ruta.getDesnivel());
        sentencia.setDate(4, ruta.getFecha());
        sentencia.setString(5, ruta.getLocalizacion());
        sentencia.setInt(6, ruta.getDificultad());
        sentencia.setInt(7, ruta.getUsuario());
        sentencia.executeUpdate();
    }

    public void deleteRuta(Ruta ruta) throws SQLException {
        String sql = "DELETE FROM rutas WHERE idruta = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, ruta.getId());
        sentencia.executeUpdate();
    }

    public void modificarRuta(Ruta ruta) throws SQLException {
        String sql = "{call sp_UpdateRuta (?,?,?,?,?,?,?,?)}";

        CallableStatement sentencia = conexion.prepareCall(sql);
        sentencia.setInt(1, ruta.getId());
        sentencia.setString(2, ruta.getNombre());
        sentencia.setFloat(3, ruta.getDistancia());
        sentencia.setInt(4, ruta.getDesnivel());
        sentencia.setString(5, ruta.getLocalizacion());
        sentencia.setInt(6, ruta.getDificultad());
        sentencia.setInt(7, ruta.getUsuario());
        sentencia.registerOutParameter(8, java.sql.Types.INTEGER);
  
        sentencia.execute();
        
//        String sql = "UPDATE rutas SET nombre = ?, distancia = ?, desnivel = ?, fecha = ?, localizacion = ?"
//                + ", dificultad = ?, usuario = ? WHERE idruta = ?";
//
//        PreparedStatement sentencia = conexion.prepareStatement(sql);
//        sentencia.setString(1, nueva.getNombre());
//        sentencia.setFloat(2, nueva.getDistancia());
//        sentencia.setInt(3, nueva.getDesnivel());
//        sentencia.setDate(4, nueva.getFecha());
//        sentencia.setString(5, nueva.getLocalizacion());
//        sentencia.setInt(6, nueva.getDificultad());
//        sentencia.setInt(7, nueva.getUsuario());
//        sentencia.setInt(8, antigua.getId());
//        sentencia.executeUpdate();
    }

    public List<Ruta> listRutas() throws SQLException {
        List<Ruta> rutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Ruta ruta = new Ruta();
            ruta.setId(resultado.getInt(1));
            ruta.setNombre(resultado.getString(2));
            ruta.setDistancia(resultado.getFloat(3));
            ruta.setDesnivel(resultado.getInt(4));
            ruta.setFecha(resultado.getDate(5));
            ruta.setLocalizacion(resultado.getString(6));
            ruta.setDificultad(resultado.getInt(7));
            ruta.setUsuario(resultado.getInt(8));
            rutas.add(ruta);
        }

        return rutas;
    }

    public boolean searchRuta(String nombre) throws SQLException {
        String sql = "SELECT * FROM rutas WHERE nombre LIKE ? LIMIT 1";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, nombre);
        ResultSet resultado = sentencia.executeQuery();

        return resultado.next();
    }
    
}
