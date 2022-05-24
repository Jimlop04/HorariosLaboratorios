/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package global;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjustePerfil {
    String 
            cedula, 
            nombre,
            apellido,
            genero,
            correo,
            rol,
            celular,
            usuario,
            password;
    boolean estadoUsuario;
    Date fechaNacimiento;
}
