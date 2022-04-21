package laboratorios.model;

import lombok.Data;

@Data
public class Laboratorio {
    private int idLaboratorio;
    private String nombre;
    private String codigo;
    private Facultad facultad;

}
