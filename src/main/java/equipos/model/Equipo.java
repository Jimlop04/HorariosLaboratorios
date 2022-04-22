package equipos.model;

import laboratorios.model.AreaAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {
    private int idEquipo;
    private String codigo;
    private String descripcion;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String estado;
    private String imagen;
    private Date fechaAdquisicion;
    private int idAreaAula;
    private int idCategoriaEquipo;
    private String areaAula;
    private String categoriaEquipo;
}
