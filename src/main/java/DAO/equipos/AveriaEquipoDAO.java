package DAO.equipos;

import Model.administracion.Persona;
import Model.equipos.AveriaEquipo;
import Model.equipos.CategoriaEquipo;
import Model.equipos.Equipo;
import Model.equipos.ReporteAveria;
import Model.laboratorios.AreaAula;
import Model.laboratorios.Laboratorio;
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

    public List<AveriaEquipo> listar(int idPersona) throws Exception {
        List<AveriaEquipo> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "select av.fecha_registro                            as fecha_registro,\n"
                    + "       av.id_averia_equipo                               as id_averia,\n"
                    + "       av.prioridad                                      as prioridad,\n"
                    + "       av.tipo_danio                                     as tipo_danio,\n"
                    + "       eq.codigo_equipo                                  as codigo_equipo,\n"
                    + "       ce.nombre_categoria_equipo                        as categoria_equipo,\n"
                    + "       ce.id_categoria_equipo                            as id_categoria_Equipo,\n"
                    + "       eq.id_equipo                                      as id_equipo,\n"
                    + "       eq.descripcion_equipo                             as descripcion_equipo,\n"
                    + "       av.descripcion                                    as descripcion,\n"
                    + "       (pe.nombre_persona || ' ' || pe.apellido_persona) as tecnico,\n"
                    + "       el.id_encargado_laboratorio                       as id_encargado_laboratorio,\n"
                    + "       la.nombre_laboratorio                             as laboratorio,\n"
                    + "       aa.id_area                                        as id_area,\n"
                    + "       aa.nombre_area                                    as area\n"
                    + "from laboratorio.averia_equipo av\n"
                    + "         inner join laboratorio.equipo eq on av.id_equipo = eq.id_equipo\n"
                    + "         inner join laboratorio.encargado_laboratorio el on av.id_encargado_laboratorio = el.id_encargado_laboratorio\n"
                    + "         inner join laboratorio.encargado en on el.encargado_id_encargado = en.id_encargado\n"
                    + "         inner join laboratorio.categoria_equipo ce on eq.categoria_equipo_id_categoria_equipo = ce.id_categoria_equipo\n"
                    + "         inner join laboratorio.laboratorio la on el.laboratorio_id_laboratorio = la.id_laboratorio\n"
                    + "         inner join laboratorio.persona pe on en.persona_id_persona = pe.id_persona\n"
                    + "         inner join laboratorio.area_aula as aa on eq.area_id_area = aa.id_area\n"
                    + "         WHERE pe.id_persona = '"+idPersona+"'\n"
                    + "order by av.fecha_registro desc";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                AveriaEquipo averiaEquipo = new AveriaEquipo();
                CategoriaEquipo categoriaEquipo = new CategoriaEquipo();
                AreaAula areaAula = new AreaAula();

                averiaEquipo.setIdAveria(rs.getInt("id_averia"));
                averiaEquipo.setFechaRegistro(rs.getDate("fecha_registro"));
                averiaEquipo.setPrioridad(rs.getString("prioridad"));
                averiaEquipo.setTipoDanio(rs.getString("tipo_danio"));
                averiaEquipo.setDescripcion(rs.getString("descripcion"));

                averiaEquipo.setId_encargadoLaboratorio(rs.getInt("id_encargado_laboratorio"));
                averiaEquipo.setEncargado(rs.getString("tecnico"));

                categoriaEquipo.setIdCategoriaEquipo(rs.getInt("id_categoria_equipo"));
                categoriaEquipo.setNombre(rs.getString("categoria_equipo"));

                areaAula.setIdAreaAula(rs.getInt("id_area"));
                areaAula.setNombre(rs.getString("area"));

                areaAula.setNombre_laboratorio(rs.getString("laboratorio"));

                equipo.setCategoriaEquipo(categoriaEquipo);
                equipo.setCodigo(rs.getString("codigo_equipo"));
                equipo.setDescripcion(rs.getString("descripcion_equipo"));
                equipo.setAreaAula(areaAula);

                averiaEquipo.setEquipo(equipo);

                lista.add(averiaEquipo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista   ;
    }

    public List<ReporteAveria> listarAveriaReporteById(AveriaEquipo averia) throws Exception {
        List<ReporteAveria> lista = new ArrayList<>();

        ResultSet rs;
        try {
            this.conectar();
            String query = "select fa.nombre_facultad                           as facultad,\n"
                    + "       av.id_averia_equipo                               as id_averia,\n"
                    + "       av.fecha_registro                                 as fecha_registro,\n"
                    + "       av.prioridad                                      as prioridad,\n"
                    + "       (pe.nombre_persona || ' ' || pe.apellido_persona) as tecnico,\n"
                    + "       ar.nombre_area                                    as area_equipo,\n"
                    + "       eq.codigo_equipo                                  as codigo_equipo,\n"
                    + "       ce.nombre_categoria_equipo                        as categoria_equipo,\n"
                    + "       eq.descripcion_equipo                             as descripcion_equipo,\n"
                    + "       eq.marca_equipo                                   as marca_equipo,\n"
                    + "       eq.modelo_equipo                                  as modelo_equipo,\n"
                    + "       eq.numero_serie_equipo                            as serie_equipo,\n"
                    + "       eq.estado_equipo                                  as estado_equipo,\n"
                    + "       av.tipo_danio                                     as tipo_danio_averia,\n"
                    + "       av.descripcion                                    as descripcion_averia,\n"
                    + "       la.nombre_laboratorio                             as laboratorio\n"
                    + "from laboratorio.averia_equipo av\n"
                    + "         inner join laboratorio.equipo as eq on av.id_equipo = eq.id_equipo\n"
                    + "         inner join laboratorio.area_aula ar on eq.area_id_area = ar.id_area\n"
                    + "         inner join laboratorio.laboratorio la on ar.laboratorio_id_laboratorio = la.id_laboratorio\n"
                    + "         inner join laboratorio.categoria_equipo ce on eq.categoria_equipo_id_categoria_equipo = ce.id_categoria_equipo\n"
                    + "         inner join laboratorio.encargado_laboratorio el on av.id_encargado_laboratorio = el.id_encargado_laboratorio\n"
                    + "         inner join laboratorio.encargado en on el.encargado_id_encargado = en.id_encargado\n"
                    + "         inner join laboratorio.persona pe on en.persona_id_persona = pe.id_persona\n"
                    + "         inner join laboratorio.facultad fa on la.facultad_id_facultad = fa.id_facultad\n"
                    + "where id_averia_equipo =" + averia.getIdAveria();

            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                ReporteAveria reporteAveria = new ReporteAveria();
                reporteAveria.setFacultad(rs.getString("facultad").trim());
                reporteAveria.setIdAveria(rs.getInt("id_averia"));
                reporteAveria.setFechaRegistro(rs.getDate("fecha_registro"));
                reporteAveria.setPrioridad(rs.getString("prioridad").trim());
                reporteAveria.setTecnico(rs.getString("tecnico").trim());
                reporteAveria.setAreaEquipo(rs.getString("area_equipo").trim());
                reporteAveria.setCodigoEquipo(rs.getString("codigo_equipo").trim());
                reporteAveria.setCategoriaEquipo(rs.getString("categoria_equipo").trim());
                reporteAveria.setDescripcionEquipo(rs.getString("descripcion_equipo").trim());
                reporteAveria.setMarcaEquipo(rs.getString("marca_equipo").trim());
                reporteAveria.setModeloEquipo(rs.getString("modelo_equipo").trim());
                reporteAveria.setSerieEquipo(rs.getString("serie_equipo").trim());
                reporteAveria.setEstadoEquipo(rs.getString("estado_equipo").trim());
                reporteAveria.setTipoDanio(rs.getString("tipo_danio_averia").trim());
                reporteAveria.setDescriocionAveria(rs.getString("descripcion_averia").trim());
                reporteAveria.setLaboratorio(rs.getString("laboratorio").trim());
                lista.add(reporteAveria);
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
            st.close();

            String queyUpdate = "UPDATE laboratorio.equipo SET estado_equipo='DAÑADO' WHERE id_equipo ='" + averiaEquipo.getEquipo().getIdEquipo() + "'";
            PreparedStatement stUp = this.getConnection().prepareStatement(queyUpdate);
            stUp.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
}
