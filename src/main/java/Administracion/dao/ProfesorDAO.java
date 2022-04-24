/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administracion.dao;

import global.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Administracion.model.Profesor;

public class ProfesorDAO extends Conexion {

    private ResultSet result;

    public List<Profesor> listarProfesor() throws SQLException {
        List<Profesor> listProfesores;
        try {
            String querry = "select * from listar_docente();";
            conectar();
            result = ejecutarSql(querry);
            listProfesores = new ArrayList<>();
            while (result.next()) {
                listProfesores.add(new Profesor(
                        result.getInt("idP"),
                        result.getInt("idU"),
                        result.getInt("idR"),
                        result.getString("nombre"),
                        result.getString("apellido"),
                        result.getString("correo"),
                        result.getString("dni"),
                        result.getDate("fechanacimiento"),
                        result.getString("genero"),
                        result.getString("celular"),
                        result.getString("rol"),
                        result.getBoolean("estado"),
                        result.getString("usuario"),
                        result.getString("pswd")
                ));

            }
        } catch (SQLException e) {
            throw e;
        } finally {
           desconectar();
        }
        return listProfesores;
    }

     public Profesor getProfesorById(int id) throws SQLException {
        Profesor profesores = new Profesor();
        try {
            String querry = String.format("select * from getdocentebyid('%1$d');", id);
            conectar();
            result = ejecutarSql(querry);
            while (result.next()) {
                profesores= new Profesor(
                        result.getInt("idP"),
                        result.getInt("idU"),
                        result.getInt("idR"),
                        result.getString("nombre"),
                        result.getString("apellido"),
                        result.getString("correo"),
                        result.getString("dni"),
                        result.getDate("fechanacimiento"),
                        result.getString("genero"),
                        result.getString("celular"),
                        result.getString("rol"),
                        result.getBoolean("estado"),
                        result.getString("usuario"),
                        result.getString("pswd")
                );

            }
        } catch (SQLException e) {
            throw e;
        } finally {
           desconectar();
        }
        return profesores;
    }
}
