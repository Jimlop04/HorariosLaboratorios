
package global;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ebert
 */
public class Mensajes {

    String warnMsj = "Advertencia";
    String infMsj = "Exito";

    public void mensajeDeAdvertencia(String msj) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN, warnMsj, msj));

    }

    public void mensajeDeExito(String msj) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, infMsj, msj));

    }
}
