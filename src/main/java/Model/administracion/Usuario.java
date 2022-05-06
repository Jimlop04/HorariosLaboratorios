package Model.administracion;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Period;
import java.util.Date;

@Data
@NoArgsConstructor
public class Usuario {
    private int idUsuario;
    private String nombreUsaurio;
    private String password;
    private Date  fechaRegistro;
    private Boolean estado;
    private Persona persona;
}
