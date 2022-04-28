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
import global.AES;
import java.text.SimpleDateFormat;

public class ProfesorDAO extends Conexion {

    private ResultSet result;
    private AES encryptAES;

    public List<Profesor> listarProfesor() throws SQLException {
        List<Profesor> listProfesores;
        try {
            String querry = "select * from laboratorio.listar_docente();";
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
                        result.getString("dni"),
                        result.getString("correo"),
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
                profesores = new Profesor(
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

    public boolean insertarProfesor(Profesor pro) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sql;
        encryptAES = new AES();
        sql = "select * from laboratorio.registrar_docente('" + pro.getNombre_persona() + "','" + pro.getApellido_persona() + "'"
                + ",'" + pro.getDni_persona() + "','" + dateFormat.format(pro.getFechanacimiento_persona()) + "'"
                + ",'" + pro.getGenero_persona() + "','" + pro.getCorreo_persona() + "',"
                + "'" + pro.getCelular_persona() + "','" + pro.getNombre_usuario() + "','" + pro.getPassword_usuario() + "');";

        try {
            conectar();
            result = ejecutarSql(sql);
            result.next();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            desconectar();
        }
    }
    public boolean editarProfesor(int idP,int idU, Profesor pro){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "select * from laboratorio.editar_docente('"+idP+"','"+idU+"',"
                + "'"+pro.getNombre_persona()+"','"+pro.getApellido_persona()+"',"
                + "'"+pro.getDni_persona()+"','"+dateFormat.format(pro.getFechanacimiento_persona())+"',"
                + "'"+pro.getGenero_persona()+"','"+pro.getCorreo_persona()+"',"
                + "'"+pro.getCelular_persona()+"','"+pro.getNombre_usuario()+"','"+pro.getPassword_usuario()+"');";
        try{
            conectar();
            result = ejecutarSql(sql);
            result.next();
            return true;
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
}
