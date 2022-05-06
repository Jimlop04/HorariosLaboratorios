package Model.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RolModuloVista {
    private int idRolModuloVista;
    private boolean ver;
    private boolean crear;
    private boolean estadoRolModuloVista;
    private boolean editar;
    private boolean elminar;

    public RolModuloVista(){

    }
}
