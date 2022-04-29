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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

        String sql = String.format("select * from laboratorio.area_aula aa inner join laboratorio.laboratorio la on la.\"idLaboratorio\" = aa.\"laboratorio_idLaboratorio\"");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                Areas.add(new AreaAula(
                        resultSet.getInt("id_area_aula"),
                        resultSet.getInt("laboratorio_idLaboratorio"),
                        resultSet.getString("codigo_aula"),
                        resultSet.getString("nombre_aula"),
                        resultSet.getShort("capacidad_aula"),
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
        return Areas;
    }

    public AreaAula registrarAula(AreaAula areaAula) {
        try {

            String sentencia = String.format("SELECT laboratorio.registrar_aula(\n"
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
