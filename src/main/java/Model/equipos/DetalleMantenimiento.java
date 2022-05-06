package Model.equipos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleMantenimiento {
    private int id_detalle_mantenimiento;
    private LocalDate fechaMantenimiento;
    //EQUIPO
    private int idEquipo;
    //MANTENIMIENTO
    private int idMantenimiento;
}
