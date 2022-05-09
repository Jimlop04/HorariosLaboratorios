package DAO.login;

import Model.administracion.Usuario;
import Model.login.UsuarioSession;
import global.Conexion;
import Model.login.Rol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO extends Conexion {
    ResultSet result;
    List<Rol> listaRoles;


    public UsuarioSession iniciarSesion(Usuario usuario) throws SQLException {

        UsuarioSession usuarioSession = new UsuarioSession();
        String sentencia = "";
        if (conectar()) {
            try {
                sentencia = "SELECT * from laboratorio.iniciar_sesion('" + usuario.getNombreUsuario() + "','" + usuario.getPassword() + "','" + usuario.getIdUsuarioRol() + "')";

                result = ejecutarSql(sentencia);

                while (result.next()) {

                    usuarioSession.setIdUsuarioRol(result.getInt("idusuariorol"));
                    usuarioSession.setIdPersona(result.getInt("idpersona"));
                    usuarioSession.setIdRol(result.getInt("idrol"));
                    usuarioSession.setNombrePersona(result.getString("nombreusuario"));
                    usuarioSession.setNombreRol(result.getString("nombrerol"));
                    usuarioSession.setIdUsuario(result.getInt("idusuario"));
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally {
                desconectar();
            }

        }
        return usuarioSession;
    }





    /*
        public Login rol(Login l) {

            Login usuarioAcceso = null;
            String sentencia = "";
            if (conectar()) {
                try {
                    sentencia = String.format(
                            " select *from laboratorio.rol_by_id_usuario('%1$s');",
                            l.getPersona_idPersona());
                    result = ejecutarSql(sentencia);

                    while (result.next()) {

                        usuarioAcceso = new Login(
                                result.getInt("code"),
                                result.getString("reslt")
                        );
                    }
                } catch (SQLException e) {
                    System.out.println(e.toString());
                } finally {
                    desconectar();
                }
                this.usuario = usuarioAcceso;
            }
            return usuarioAcceso;
        }
    */
    PreparedStatement pstatement;

    public boolean masDeUnRol(Usuario u) {
        boolean var = false;

        String sql = "select count( ro.nombre_rol)\n" +
                "                          from laboratorio.persona p\n" +
                "                                   inner join laboratorio.usuario u\n" +
                "                                              on p.id_persona = u.persona_id_persona\n" +
                "                                   inner join laboratorio.usuario_rol ur\n" +
                "                                              on u.id_usuario = ur.usuario_id_usuario\n" +
                "                                   inner join laboratorio.rol ro\n" +
                "                                              on ur.rol_id_rol = ro.id_rol\n" +
                "                          where u.nombre_usuario = '"+u.getNombreUsuario()+"';";
        try {
            conectar();
            pstatement = connection.prepareStatement(sql);
            result = pstatement.executeQuery();
            while (result.next()) {
                var = true;
            }

        } catch (Exception e) {
            return var;
        } finally {
            desconectar();
        }
        return var;
    }

    // Verifica si un usuario existe en la bd
    public boolean existeUsuario(Usuario u) {
        boolean band = false;
        try {
            String sql = "select  laboratorio.existe_usuario('"+u.getNombreUsuario()+"', '"+u.getPassword()+"');";
            System.out.println(sql);
            conectar();
            pstatement = this.getConnection().prepareStatement(sql);
            result = pstatement.executeQuery();

            while (result.next()) {
                band = result.getBoolean(1);
            }


        } catch (Exception e) {

        } finally {
            desconectar();
        }
        return band;
    }





}
