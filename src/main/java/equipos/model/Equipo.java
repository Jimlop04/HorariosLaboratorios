package equipos.model;

import laboratorios.model.AreaAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String imagen ="NA";
    private Date fechaAdquisicion;
    private AreaAula areaAula;
    private CategoriaEquipo categoriaEquipo;
}
