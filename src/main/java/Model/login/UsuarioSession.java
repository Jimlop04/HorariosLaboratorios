package Model.login;

import Model.administracion.Persona;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioSession {
    private int idUsuarioRol;
    private int idPersona;
    private int idRol;
    private int idUsuario;
    private String nombrePersona;
    private String nombreRol;


}
