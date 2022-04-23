
package laboratorios.controller;

import global.Mensajes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import laboratorios.DAO.AreaAulaDAO;
import laboratorios.model.AreaAula;

/**
 *
 * @author Jimmy
 */
    
@ManagedBean(name = "areaAulaMB")

public class AreaAulaManageBean implements Serializable {

private AreaAula areaAula = new AreaAula();
private AreaAulaDAO areaAulaDAO = new AreaAulaDAO();
private List<AreaAula> listaAreas = new ArrayList<>();
Mensajes mensajesJSF;

     @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
        listaAreas = areaAulaDAO.getAreas();
        mensajesJSF = new Mensajes();
        areaAula = new AreaAula();
    }

    public AreaAula getAreaAula() {
        return areaAula;
    }

    public void setAreaAula(AreaAula areaAula) {
        this.areaAula = areaAula;
    }

    public AreaAulaDAO getAreaAulaDAO() {
        return areaAulaDAO;
    }

    public void setAreaAulaDAO(AreaAulaDAO areaAulaDAO) {
        this.areaAulaDAO = areaAulaDAO;
    }

    public List<AreaAula> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<AreaAula> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public Mensajes getMensajesJSF() {
        return mensajesJSF;
    }

    public void setMensajesJSF(Mensajes mensajesJSF) {
        this.mensajesJSF = mensajesJSF;
    }
    
    
    
    
}
    
    
    
    
    

