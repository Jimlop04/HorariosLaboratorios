package Model.login;

import Model.administracion.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class UsuarioRol {
    private int idUsuarioRol;
    private Rol rol;
    private Usuario usuario;
    private Date fechaRegistro;
}
