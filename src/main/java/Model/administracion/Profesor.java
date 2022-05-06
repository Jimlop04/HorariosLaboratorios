package Model.administracion;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Profesor implements Serializable{
    int idPersona, idUsuario, idRol, idUsuaioRoles;
    //DATOS PERSONA
    String nombre_persona,
            apellido_persona,
            dni_persona,
            genero_persona,
            correo_persona,
            celular_persona;
    Date fechanacimiento_persona;
    //DATOS USUARIO
    String nombre_usuario, password_usuario;
    boolean estado_usuario;
    //DATOS ROLES
    String nombre_rol;

    //CONSTRUCTORES PERSONALIZADOS

    public Profesor(int idPersona,
                    int idUsuario,
                    int idRol,
                    String nombre_persona,
                    String apellido_persona,
                    String dni_persona,
                    String correo_persona,                    
                    Date fechanacimiento_persona,
                    String genero_persona,
                    String celular_persona,
                    String nombre_rol,
                    boolean estado_usuario,
                    String nombre_usuario,
                    String password_usuario
    ) {
        this.idPersona = idPersona;
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.dni_persona = dni_persona;
        this.fechanacimiento_persona = fechanacimiento_persona;
        this.genero_persona = genero_persona;
        this.correo_persona = correo_persona;
        this.celular_persona = celular_persona;
        this.estado_usuario = estado_usuario;
        this.nombre_rol = nombre_rol;
        this.nombre_usuario = nombre_usuario;
        this.password_usuario = password_usuario;
    }

    public Profesor() {
    }


}
