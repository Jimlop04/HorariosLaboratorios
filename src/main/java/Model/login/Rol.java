package Model.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    private int idRol;
    private String nombreRol;
    private String descripcionRol;
    private boolean estadoRol;

    private int idUsuarioRol;

}
