package Model.equipos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempDetalleMantenimiento {
    private int idMantenimiento;
    private Date periodo;
    private String tipoMantenimiento;
    private String procedencia;
    private String estadoMantenimiento;
    private String nombrePersona;
    private String apellidoPersona;
    private String laboratorio;
    private String area;
    private Date fechaMantenimientoEquipo;
    private String numeroSerie;
    private String descricionEquipo;
    private String estadoEquipo;
    private String categoriaEquipo;
    private int idEquipo;
}
