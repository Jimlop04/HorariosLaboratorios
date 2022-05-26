/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.solicitudes;

import Model.administracion.Encargado;
import Model.administracion.Profesor;
import java.sql.Time;
import java.util.Date;
import Model.laboratorios.Laboratorio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practica {
    Encargado encargado;
    Profesor profesor;
    Laboratorio laboratorio;
    PeriodoAcademico PeriodoAcademico;
    int idEstado;
    TipoSolicitud idTipoSolicitud;
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
    String estado;

   
}
