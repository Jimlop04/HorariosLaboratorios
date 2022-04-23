package laboratorios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaAula {
    private int idAreaAula;
    private String codigo;
    private String nombre;
    private Short capacidad;
    
    private int idLaboratorio;
    private int facultad_idfacultad;
    private String nombre_laboratorio;
    private String codigo_laboratorio;
}
