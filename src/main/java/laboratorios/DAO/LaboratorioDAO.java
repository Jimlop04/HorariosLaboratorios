/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorios.DAO;

import global.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import laboratorios.model.AreaAula;
import laboratorios.model.Laboratorio;
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

        String sql = String.format("select * from laboratorio l inner join facultad f on l.facultad_idFacultad = f.\"idFacultad\"");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                Laboratorios.add(new Laboratorio(
                        resultSet.getInt("idFacultad"),
                        resultSet.getString("nombre_facultad"),
                        resultSet.getInt("idLaboratorio"),
                        resultSet.getInt("facultad_idfacultad"),
                        resultSet.getString("nombre_laboratorio"),
                        resultSet.getString("codigo_laboratorio")));

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

        String sql = String.format("SELECT * FROM laboratorio");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                soloLaboratorios.add(new Laboratorio(
                        resultSet.getInt("idLaboratorio"),
                        resultSet.getString("nombre_laboratorio"),
                        resultSet.getString("codigo_laboratorio")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return soloLaboratorios;
    }

    public List<Laboratorio> getsoloLaboratoriosxfacultades(int idLaboratorio) {
        List<Laboratorio> soloLaboratorios = new ArrayList<>();

        String sql = String.format("SELECT * FROM laboratorio " + idLaboratorio + "");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                soloLaboratorios.add(new Laboratorio(
                        resultSet.getInt("idLaboratorio"),
                        resultSet.getString("nombre_laboratorio"),
                        resultSet.getString("codigo_laboratorio")));
            }
            return soloLaboratorios;
        } catch (SQLException e) {
            return soloLaboratorios;

        } finally {
            conexion.desconectar();
        }

    }

    public List<Laboratorio> getfacultades() {
        List<Laboratorio> facultades = new ArrayList<>();

        String sql = String.format("SELECT * FROM facultad");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                facultades.add(new Laboratorio(
                        resultSet.getInt("idFacultad"),
                        resultSet.getString("nombre_facultad")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return facultades;
    }

    public List<AreaAula> getsoloAreas(int idArea) {
        List<AreaAula> soloAreas = new ArrayList<>();

        String sql = String.format("SELECT * from public.area_aula");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                soloAreas.add(new AreaAula(
                        resultSet.getInt("id_area_aula"),
                        resultSet.getInt("laboratorio_idLaboratorio"),
                        resultSet.getString("codigo_aula"),
                        resultSet.getString("nombre_aula"),
                        resultSet.getShort("capacidad_aula")));

            }
            return soloAreas;
        } catch (SQLException e) {
            return soloAreas;
        } finally {
            conexion.desconectar();
        }
    }

    public void modificarLaboratorio(Laboratorio laboratorio) throws SQLException {
        try {

            String sql = "";
            conexion.ejecutarSql(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            conexion.desconectar();
        }
    }

}
