package equipos.DAO;

import equipos.model.AveriaEquipo;
import equipos.model.Equipo;
import global.Conexion;
import laboratorios.model.AreaAula;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AveriaEquipoDAO extends Conexion {
//    public List<AveriaEquipo> listarAreasAulasID(int idLaboratorio) throws Exception {
//        List<AveriaEquipo> lista;
//        ResultSet rs;
//        try {
//            String query = "Select ce.nombre            as categoria,\n" +
//                    "       eq.descripcion       as descripcion,\n" +
//                    "       ae.id_averia_equipo  as id_averia,\n" +
//                    "       eq.codigo            as codigo_equipo,\n" +
//                    "       eq.id_equipo         as id_equipo,\n" +
//                    "       eq.imagen            as imagen,\n" +
//                    "       aa.nombre_aula       as area,\n" +
//                    "       l.nombre_laboratorio as laboratorio,\n" +
//                    "       f.nombre_facultad    as facultad\n" +
//                    "from averias_equipos ae\n" +
//                    "         inner join encargado_laboratorio el on ae.id_encargado_laboratorio = el.\"idEncargadoLaboratorio\"\n" +
//                    "         inner join encargado e on el.\"encargado_idEncargado\" = e.\"idEncargado\"\n" +
//                    "         inner join persona p on e.\"persona_idPersona\" = p.\"idPersona\"\n" +
//                    "         inner join equipos eq on ae.id_equipo = eq.id_equipo\n" +
//                    "         inner join categoria_equipos ce on eq.id_categoria_equipos = ce.id_categoria_equipos\n" +
//                    "         inner join area_aula aa on eq.id_area_aula = aa.id_area_aula\n" +
//                    "         inner join laboratorio l on aa.\"laboratorio_idLaboratorio\" = l.\"idLaboratorio\"\n" +
//                    "         inner join facultad f on l.facultad_idfacultad = f.\"idFacultad\"";
//            this.conectar();
//            PreparedStatement st = this.getConnection().prepareStatement(query);
//            rs = st.executeQuery();
//            lista = new ArrayList<>();
//            while (rs.next()) {
//                AveriaEquipo o = new AveriaEquipo();
//                o.setCategoriaEquipo("categoria");
//                o.setDescripcion("descripcion");
//                o.setIdAveria(rs.getInt("id_averia"));
//                o.setCodigoEquipo("codigo_equipo");
//                o.setIdEquipo(rs.getInt("id_equipo"));
//                o.setImagen(rs.getString("imagen"));
//                o.setArea(rs.getString("area"));
//                o.setLabotarorio(rs.getString("laboratorio"));
//                o.setFacultad(rs.getString("facultad"));
//                lista.add(o);
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.desconectar();
//        }
//        return lista;
//    }

    public void resgistrar(AveriaEquipo o) throws SQLException {
        try {
            String sql_registrar = "insert into averias_equipos (fecha_registro, id_encargado_laboratorio, tipo_danio, descripcion,id_equipo, prioridad)\n" +
                    "values (?,?,?,?,?,?)";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(sql_registrar);
            st.setDate(1,  new Date(o.getFechaRegistro().getTime()));
            st.setInt(2, o.getIdEncarado());
            st.setString(3, o.getTipoDanio());
            st.setString(4, o.getDescripcion());
            st.setInt(5, o.getIdEquipo());
            st.setString(6, o.getPrioridad());
            st.executeUpdate();
        } catch(Exception e){
            throw  e;
        }
        finally {
            this.desconectar();
        }
    }

    public AveriaEquipo listarReporte(int idAveria) throws Exception {
        ResultSet rs;
        AveriaEquipo o = new AveriaEquipo();
        try {
            String query = "Select ce.nombre            as categoria,\n" +
                    "       eq.descripcion       as descripcion,\n" +
                    "       ae.id_averia_equipo  as id_averia,\n" +
                    "       eq.codigo            as codigoEquipo,\n" +
                    "       eq.id_equipo         as id_equipo,\n" +
                    "       eq.imagen            as imagen,\n" +
                    "       aa.nombre_aula       as Area,\n" +
                    "       l.nombre_laboratorio as laboratorio,\n" +
                    "       f.nombre_facultad    as facultad,\n" +
                    "       eq.modelo            as modelo,\n" +
                    "       eq.descripcion       as descripcion_equipo,\n" +
                    "       eq.codigo            as codigo_equipo,\n" +
                    "       eq.numero_serie      as numero_serie,\n" +
                    "       ae.tipo_danio        as tipo_danio\n" +
                    "from averias_equipos ae\n" +
                    "         inner join encargado_laboratorio el on ae.id_encargado_laboratorio = el.\"idEncargadoLaboratorio\"\n" +
                    "         inner join encargado e on el.\"encargado_idEncargado\" = e.\"idEncargado\"\n" +
                    "         inner join persona p on e.\"persona_idPersona\" = p.\"idPersona\"\n" +
                    "         inner join equipos eq on ae.id_equipo = eq.id_equipo\n" +
                    "         inner join categoria_equipos ce on eq.id_categoria_equipos = ce.id_categoria_equipos\n" +
                    "         inner join area_aula aa on eq.id_area_aula = aa.id_area_aula\n" +
                    "         inner join laboratorio l on aa.\"laboratorio_idLaboratorio\" = l.\"idLaboratorio\"\n" +
                    "         inner join facultad f on l.facultad_idfacultad = f.\"idFacultad\" where id_averia_equipo =" + idAveria;
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                o.setCategoriaEquipo(rs.getString("categoria"));
                o.setDescripcion(rs.getString("descripcion"));
                o.setIdAveria(rs.getInt("id_averia"));
                o.setCodigoEquipo(rs.getString("codigoEquipo"));
                o.setIdEquipo(rs.getInt("id_equipo"));
                o.setArea(rs.getString("area"));
                o.setLabotarorio(rs.getString(" laboratorio"));
                o.setFacultad(rs.getString("facultad"));
                o.setModelo(rs.getString("modelo"));
                o.setDescripcionEquipo(rs.getString("descripcion_equipo"));
                o.setCodigoEquipo(rs.getString("codigo_equipo"));
                o.setNumeroSerie(rs.getString("numero_serie"));
                o.setTipoDanio(rs.getString("tipo_danio"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return o;
    }
}

