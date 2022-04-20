package equipos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEquipo {
    private int idCategoriaEquipo;
    private String nombre;
    private String descripcion;
}
