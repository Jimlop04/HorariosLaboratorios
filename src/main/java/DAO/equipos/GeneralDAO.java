package DAO.equipos;

import Model.administracion.Encargado;
import global.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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


    public ArrayList<Encargado> listarEncargados() throws Exception {
        ArrayList<Encargado> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "select *\n" +
                    "from laboratorio.encargado e\n" +
                    "         inner join laboratorio.encargado_laboratorio el on e.\"idEncargado\" = el.\"encargado_idEncargado\"\n" +
                    "         inner join laboratorio.persona p on p.\"idPersona\" = e.\"persona_idPersona\"";
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
    }


}


