/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 *
 * @author ebert
 */
@Data
@AllArgsConstructor
public class Login {
    int code;
    String reslt;
    int idUsuario;
    String nombre_persona;
    String apellido_persona;
    String nombre_usuario;
    String password_usuario;
    boolean estado_usuario;
    int persona_idPersona;
    List<Rol> roles;

    public Login() {
    }

    public Login(String nombre_usuario, String password_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.password_usuario = password_usuario;
    }

    public Login(int code, String reslt, int idUsuario, String nombre_persona, String apellido_persona, String nombre_usuario, String password_usuario, int persona_idPersona) {
        this.code = code;
        this.reslt = reslt;
        this.idUsuario = idUsuario;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.nombre_usuario = nombre_usuario;
        this.password_usuario = password_usuario;
        this.persona_idPersona = persona_idPersona;
    }
}
