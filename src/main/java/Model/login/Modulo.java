package Model.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Modulo {
    private int idModulo;
    private String nombreModulo;
    private String descripcionModulo;
    private boolean enabled;
    public Modulo(){

    }
}
