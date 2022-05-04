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
                        resultSet.getString("nombre_facultad"),
                        resultSet.getInt("id_laboratorio"),
                        resultSet.getInt("facultad_id_facultad"),
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

        String sql = String.format("SELECT * FROM laboratorio.laboratorio");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                soloLaboratorios.add(new Laboratorio(
                        resultSet.getInt("id_laboratorio"),
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

    public List<Laboratorio> getfacultades() {
        List<Laboratorio> facultades = new ArrayList<>();

        String sql = String.format("SELECT * FROM laboratorio.facultad");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                facultades.add(new Laboratorio(
                        resultSet.getInt("id_facultad"),
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

        String sql = String.format("SELECT * from laboratorio.listar_areas()");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                soloAreas.add(new AreaAula(
                        resultSet.getInt("idarea"),
                        resultSet.getInt("idlaboratoriofk"),
                        resultSet.getString("codarea"),
                        resultSet.getString("nomarea"),
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
                    + "'" + laboratorio.getNombre_laboratorio() + "',"
                    + "'" + laboratorio.getCodigo_laboratorio() + "')");
            
            System.out.println(sentencia);
            
            conexion.ejecutarSql(sentencia);
            System.out.println(sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.desconectar();
        }
    }
    
    
        public Laboratorio getGrupoById(int id) {
        
        try {
            conexion.conectar();
            ResultSet result = conexion.ejecutarSql("select getgrupocuentabyid(" + id + ")");
            if (result.next()) {
                String cadenaJSON = result.getString("getgrupocuentabyid");
            }
        } catch (SQLException ex) {
            System.out.println("Error getgrupocuenta: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
    return laboratorio;
    }

}
