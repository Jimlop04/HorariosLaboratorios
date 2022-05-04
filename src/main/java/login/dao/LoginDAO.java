package login.dao;

import global.Conexion;
import login.model.Login;
import login.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class LoginDAO extends Conexion {
    Login usuario = new Login();
    Rol roles = new Rol();
    ResultSet result;

    List<Rol> listaRoles;

    public LoginDAO() {

    }

    public Login iniciarSesion(Login u) throws SQLException {

        Login usuarioAcceso = null;
        String sentencia = "";
        if (conectar()) {
            try {
                sentencia = String.format(
                        "SELECT * from laboratorio.iniciar_sesion('%1$s','%2$s')",
                        u.getNombre_usuario(), u.getPassword_usuario());
                result = ejecutarSql(sentencia);

                while (result.next()) {

                    usuarioAcceso = new Login(
                            result.getInt("code"),
                            result.getString("reslt"),
                            result.getInt("iduser"),
                            result.getString("name"),
                            result.getString("surname"),
                            result.getString("usrnm"),
                            result.getString("pswrd"),
                            result.getInt("idPersona")
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

    public List<Rol> listarRolId(Login l) {
        List<Rol> miRol = new ArrayList<>();
        Rol usuarioR = null;
        String sentencia = "";
        if (conectar()) {
            try {
                sentencia = String.format(
                        " select *from listar_rol_id('%1$s');",
                        l.getPersona_idPersona());
                result = ejecutarSql(sentencia);

                while (result.next()) {

                    usuarioR = new Rol(
                            result.getInt("code"),
                            result.getString("reslt")
                    );
                    miRol.add(usuarioR);
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally {
                desconectar();
            }
            this.roles = usuarioR;
        }
        return miRol;
    }

    PreparedStatement pstatement;


    public boolean masRol(int idP) {
        boolean band=false;
        System.out.println("DONDE ESTAS");
        int valor = 0;
        try {
            String sql = "select ro.nombre_rol\n" +
                    "                          from laboratorio.persona p\n" +
                    "                                   inner join laboratorio.usuario u\n" +
                    "                                              on p.id_persona = u.persona_id_persona\n" +
                    "                                   inner join laboratorio.usuario_rol ur\n" +
                    "                                              on u.id_usuario = ur.usuario_id_usuario\n" +
                    "                                   inner join laboratorio.rol ro\n" +
                    "                                              on ur.rol_id_rol = ro.id_rol\n" +
                    "                          where u.id_usuario ="+idP;
            System.out.println(sql);
            conectar();
            pstatement = this.getConnection().prepareStatement(sql);
            result = pstatement.executeQuery();

            while (result.next()) {
                valor += 1;
            }
            if(valor>1){
                band = true;
            }


        } catch (Exception e) {

        } finally {
            desconectar();
        }
        return band;
    }
}
