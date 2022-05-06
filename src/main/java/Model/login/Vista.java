package Model.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vista {
    private int idVista;
    private String nombreVista;
    private String descripcionVista;
    private int idModulo;
    private boolean estadoVista;
    public Vista(){

    }
}
