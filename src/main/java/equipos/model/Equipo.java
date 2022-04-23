package equipos.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
