package DAO.login;

import Model.administracion.Usuario;
import global.Conexion;
import Model.login.Rol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class RolDAO extends Conexion {
    public List<Rol> listarRoles(int id) throws Exception {
        List<Rol> listaRoles;
        ResultSet rs;
        try {
            String query = "select ro.nombre_rol as rol, ur.id_usuario_rol as id_usuario_rol\n" +
                    "from laboratorio.persona p\n" +
                    "         inner join laboratorio.usuario u on p.id_persona = u.persona_id_persona\n" +
                    "         inner join laboratorio.usuario_rol ur on u.id_usuario = ur.usuario_id_usuario\n" +
                    "         inner join laboratorio.rol ro on ur.rol_id_rol = ro.id_rol\n" +
                    "where p.id_persona =" + id;
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            listaRoles = new ArrayList<>();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdUsuarioRol(rs.getInt("id_usuario_rol"));
                rol.setNombreRol(rs.getString("rol"));
                listaRoles.add(rol);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return listaRoles;
    }

    public List<Rol> listarRolesByUserName(Usuario username) throws Exception {
        List<Rol> listaRoles;
        ResultSet rs;
        try {
            String query = "select ro.nombre_rol as rol, ur.id_usuario_rol as id_usuario_rol\n" +
                    "from laboratorio.persona p\n" +
                    "         inner join laboratorio.usuario u on p.id_persona = u.persona_id_persona\n" +
                    "         inner join laboratorio.usuario_rol ur on u.id_usuario = ur.usuario_id_usuario\n" +
                    "         inner join laboratorio.rol ro on ur.rol_id_rol = ro.id_rol\n" +
                    "where u.nombre_usuario='"+username.getNombreUsuario()+"';";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            listaRoles = new ArrayList<>();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdUsuarioRol(rs.getInt("id_usuario_rol"));
                rol.setNombreRol(rs.getString("rol"));
                listaRoles.add(rol);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return listaRoles;
    }

    public int personaByUserName(Usuario usuario)throws Exception {

        int idPersona=0 ;
        ResultSet rs;
        try {
            String query = " select p.id_persona as id_persona\n" +
                    "        from laboratorio.persona p\n" +
                    "        inner join laboratorio.usuario u on p.id_persona = u.persona_id_persona\n" +
                    "        inner join laboratorio.usuario_rol ur on u.id_usuario = ur.usuario_id_usuario\n" +
                    "        inner join laboratorio.rol ro on ur.rol_id_rol = ro.id_rol\n" +
                    "        where u.nombre_usuario =" + usuario.getNombreUsuario();
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                idPersona = rs.getInt("id_persona");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return idPersona;
    }


    public Rol listarRoleObtenido(int id) throws Exception {
        Rol listaRoles = new Rol();
        ResultSet rs;
        try {
            String query = "SELECT ro.nombre_rol as rol" +
                    "FROM laboratorio.usuario u\n" +
                    "         inner join laboratorio.persona p on p.id_persona = u.persona_id_persona\n" +
                    "         inner join laboratorio.usuario_rol on u.id_usuario = usuario_rol.usuario_id_usuario\n" +
                    "         inner join laboatorio.rol ro on usuario_rol.rol_id_rol = ro.id_rol\n" +
                    "where id_usuario_rol =" + id;
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setNombreRol(rs.getString("rol"));
                listaRoles = rol;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return listaRoles;
    }
}
