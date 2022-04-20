/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.dao;

import global.AES;
import global.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import login.model.Profesor;

/**
 *
 * @author ebert
 */
public class ProfesorDAO {

    Profesor profesor = new Profesor();
    Conexion conexion;
    AES encryptAES;
    ResultSet result;

    public ProfesorDAO() {
        conexion = new Conexion();
        encryptAES = new AES();
    }

    public Profesor registrarProfesor(Profesor profesor) throws SQLException {

        try {
            conexion.conectar();
            String sentencia = "Select public.registrarprofesor('" + profesor.getNombre_profesor() + "',\n"
                    + "'" + profesor.getApellido_profesor() + "',\n"
                    + "'" + profesor.getCedula_profesor() + "',\n"
                    + "'" + profesor.getGenero_profesor() + "',\n"
                    + "'" + profesor.getFnacimiento_profesor() + "',\n"
                    + "'" + profesor.getUsuario_profesor() + "',\n"
                    + "'" + encryptAES.getAESEncrypt(profesor.getPassword_profesor()) + "');";
            conexion.ejecutarSql(sentencia);
            conexion.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return profesor;
    }

}