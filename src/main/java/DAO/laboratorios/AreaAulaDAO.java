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

/**
 *
 * @author Jimmy
 */
public class AreaAulaDAO {

    Conexion conexion = new Conexion();
    private AreaAula areaAula;
    private ResultSet resultSet;
    private List<AreaAula> listaAreas;

    public AreaAulaDAO() {
        areaAula = new AreaAula();
        conexion = new Conexion();
        listaAreas = new ArrayList<>();
    }

    public AreaAulaDAO(AreaAula areaAula) {
        conexion = new Conexion();
        this.areaAula = areaAula;
    }

    public List<AreaAula> getAreas() {
        List<AreaAula> Areas = new ArrayList<>();

        String sql = String.format("select * from laboratorio.area_aula aa inner join laboratorio.laboratorio la on la.id_laboratorio = aa.laboratorio_id_laboratorio");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                Areas.add(new AreaAula(
                        resultSet.getInt("id_area"),
                        resultSet.getInt("laboratorio_id_laboratorio"),
                        resultSet.getString("codigo_area").trim(),
                        resultSet.getString("nombre_area").trim(),
                        resultSet.getShort("capacidad_area"),
                        resultSet.getInt("id_laboratorio"),
                        resultSet.getInt("facultad_id_facultad"),
                        resultSet.getString("nombre_laboratorio").trim(),
                        resultSet.getString("codigo_laboratorio").trim()));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return Areas;
    }

    public AreaAula registrarAula(AreaAula areaAula) {
        try {

            String sentencia = String.format("SELECT laboratorio.registrar_area(\n"
                    + "	'" + areaAula.getIdLaboratorio() + "', \n"
                    + "	'" + areaAula.getCodigo().trim() + "', \n"
                    + "	'" + areaAula.getNombre().trim() + "', \n"
                    + "	'" + areaAula.getCapacidad() + "'\n"
                    + ")");
            conexion.ejecutarSql(sentencia);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return areaAula;
    }

    public void modificarArea(AreaAula areaAula) throws SQLException {
        try {

            String sentencia = String.format("SELECT laboratorio.editar_aula("
                    + "'" + areaAula.getIdAreaAula() + "',"
                    + "'" + areaAula.getIdLaboratorio() + "',"
                    + "'" + areaAula.getCodigo() + "',"
                    + "'" + areaAula.getNombre() + "',"
                    + "'" + areaAula.getCapacidad() + "')");

            System.out.println(sentencia);

            conexion.ejecutarSql(sentencia);
            System.out.println(sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.desconectar();
        }
    }

}
