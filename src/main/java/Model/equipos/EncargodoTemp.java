package Model.equipos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncargodoTemp {
    private int idEncargado;
    private int idEncargadoDetalle;
    private String nombre;
    private String apellido;
}
