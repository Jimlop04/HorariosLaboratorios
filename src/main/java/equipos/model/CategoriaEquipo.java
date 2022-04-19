package equipos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaEquipo {
    private int idCategoriaEquipo;
    private String nombre;
    private String descripcion;
}
