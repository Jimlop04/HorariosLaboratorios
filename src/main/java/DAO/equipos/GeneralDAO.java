package DAO.equipos;

import Model.administracion.Encargado;
import Model.equipos.CategoriaEquipo;
import Model.laboratorios.AreaAula;
import Model.laboratorios.Laboratorio;
import global.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GeneralDAO extends Conexion {

    /*
    public ArrayList<Equipo> listarEncargados() throws Exception {
        ArrayList<Equipo> lista;
        ResultSet rs;
        try {
            String query = "SELECT ce.nombre           as categoria,\n" +
                    "       e.id_categoria_equipos       as idCategoria,\n" +
                    "       e.descripcion       as descripcion,\n" +
                    "       e.id_equipo       as id,\n" +
                    "       e.marca             as marca,\n" +
                    "       e.modelo            as modelo,\n" +
                    "       e.numero_serie      as serie,\n" +
                    "       e.codigo            as codigo,\n" +
                    "       e.fecha_adquisicion as fechaA,\n" +
                    "       e.imagen            as imagen,\n" +
                    "       aa.nombre_aula      as area,\n" +
                    "       aa.id_area_aula     as idArea,\n" +
                    "       e.estado            as estado\n" +
                    "FROM equipos e\n" +
                    "         inner join categoria_equipos ce on e.id_categoria_equipos = ce.id_categoria_equipos\n" +
                    "         inner join area_aula aa on e.id_area_aula = aa.id_area_aula\n" +
                    "ORDER BY categoria ASC";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setModelo(rs.getString("modelo"));
                equipo.setNumeroSerie(rs.getString("serie"));
                equipo.setFechaAdquisicion(rs.getDate("fechaA"));
                equipo.setEstado(rs.getString("estado"));
                equipo.setCodigo(rs.getString("codigo"));
                equipo.setIdCategoriaEquipo(rs.getInt("idCategoria"));
                equipo.setIdAreaAula(rs.getInt("idArea"));
                equipo.setCategoriaEquipo(rs.getString("categoria"));
                equipo.setAreaAula(rs.getString("area"));
                equipo.setIdEquipo(rs.getInt("id"));
                lista.add(equipo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
     */

 /*   public ArrayList<Encargado> listarEncargados() throws Exception {
        ArrayList<Encargado> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "\n" +
                    "select e.\"idEncargado\"             as id_encargado,\n" +
                    "       el.\"idEncargadoLaboratorio\" as id_encargado_detalle,\n" +
                    "       p.apellido_persona          as apellido,\n" +
                    "       p.nombre_persona            as nombre\n" +
                    "from laboratorio.encargado e\n" +
                    "         inner join laboratorio.encargado_laboratorio el on e.\"idEncargado\" = el.\"encargado_idEncargado\"\n" +
                    "         inner join laboratorio.persona p on p.\"idPersona\" = e.\"persona_idPersona\" order by p.apellido_persona asc";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Encargado encargado = new Encargado();
                encargado.setIdencargadoLaboratorioDetalle(rs.getInt("id_encargado_detalle"));
                encargado.setNombre_persona(rs.getString("nombre"));
                encargado.setApellido_persona(rs.getString("apellido"));
                encargado.setIdEncargadoLaboratorio(rs.getInt("id_Encargado"));
                lista.add(encargado);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }*/
 /*   public List<Encargado> listarEncargados() throws Exception {
        List<Encargado> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "select *\n" +
                    "from laboratorio.encargado\n" +
                    "         inner join laboratorio.persona p on p.\"idPersona\" = encargado.\"persona_idPersona\"\n" +
                    "         inner join laboratorio.encargado_laboratorio el on encargado.\"idEncargado\" = el.\"encargado_idEncargado\" order by apellido_persona asc";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Encargado encargado = new Encargado();
                encargado.setIdencargadoLaboratorioDetalle(rs.getInt("idEncargadoLaboratorio"));
                encargado.setNombre_persona(rs.getString("nombre_persona"));
                encargado.setApellido_persona(rs.getString("apellido_persona"));
                encargado.setIdEncargadoLaboratorio(rs.getInt("idEncargado"));
                lista.add(encargado);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }*/
    public ArrayList<Encargado> listarEncargados() throws SQLException {
        ArrayList<Encargado> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "select *\n"
                    + "from laboratorio.encargado e\n"
                    + "         inner join laboratorio.encargado_laboratorio el on e.\"idEncargado\" = el.\"encargado_idEncargado\"\n"
                    + "         inner join laboratorio.persona p on p.\"idPersona\" = e.\"persona_idPersona\"";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Encargado encargado = new Encargado();
                encargado.setIdencargadoLaboratorioDetalle(rs.getInt("idEncargadoLaboratorio"));
                encargado.setNombre_persona(rs.getString("nombre_persona"));
                encargado.setApellido_persona(rs.getString("apellido_persona"));
                encargado.setIdEncargadoLaboratorio(rs.getInt("idEncargado"));
                lista.add(encargado);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

    public List<CategoriaEquipo> listarCategoriasEquipos() throws Exception {
        List<CategoriaEquipo> lista;
        ResultSet rs;
        try {
            this.conectar();
            String query = "select *\n"
                    + "from laboratorio.categoria_equipo order by nombre_categoria_equipo asc;";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                CategoriaEquipo obj = new CategoriaEquipo();
                obj.setIdCategoriaEquipo(rs.getInt("id_categoria_equipo"));
                obj.setNombre(rs.getString("nombre_categoria_equipo"));
                obj.setDescripcion(rs.getString("descripcion_categoria_equipo"));

                lista.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

    public List<Laboratorio> listarLaboratoriosByEncargadoId(int idPersona) throws Exception {
        List<Laboratorio> lista;
        ResultSet rs;
        try {
            this.conectar();
            String query = "select la.nombre_laboratorio as nombre_laboratorio, la.id_laboratorio as id_laboratorio,el.laboratorio_id_laboratorio as id_encargado_laboratorio\n"
                    + "from laboratorio.laboratorio la\n"
                    + "         inner join laboratorio.encargado_laboratorio el on la.id_laboratorio = el.laboratorio_id_laboratorio\n"
                    + "         inner join laboratorio.encargado en on el.encargado_id_encargado = en.id_encargado\n"
                    + "         inner join laboratorio.persona on en.persona_id_persona = persona.id_persona\n"
                    + "where id_persona=" + idPersona;
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Laboratorio obj = new Laboratorio();
                obj.setIdLaboratorio(rs.getInt("id_laboratorio"));
                obj.setNombre_laboratorio(rs.getString("nombre_laboratorio"));
                obj.setIdEncargadoLaboratorio(rs.getInt("id_encargado_laboratorio"));
                System.out.println(obj);
                lista.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return lista;

    }

    public List<AreaAula> listarAreasByLaboratorioId(int idLaboratorio) throws Exception {
        List<AreaAula> lista;
        ResultSet rs;
        try {
            this.conectar();
            String query = "select aa.id_area as id_area, aa.nombre_area as nombre_area\n"
                    + "from laboratorio.area_aula aa\n"
                    + "where laboratorio_id_laboratorio=" + idLaboratorio;
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                AreaAula obj = new AreaAula();
                obj.setIdAreaAula(rs.getInt("id_area"));
                obj.setNombre(rs.getString("nombre_area"));
                lista.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return lista;

    }

    public void calcularFechas(Calendar inicio, Calendar fin) {
        //
        int HoraA = inicio.getTime().getHours();
        int MinutoA = inicio.getTime().getMinutes();

        int HoraB = fin.getTime().getHours();
        int MinutoB = fin.getTime().getMinutes();
        int horaX = 0;
        int minutoX = 0;

        while ((HoraA & MinutoA) < (MinutoB & HoraB)) {
            //
            int horaI = 0;
            int minutoI = 0;
            
            
        }

    }

}
