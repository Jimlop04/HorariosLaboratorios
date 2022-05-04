package login.dao;

import global.Conexion;
import login.model.Login;
import login.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
@AllArgsConstructor
public class LoginDAO extends Conexion {
    Login usuario = new Login();
    ResultSet result;

    List<Rol> listaRoles;
    public LoginDAO(){

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

}
