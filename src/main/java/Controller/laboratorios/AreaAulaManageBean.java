package Controller.laboratorios;

import global.Mensajes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import DAO.laboratorios.AreaAulaDAO;
import Model.laboratorios.AreaAula;
import org.primefaces.PrimeFaces;

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

    public void registrarArea() throws Exception {

        try {
            if ("".equals(areaAula.getIdLaboratorio())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Codigo Laboratorio");
            } else if ("".equals(areaAula.getCodigo())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Codigo Aula");
            } else if ("".equals(areaAula.getNombre())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Nombre Aula");
            } else if ("".equals(areaAula.getCapacidad())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Capacidad Aula");
            } else {
                this.areaAulaDAO.registrarAula(areaAula);
                mensajesJSF.mensajeDeExito("Registro Exitoso");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
      public void editarArea() {
        try {
            if ("".equals(areaAula.getIdLaboratorio())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", ""));
            } else if ("".equals(areaAula.getCodigo())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Codigo Aula"));
            } else if ("".equals(areaAula.getNombre())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Nombre Aula"));
                } else if ("".equals(areaAula.getCapacidad())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Capacidad Aula"));
            } else {
                this.areaAulaDAO.modificarArea(areaAula);
                listaAreas = areaAulaDAO.getAreas();
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Aula Guardado"));
                
                PrimeFaces.current().executeScript("PF('centroEditarAulaDialog').hide()");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Error al guardar"));
        }
        PrimeFaces.current().ajax().update(":form-principal:integracionLaboratorio");
    } 
    
    

}
