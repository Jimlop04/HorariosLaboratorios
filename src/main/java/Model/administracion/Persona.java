package Model.administracion;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
public class Persona {
    private boolean verifica;
    private int idPersona;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;
    private String dni;
    private String correo;
    private String telefono;

    public Persona(int idPersona, String nombre, String apellido, String genero, String dni) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.dni = dni;
    }
    
}
