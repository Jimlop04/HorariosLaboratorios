package DAO.equipos;

import Model.equipos.CategoriaEquipo;
import Model.equipos.Equipo;
import Model.laboratorios.Laboratorio;
import Model.login.UsuarioSession;
import global.Conexion;
import Model.laboratorios.AreaAula;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO extends Conexion {

    public void resgistrar(Equipo equipo) throws SQLException {
        try {
            String query = "insert into laboratorio.equipo (descripcion_equipo, marca_equipo, modelo_equipo, numero_serie_equipo, estado_equipo, imagen_equipo, categoria_equipo_id_categoria_equipo, fecha_adquisicion_equipo, codigo_equipo, area_id_area)\n"
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            st.setString(1, equipo.getDescripcion());
            st.setString(2, equipo.getMarca());
            st.setString(3, equipo.getModelo());
            st.setString(4, equipo.getNumeroSerie());
            st.setString(5, equipo.getEstado());
            st.setString(6, equipo.getImagen());
            st.setInt(7, equipo.getCategoriaEquipo().getIdCategoriaEquipo());
            st.setDate(8, new java.sql.Date(equipo.getFechaAdquisicion().getTime()));
            st.setString(9, equipo.getCodigo());
            st.setInt(10, equipo.getAreaAula().getIdAreaAula());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    public List<Equipo> listarEquipos(int idLaboratorio) throws Exception {
        List<Equipo> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "select e.id_equipo                as id_equipo,\n"
                    + "       e.codigo_equipo            as codigo,\n"
                    + "       e.descripcion_equipo       as descripcion,\n"
                    + "       e.estado_equipo            as estado,\n"
                    + "       e.marca_equipo             as marca,\n"
                    + "       e.modelo_equipo            as modelo,\n"
                    + "       e.fecha_adquisicion_equipo as fecha_adquisicion,\n"
                    + "       e.numero_serie_equipo      as numero_serie,\n"
                    + "       ce.nombre_categoria_equipo as categoria,\n"
                    + "       ce.id_categoria_equipo     as id_categoria_equipo,\n"
                    + "       aa.nombre_area             as area,\n"
                    + "       aa.id_area                 as id_area,\n"
                    + "       l.nombre_laboratorio       as laboratorio,\n"
                    + "       l.id_laboratorio           as id_laboratorio\n"
                    + "from laboratorio.equipo e\n"
                    + "         inner join laboratorio.categoria_equipo ce on ce.id_categoria_equipo = e.categoria_equipo_id_categoria_equipo\n"
                    + "         inner join laboratorio.area_aula aa on aa.id_area = e.area_id_area\n"
                    + "         inner join laboratorio.laboratorio l on l.id_laboratorio = aa.laboratorio_id_laboratorio\n"
                    + "         inner join laboratorio.encargado_laboratorio el on l.id_laboratorio = el.laboratorio_id_laboratorio\n"
                    + "         inner join laboratorio.encargado en on el.encargado_id_encargado = en.id_encargado\n"
                    + "where l.id_laboratorio = '" + idLaboratorio + "'\n"
                    + "order by categoria asc";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                CategoriaEquipo categoriaEquipo = new CategoriaEquipo();
                AreaAula area = new AreaAula();
                Laboratorio laboratorio = new Laboratorio();

                equipo.setIdEquipo(rs.getInt("id_equipo"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setCodigo(rs.getString("codigo"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setModelo(rs.getString("modelo"));
                equipo.setNumeroSerie(rs.getString("numero_serie"));
                equipo.setEstado(rs.getString("estado"));
                equipo.setFechaAdquisicion(rs.getDate("fecha_adquisicion"));

                area.setIdAreaAula(rs.getInt("id_area"));
                area.setNombre(rs.getString("area"));

                categoriaEquipo.setIdCategoriaEquipo(rs.getInt("id_categoria_equipo"));
                categoriaEquipo.setNombre(rs.getString("categoria"));

                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setNombre_laboratorio(rs.getString("laboratorio"));

                equipo.setAreaAula(area);
                equipo.setCategoriaEquipo(categoriaEquipo);
                lista.add(equipo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

    public List<Equipo> listarEquiposByEncargado(int idPersona) throws Exception {
        List<Equipo> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "select e.id_equipo                as id_equipo,\n"
                    + "       e.codigo_equipo            as codigo,\n"
                    + "       e.descripcion_equipo       as descripcion,\n"
                    + "       e.estado_equipo            as estado,\n"
                    + "       e.marca_equipo             as marca,\n"
                    + "       e.modelo_equipo            as modelo,\n"
                    + "       e.fecha_adquisicion_equipo as fecha_adquisicion,\n"
                    + "       e.numero_serie_equipo      as numero_serie,\n"
                    + "       ce.nombre_categoria_equipo as categoria,\n"
                    + "       ce.id_categoria_equipo     as id_categoria_equipo,\n"
                    + "       aa.nombre_area             as area,\n"
                    + "       aa.id_area                 as id_area,\n"
                    + "       l.nombre_laboratorio       as laboratorio,\n"
                    + "       l.id_laboratorio           as id_laboratorio\n"
                    + "from laboratorio.equipo e\n"
                    + "         inner join laboratorio.categoria_equipo ce on ce.id_categoria_equipo = e.categoria_equipo_id_categoria_equipo\n"
                    + "         inner join laboratorio.area_aula aa on aa.id_area = e.area_id_area\n"
                    + "         inner join laboratorio.laboratorio l on l.id_laboratorio = aa.laboratorio_id_laboratorio\n"
                    + "         inner join laboratorio.encargado_laboratorio el on l.id_laboratorio = el.laboratorio_id_laboratorio\n"
                    + "         inner join laboratorio.encargado en on el.encargado_id_encargado = en.id_encargado\n"
                    + "where en.persona_id_persona = '" + idPersona + "'\n"
                    + "order by categoria asc";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                CategoriaEquipo categoriaEquipo = new CategoriaEquipo();
                AreaAula area = new AreaAula();
                Laboratorio laboratorio = new Laboratorio();

                equipo.setIdEquipo(rs.getInt("id_equipo"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setCodigo(rs.getString("codigo"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setModelo(rs.getString("modelo"));
                equipo.setNumeroSerie(rs.getString("numero_serie"));
                equipo.setEstado(rs.getString("estado"));
                equipo.setFechaAdquisicion(rs.getDate("fecha_adquisicion"));

                area.setIdAreaAula(rs.getInt("id_area"));
                area.setNombre(rs.getString("area"));

                categoriaEquipo.setIdCategoriaEquipo(rs.getInt("id_categoria_equipo"));
                categoriaEquipo.setNombre(rs.getString("categoria"));

                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setNombre_laboratorio(rs.getString("laboratorio"));

                equipo.setAreaAula(area);
                equipo.setCategoriaEquipo(categoriaEquipo);
                lista.add(equipo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

    public Equipo equipoByID(Equipo e) throws SQLException {
        Equipo equipo = null;
        ResultSet rs;
        try {
            String query = "select e.id_equipo                as id_equipo,\n"
                    + "       e.codigo_equipo            as codigo,\n"
                    + "       e.descripcion_equipo       as descripcion,\n"
                    + "       e.estado_equipo            as estado,\n"
                    + "       e.marca_equipo             as marca,\n"
                    + "       e.modelo_equipo            as modelo,\n"
                    + "       e.fecha_adquisicion_equipo as fehca_adquisicion,\n"
                    + "       e.numero_serie_equipo      as numero_serie,\n"
                    + "       ce.nombre_categoria_equipo as categoria,\n"
                    + "       ce.id_categoria_equipo     as id_categoria_equipó,\n"
                    + "       aa.nombre_area             as area,\n"
                    + "       aa.id_area                 as id_area,\n"
                    + "       l.nombre_laboratorio       as laboratorio,\n"
                    + "       l.id_laboratorio           as id_laboratorio\n"
                    + "from laboratorio.equipo e\n"
                    + "         inner join laboratorio.categoria_equipo ce on ce.id_categoria_equipo = e.categoria_equipo_id_categoria_equipo\n"
                    + "         inner join laboratorio.area_aula aa on aa.id_area = e.area_id_area\n"
                    + "         inner join laboratorio.laboratorio l on l.id_laboratorio = aa.laboratorio_id_laboratorio\n"
                    + "where e.id_equipo ="+e.getIdEquipo();
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                equipo = new Equipo();
                CategoriaEquipo categoriaEquipo = new CategoriaEquipo();
                AreaAula area = new AreaAula();
                Laboratorio laboratorio = new Laboratorio();

                equipo.setIdEquipo(rs.getInt("id_equipo"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setCodigo(rs.getString("codigo"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setModelo(rs.getString("modelo"));
                equipo.setNumeroSerie(rs.getString("numero_serie"));
                equipo.setEstado(rs.getString("estado"));
                equipo.setFechaAdquisicion(rs.getDate("fehca_adquisicion"));

                area.setIdAreaAula(rs.getInt("id_area"));
                area.setNombre(rs.getString("area"));

                categoriaEquipo.setIdCategoriaEquipo(rs.getInt("id_categoria_equipo"));
                categoriaEquipo.setNombre(rs.getString("categoria"));

                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setNombre_laboratorio(rs.getString("laboratorio"));

                equipo.setAreaAula(area);
                equipo.setCategoriaEquipo(categoriaEquipo);
            }

        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.desconectar();
        }
        return equipo;
    }

    public Equipo modificarEquipo(Equipo eq) throws SQLException {
        Equipo equipo = null;
        ResultSet rs;
        try {
            String query = "UPDATE equipo (descripcion_equipo, marca_equipo, modelo_equipo, numero_serie_equipo, estado_equipo, categoria_equipo_id_categoria_equipo, fehca_adquisicion_equipo, codigo_equipo, area_id_area ) VALUES(?,?,?,?,?,?,?,?,?)";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            st.setString(1, eq.getDescripcion());
            st.setString(2, eq.getMarca());
            st.setString(3, eq.getModelo());
            st.setString(4, eq.getNumeroSerie());
            st.setString(5, eq.getEstado());
            st.setInt(6, eq.getCategoriaEquipo().getIdCategoriaEquipo());
            st.setDate(7, (Date) eq.getFechaAdquisicion());
            st.setString(8, eq.getCodigo());
            st.setInt(9, eq.getAreaAula().getIdAreaAula());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            throw ex;
        } finally {
            this.desconectar();
        }
        return equipo;
    }

}
