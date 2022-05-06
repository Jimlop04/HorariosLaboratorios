package Model.administracion;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Persona {
    private int idPersona;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;
    private String dni;
    private String correo;
    private String telefono;
}
