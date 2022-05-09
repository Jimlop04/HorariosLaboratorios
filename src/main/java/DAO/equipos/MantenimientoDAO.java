package DAO.equipos;


import Model.administracion.Encargado;
import Model.equipos.DetalleMantenimiento;
import Model.equipos.Equipo;
import Model.equipos.Mantenimiento;
import Model.equipos.TempDetalleMantenimiento;
import global.Conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MantenimientoDAO extends Conexion {
    LocalDate fecha_servidor = LocalDate.now();

    public ArrayList<TempDetalleMantenimiento> listarDetalleGeneral() throws Exception {
        ArrayList<TempDetalleMantenimiento> lista;
        ResultSet rs;
        try {
            String query = "select ma.id_mantenimiento    as id_mantenimiento,\n" +
                    "       ma.periodo             as periodo,\n" +
                    "       ma.tipo                as tipo_mantenimiento,\n" +
                    "       ma.procedencia         as procedencia,\n" +
                    "       ma.estado              as estado_mantenimiento,\n" +
                    "       p.nombre_persona       as nombre_persona,\n" +
                    "       p.apellido_persona     as apellido_persona,\n" +
                    "       l.nombre_laboratorio   as laboratorio,\n" +
                    "       aa.nombre_aula         as area,\n" +
                    "       dm.fecha_mantenimiento as fecha_mantenimiento_equipo,\n" +
                    "       e.numero_serie         as numero_serie,\n" +
                    "       e.descripcion          as descripcion_equipo,\n" +
                    "       e.estado               as estado_equipo,\n" +
                    "       ce.nombre              as categoria_equipo,\n" +
                    "       e.id_equipo            as id_equipo\n" +
                    "from laboratorio.mantenimientos ma\n" +
                    "         inner join laboratorio.detalle_mantenimiento dm on ma.id_mantenimiento = dm.id_mantenimiento\n" +
                    "         inner join laboratorio.equipos e on e.id_equipo = dm.id_equipo\n" +
                    "         inner join laboratorio.encargado_laboratorio el on el.\"idEncargadoLaboratorio\" = ma.id_encargado_laboratorio\n" +
                    "         inner join laboratorio.area_aula aa on aa.id_area_aula = e.id_area_aula\n" +
                    "         inner join laboratorio.laboratorio l on aa.\"laboratorio_idLaboratorio\" = l.\"idLaboratorio\"\n" +
                    "         inner join laboratorio.encargado e2 on e2.\"idEncargado\" = el.\"encargado_idEncargado\"\n" +
                    "         inner join laboratorio.usuario u on e2.\"persona_idPersona\" = u.\"persona_idPersona\"\n" +
                    "         inner join laboratorio.persona p on p.\"idPersona\" = e2.\"persona_idPersona\"\n" +
                    "         inner join laboratorio.categoria_equipos ce on ce.id_categoria_equipos = e.id_categoria_equipos";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                TempDetalleMantenimiento obj = new TempDetalleMantenimiento();
                obj.setIdMantenimiento(rs.getInt("id_mantenimiento"));
                obj.setPeriodo(rs.getDate("periodo"));
                obj.setTipoMantenimiento(rs.getString("tipo_mantenimiento"));
                obj.setProcedencia(rs.getString("procedencia"));
                obj.setEstadoMantenimiento(rs.getString("estado_mantenimiento"));
                obj.setNombrePersona(rs.getString("nombre_persona"));
                obj.setApellidoPersona(rs.getString("apellido_persona"));
                obj.setLaboratorio(rs.getString("laboratorio"));
                obj.setArea(rs.getString("area"));
                obj.setFechaMantenimientoEquipo(rs.getDate("fecha_mantenimiento_equipo"));
                obj.setNumeroSerie(rs.getString("numero_serie"));
                obj.setDescricionEquipo(rs.getString("descripcion_equipo"));
                obj.setEstadoEquipo(rs.getString("estado_equipo"));
                obj.setCategoriaEquipo(rs.getString("categoria_equipo"));
                obj.setIdEquipo(rs.getInt("id_equipo"));
                lista.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

    public void resgistrar(Mantenimiento obj, List<DetalleMantenimiento> ltsDetalle) throws SQLException {
        try {
            //INSERT MANTENIMIENTO (CABECERA)
            String sql_registrar_mantenimiento = "insert into laboratorio.mantenimientos (periodo, id_encargado_laboratorio, procedencia, tipo)\n" +
                    "values (?, ?, ?, ?);";
            this.conectar();
            this.getConnection().setAutoCommit(false);
            PreparedStatement st = this.getConnection().prepareStatement(sql_registrar_mantenimiento);
            st.setDate(1, new Date(obj.getPeriodo().getTime()));
            st.setInt(2, obj.getIdEncargadoLaboratorio());
            st.setString(3, obj.getProcedencia());
            st.setString(4, obj.getTipo());
//            st.setString(5, "T");
            st.executeUpdate();
            st.close();
            //OBTENER ÚLTIMO ID DE MANTENIMIENTO
            String sql_max_id_mantenimiento = "select max(id_mantenimiento) from laboratorio.mantenimientos";
            PreparedStatement st2 = this.getConnection().prepareStatement(sql_max_id_mantenimiento);
            ResultSet resultSet;
            resultSet = st2.executeQuery();
            int idMantenimiento = 0;
            while (resultSet.next()) {
                idMantenimiento = resultSet.getInt(1);
            }
            System.out.println(ltsDetalle);
            //INSERT DETALLE_MANTENIMIENTO
            String sql_registrar_detalle_mantenimiento = "insert into laboratorio.detalle_mantenimiento (id_equipo, id_mantenimiento)\n" +
                    "values (?, ?)";
            PreparedStatement st3 = this.getConnection().prepareStatement(sql_registrar_detalle_mantenimiento);
            for (DetalleMantenimiento o : ltsDetalle) {
                st3.setInt(1, o.getIdEquipo());
                st3.setInt(2, idMantenimiento);
//                st3.setDate(3, Date.valueOf(fecha_servidor));
                st3.executeUpdate();
                st3.close();
            }
            this.getConnection().commit();
        } catch (Exception e) {
            this.getConnection().rollback();
            throw e;
        } finally {
            this.desconectar();
        }
    }

    public ArrayList<Encargado> listarEncargados() throws Exception {
        ArrayList<Encargado> lista;
        ResultSet rs;
        try {
            String query = "select el.\"idEncargadoLaboratorio\" as id, p.nombre_persona as nombre, p.apellido_persona as apellido\n" +
                    "from laboratorio.encargado e\n" +
                    "         inner join laboratorio.persona p on p.\"idPersona\" = e.\"persona_idPersona\"\n" +
                    "         inner join laboratorio.encargado_laboratorio el on e.\"idEncargado\" = el.\"encargado_idEncargado\"";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Encargado o = new Encargado();
                o.setIdencargadoLaboratorioDetalle(rs.getInt("id"));
                o.setApellido_persona(rs.getString("nombre"));
                o.setApellido_persona(rs.getString("apellido"));
                lista.add(o);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }



}
