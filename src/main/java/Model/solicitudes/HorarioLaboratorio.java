/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.solicitudes;

import Model.laboratorios.Laboratorio;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ebert
 */
@Data
@NoArgsConstructor
public class HorarioLaboratorio {
    int horarioLaboratorio;
   
    Calendar horaAperturaLaboratorio;
    Calendar horaCierreLaboratorio;
    Calendar fechaRegistroLaboratorio;
    Laboratorio laboratorio;
    PeriodoAcademico periodo;


}
