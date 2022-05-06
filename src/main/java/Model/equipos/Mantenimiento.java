package Model.equipos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mantenimiento {
    private int idMantenimiento;
    private String procedencia;
    private String tipo;
    private String estado;
    private Date periodo;
    private int idEncargadoLaboratorio;
}
