package Model.administracion;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String password;
    private Date  fechaRegistro;
    private Boolean estado;
    private Persona persona;
    private int idUsuarioRol;
}
