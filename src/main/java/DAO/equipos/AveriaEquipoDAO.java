package DAO.equipos;

import Model.equipos.AveriaEquipo;
import Model.equipos.CategoriaEquipo;
import Model.equipos.Equipo;
import global.Conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AveriaEquipoDAO extends Conexion {

    public List<AveriaEquipo> listarAverias(int IdEncargadoLaboratorio) throws Exception {
        List<AveriaEquipo> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "select av.fecha_registro                                 as fechaRegistro,\n"
                    + "       av.descripcion                                    as descripcion,\n"
                    + "       av.tipo_danio                                     as tipo_danio,\n"
                    + "       av.prioridad                                      as prioridad,\n"
                    + "       eq.id_equipo                                      as id_equipo,\n"
                    + "       ce.nombre_categoria_equipo                        as categoria_equipo,\n"
                    + "       ce.id_categoria_equipo                            as id_categoria_equipo,\n"
                    + "       la.id_laboratorio                                 as id_laboratorio,\n"
                    + "       la.nombre_laboratorio                             as nombre_laboratorio,\n"
                    + "       (pe.nombre_persona || ' ' || pe.apellido_persona) as encargado,\n"
                    + "       el.id_encargado_laboratorio                       as id_encargado_laboratorio\n"
                    + "from laboratorio.averia_equipo av\n"
                    + "         inner join laboratorio.equipo eq on av.id_equipo = eq.id_equipo\n"
                    + "         inner join laboratorio.categoria_equipo as ce\n"
                    + "                    on eq.categoria_equipo_id_categoria_equipo = ce.id_categoria_equipo\n"
                    + "         inner join laboratorio.area_aula au on eq.area_id_area = au.id_area\n"
                    + "         inner join laboratorio.laboratorio la on au.laboratorio_id_laboratorio = la.id_laboratorio\n"
                    + "         inner join laboratorio.encargado_laboratorio el on av.id_encargado_laboratorio = el.id_encargado_laboratorio\n"
                    + "         inner join laboratorio.encargado en on el.encargado_id_encargado = en.id_encargado\n"
                    + "         inner join laboratorio.persona pe on en.persona_id_persona = pe.id_persona\n"
                    + "where av.id_encargado_laboratorio = '" + IdEncargadoLaboratorio + "'\n"
                    + "order by la.nombre_laboratorio asc";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                AveriaEquipo averiaEquipo = new AveriaEquipo();
                CategoriaEquipo categoriaEquipo = new CategoriaEquipo();

                averiaEquipo.setFechaRegistro(rs.getDate("fechaRegistro"));
                averiaEquipo.setPrioridad(rs.getNString("prioridad"));
                averiaEquipo.setTipoDanio(rs.getString("tipo_danio"));
                averiaEquipo.setDescripcion(rs.getString("descripcion"));

                averiaEquipo.setId_encargadoLaboratorio(rs.getInt("id_encargado_laboratorio"));
                averiaEquipo.setEncargado(rs.getString("encargado"));

                categoriaEquipo.setIdCategoriaEquipo(rs.getInt("id_categoria_equipo"));
                categoriaEquipo.setNombre(rs.getString("categoria_equipo"));

                equipo.setCategoriaEquipo(categoriaEquipo);

                averiaEquipo.setEquipo(equipo);

                lista.add(averiaEquipo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

    public void resgistrar(AveriaEquipo averiaEquipo, int idEncargadoLaboratorio) throws SQLException {
        try {
            String query = "insert into laboratorio.averia_equipo (fecha_registro, id_encargado_laboratorio, tipo_danio, descripcion, id_equipo,prioridad)\n"
                    + "values (?, ?, ?, ?, ?, ?)";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            st.setDate(1, new java.sql.Date(averiaEquipo.getFechaRegistro().getTime()));
            st.setInt(2, idEncargadoLaboratorio);
            st.setString(3, averiaEquipo.getTipoDanio());
            st.setString(4, averiaEquipo.getDescripcion());
            st.setInt(5, averiaEquipo.getEquipo().getIdEquipo());
            st.setString(6, averiaEquipo.getPrioridad());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
}
