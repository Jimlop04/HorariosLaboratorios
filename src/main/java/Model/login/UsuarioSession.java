package Model.login;

import Model.administracion.Persona;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioSession {
    private int idUsuarioRol;
    private Persona persona;
    private Rol rol;
    private String nombrePersona;
    private String nombreRol;
}
