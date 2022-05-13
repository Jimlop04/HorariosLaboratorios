/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.solicitudes;

import Model.administracion.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author ebert
 */
@Data
@AllArgsConstructor
public class AsignaturaProfesor {
    int idAsignaturaProfesor;
    Asignatura asignatura;
    Profesor profesor;
    
}
