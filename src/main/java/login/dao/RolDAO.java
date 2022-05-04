package login.dao;

import global.Conexion;
import laboratorios.model.AreaAula;
import login.model.Modulo;
import login.model.RolModuloVista;
import login.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.faces.model.SelectItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    "where p.id_persona ="+id;
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
}
