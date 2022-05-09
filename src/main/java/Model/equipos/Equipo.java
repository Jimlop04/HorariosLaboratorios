package Model.equipos;

import Model.laboratorios.Area;
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
    //Relaciones entidades
    private Area areaAula;
    private CategoriaEquipo categoriaEquipo;

}
