package Model.equipos;

import Model.laboratorios.AreaAula;
import lombok.*;

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
    private String imagen;
    private Date fechaAdquisicion;
    //Relaciones entidades
    private AreaAula areaAula;
    private CategoriaEquipo categoriaEquipo;



}
