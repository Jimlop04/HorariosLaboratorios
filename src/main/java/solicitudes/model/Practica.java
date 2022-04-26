/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solicitudes.model;

import Administracion.model.Encargado;
import Administracion.model.Profesor;
import java.sql.Time;
import java.util.Date;
import laboratorios.model.Laboratorio;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Practica {
    Encargado encargado;
    Profesor profesor;
    Laboratorio laboratorio;
    int idPeriodoAcademico;
    int idEstado;
    int idTipoSolicitud;
    int idPractica;
    int numero_estudiantes;
    Date fecha_reserva;
    Time hora_inicio;
    Time hora_fin;
    int numero_practica;
    String tema_practica;
    String objetivo_practica;
    Date fecha_solicitud;
    Date fecha_estado;

    public Practica(){
    }
    
}
