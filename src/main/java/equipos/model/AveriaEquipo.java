package equipos.model;

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

    private String categoriaEquipo;
    private String facultad;
    private String area;
    private String encargado;
    private String descripcionEquipo;
    private String codigoEquipo;
    private String imagen;
    private String labotarorio;
    private String marca;
    private String numeroSerie;
    private String modelo;
    private String estadoEquipo;

    private int idEquipo;

    private int idEncarado;

}
