package equipos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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
    private int area_aula=1;
    private CategoriaEquipo categoriaEquipo;
}
