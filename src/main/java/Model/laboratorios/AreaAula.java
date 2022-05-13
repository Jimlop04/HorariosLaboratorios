package Model.laboratorios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaAula {
    private int idAreaAula;
    private int laboratorio_idLaboratorio;
    private String codigo;
    private String nombre;
    private Short capacidad;
    
    private int idLaboratorio;
    private int facultad_idfacultad;
    private String nombre_laboratorio;
    private String codigo_laboratorio;

    public AreaAula(int idAreaAula, int laboratorio_idLaboratorio, String codigo, String nombre, Short capacidad) {
        this.idAreaAula = idAreaAula;
        this.laboratorio_idLaboratorio = laboratorio_idLaboratorio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public Object getIdFacultad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AreaAula(int idAreaAula) {
        this.idAreaAula = idAreaAula;
    }

}
