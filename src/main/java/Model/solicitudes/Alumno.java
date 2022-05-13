/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.solicitudes;

import Model.administracion.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ebert
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
    int idAlumno;
    boolean estadoAlumno;
    Persona persona;
    
}
