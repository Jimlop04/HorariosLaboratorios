package login.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rol {
    private int idRol;
    private String nombreRol;
    private String descripcionRol;
    private boolean estadoRol;
    public Rol(){

    }
}
