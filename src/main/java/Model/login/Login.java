package Model.login;

import Model.administracion.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Login {
    Usuario usuarioModelo;
    UsuarioRol usuarioRolModelo;
}
