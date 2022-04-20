
package global;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ebert
 */
public class Mensajes {

    public String getWarnMsj() {
        return warnMsj;
    }

    public void setWarnMsj(String warnMsj) {
        this.warnMsj = warnMsj;
    }

    public String getInfMsj() {
        return infMsj;
    }

    public void setInfMsj(String infMsj) {
        this.infMsj = infMsj;
    }

    String warnMsj = "Advertencia";
    String infMsj = "Exito";

    public Mensajes() {
    }

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
