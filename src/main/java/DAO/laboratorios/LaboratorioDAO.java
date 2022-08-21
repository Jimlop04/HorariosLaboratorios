/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.laboratorios;

import global.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.laboratorios.AreaAula;
import Model.laboratorios.Laboratorio;
import Model.solicitudes.Facultad;
import java.sql.PreparedStatement;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Jimmy
 */
public class LaboratorioDAO {

    Conexion conexion = new Conexion();
    private Laboratorio laboratorio;
    private ResultSet resultSet;
    private List<Laboratorio> listaLaboratorios;
    private List<Laboratorio> listasoloLaboratorios;
    private List<Laboratorio> listafacultades;
    private TreeNode rootIntegracion;
    String sentencia;

    public LaboratorioDAO() {
        laboratorio = new Laboratorio();
        conexion = new Conexion();
        listaLaboratorios = new ArrayList<>();
        listasoloLaboratorios = new ArrayList<>();
        listafacultades = new ArrayList<>();
        rootIntegracion = new DefaultTreeNode("Root Node", null);
    }

    public LaboratorioDAO(Laboratorio laboratorio) {
        conexion = new Conexion();
        this.laboratorio = laboratorio;
    }

    public List<Laboratorio> getLaboratorios() {
        List<Laboratorio> Laboratorios = new ArrayList<>();

        String sql = String.format("select * from laboratorio.laboratorio l inner join laboratorio.facultad f on l.facultad_id_facultad = f.id_facultad");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                Laboratorios.add(new Laboratorio(
                        resultSet.getInt("id_facultad"),
                        resultSet.getString("nombre_facultad").trim(),
                        resultSet.getInt("id_laboratorio"),
                        resultSet.getInt("facultad_id_facultad"),
                        resultSet.getString("nombre_laboratorio").trim(),
                        resultSet.getString("codigo_laboratorio").trim(),
                        resultSet.getShort("capacidad_laboratorio")));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return Laboratorios;
    }

    public List<Laboratorio> getsoloLaboratorios() {
        List<Laboratorio> soloLaboratorios = new ArrayList<>();

        String sql = String.format("SELECT * FROM laboratorio.laboratorio");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                soloLaboratorios.add(new Laboratorio(
                        resultSet.getInt("id_laboratorio"),
                        resultSet.getString("nombre_laboratorio").trim(),
                        resultSet.getString("codigo_laboratorio").trim(),
                        resultSet.getShort("capacidad_laboratorio")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return soloLaboratorios;
    }

    public List<Laboratorio> getfacultades() {
        List<Laboratorio> facultades = new ArrayList<>();

        String sql = String.format("SELECT * FROM laboratorio.facultad");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                facultades.add(new Laboratorio(
                        resultSet.getInt("id_facultad"),
                        resultSet.getString("nombre_facultad").trim()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return facultades;
    }

    public List<Facultad> listarFacultades() {
        List<Facultad> lista = null;

        String sql = String.format("SELECT * FROM laboratorio.facultad");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);
            lista = new ArrayList<>();
            while (resultSet.next()) {
                Facultad obj = new Facultad();
                obj.setIdFacultad(resultSet.getInt("idFacultad"));
                obj.setNombreFacultad(resultSet.getNString("nombreFacultad"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }

    public List<AreaAula> getsoloAreas(int idArea) {
        List<AreaAula> soloAreas = new ArrayList<>();

        String sql = String.format("SELECT * from laboratorio.listar_areas()");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                soloAreas.add(new AreaAula(
                        resultSet.getInt("idarea"),
                        resultSet.getInt("idlaboratoriofk"),
                        resultSet.getString("codarea").trim(),
                        resultSet.getString("nomarea").trim(),
                        resultSet.getShort("caparea")));

            }
            return soloAreas;
        } catch (SQLException e) {
            return soloAreas;
        } finally {
            conexion.desconectar();
        }
    }

    public Laboratorio registrarLab(Laboratorio laboratorio) {
        try {

            sentencia = String.format("SELECT laboratorio.registrar_laboratorio(\n"
                    + "	'" + laboratorio.getIdFacultad() + "', \n"
                    + "	'" + laboratorio.getNombre_laboratorio().trim() + "', \n"
                    + "	'" + laboratorio.getCodigo_laboratorio().trim() + "'\n"
                    + ")");
            conexion.ejecutarSql(sentencia);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return laboratorio;
    }

    public void modificarLaboratorio(Laboratorio laboratorio) throws SQLException {
        try {

            sentencia = String.format("SELECT laboratorio.editar_laboratorio("
                    + "'" + laboratorio.getIdLaboratorio() + "',"
                    + "'" + laboratorio.getIdFacultad() + "',"
                    + "'" + laboratorio.getNombre_laboratorio().trim() + "',"
                    + "'" + laboratorio.getCodigo_laboratorio().trim() + "')");

            System.out.println(sentencia);

            conexion.ejecutarSql(sentencia);
            System.out.println(sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.desconectar();
        }
    }

    public String ObtenerCodigo(int id) {
        String cadena = new String();
        sentencia = String.format("SELECT laboratorio.obtenercodigolaboratorio(" + id + ")");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sentencia);
            if (resultSet.next()) {
                cadena = resultSet.getString(sentencia);
            }

        } catch (SQLException e) {
        } finally {
            conexion.desconectar();
            System.out.println("Error No ahi Codigo");
        }
        return cadena;
    }

}
