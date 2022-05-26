/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.ajustesPerfil;

import Model.login.Rol;
import global.AjustePerfil;
import global.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AjustePerfilDAO extends Conexion {

    public AjustePerfil ajustePerfil(int usuarioRol, int idPersona, int idUsuario) {
       
        ResultSet resultSet;
        AjustePerfil obj = new AjustePerfil();
        String sql = String.format("select u.estado_usuario,\n"
                + "       u.nombre_usuario,\n"
                + "       u.password_usuario,\n"
                + "       r.nombre_rol,\n"
                + "       p.nombre_persona,\n"
                + "       p.apellido_persona,\n"
                + "       p.dni_persona,\n"
                + "       p.celular_persona,\n"
                + "       p.fecha_nacimiento_persona,\n"
                + "       p.genero_persona,\n"
                + "       p.correo_persona from laboratorio.persona p\n"
                + "inner join laboratorio.usuario u on p.id_persona = u.persona_id_persona\n"
                + "inner join laboratorio.usuario_rol ur on ur.usuario_id_usuario = u.id_usuario\n"
                + "inner join laboratorio.rol r on r.id_rol = ur.rol_id_rol where\n"
                + "ur.id_usuario_rol =" + usuarioRol + " and p.id_persona=" + idPersona + " and u.id_usuario=" + idUsuario + ";");
        try {
            conectar();
            resultSet = ejecutarSql(sql);

            while (resultSet.next()) {
                
                obj.setCedula(resultSet.getString("dni_persona"));
                obj.setNombre(resultSet.getString("nombre_persona"));
                obj.setApellido(resultSet.getString("apellido_persona"));
                obj.setCelular(resultSet.getString("celular_persona"));
                obj.setFechaNacimiento(resultSet.getDate("fecha_nacimiento_persona"));
                obj.setGenero(resultSet.getString("genero_persona"));
                obj.setCorreo(resultSet.getString("correo_persona"));
                obj.setEstadoUsuario(resultSet.getBoolean("estado_usuario"));
                obj.setUsuario(resultSet.getString("nombre_usuario"));
                obj.setPassword(resultSet.getString("password_usuario"));
                obj.setRol(resultSet.getString("nombre_rol"));
             

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
        return obj;
    }
    public Rol roles(int idRol){
        
        ResultSet resultSet;
        Rol obj = new Rol();
        String sql = "select * from laboratorio.rol r WHERE r.id_rol="+idRol;
        try {
            conectar();
            resultSet = ejecutarSql(sql);

            while (resultSet.next()) {                
                obj.setNombreRol(resultSet.getString("nombre_rol"));
                obj.setEstadoRol(resultSet.getBoolean("estado_rol"));
                obj.setDescripcionRol(resultSet.getString("descripcion_rol"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
        return obj;
    }
}
