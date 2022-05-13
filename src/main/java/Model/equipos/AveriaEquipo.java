package Model.equipos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AveriaEquipo {
    private int idAveria;
    private Date fechaRegistro;
    private String tipoDanio;
    private String descripcion;
    private String prioridad;

private Equipo equipo;
private String encargado;
private int id_encargadoLaboratorio;

}
