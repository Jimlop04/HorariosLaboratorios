
package laboratorios.controller;

import global.Mensajes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import laboratorios.DAO.LaboratorioDAO;
import laboratorios.model.Laboratorio;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Jimmy
 */

@ManagedBean(name = "laboratorioMB")
@SessionScoped
public class LaboratorioManageBean implements Serializable {
    
    private Laboratorio laboratorio = new Laboratorio();
    private LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
    private List<Laboratorio> listaLaboratorios = new ArrayList<>();
    private List<Laboratorio> listasoloLaboratorios = new ArrayList<>();
    private List<Laboratorio> listafacultades = new ArrayList<>();
    Mensajes mensajesJSF;
    
     @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
        listaLaboratorios = laboratorioDAO.getLaboratorios();
        mensajesJSF = new Mensajes();
        laboratorio = new Laboratorio();
        listasoloLaboratorios = laboratorioDAO.getsoloLaboratorios();
        listafacultades = laboratorioDAO.getfacultades();
    } 

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public LaboratorioDAO getLaboratorioDAO() {
        return laboratorioDAO;
    }

    public void setLaboratorioDAO(LaboratorioDAO laboratorioDAO) {
        this.laboratorioDAO = laboratorioDAO;
    }

    public List<Laboratorio> getListaLaboratorios() {
        return listaLaboratorios;
    }

    public void setListaLaboratorios(List<Laboratorio> listaLaboratorios) {
        this.listaLaboratorios = listaLaboratorios;
    }

    public Mensajes getMensajesJSF() {
        return mensajesJSF;
    }

    public void setMensajesJSF(Mensajes mensajesJSF) {
        this.mensajesJSF = mensajesJSF;
    }

    public List<Laboratorio> getListasoloLaboratorios() {
        return listasoloLaboratorios;
    }

    public void setListasoloLaboratorios(List<Laboratorio> listasoloLaboratorios) {
        this.listasoloLaboratorios = listasoloLaboratorios;
    }

    public List<Laboratorio> getListafacultades() {
        return listafacultades;
    }

    public void setListafacultades(List<Laboratorio> listafacultades) {
        this.listafacultades = listafacultades;
    }

    
     public void editarLaboratorio() {
        try {
            if ("".equals(laboratorio.getNombre_facultad())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Facultad"));
            } else if ("".equals(laboratorio.getCodigo_laboratorio())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Codigo Laboratorio"));
            } else if ("".equals(laboratorio.getNombre_laboratorio())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Nombre Laboratorio"));
            } else {
                this.laboratorioDAO.modificarLaboratorio(laboratorio);
                listaLaboratorios = laboratorioDAO.getLaboratorios(); 
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Laboratorio Guardado"));
                
                PrimeFaces.current().executeScript("PF('centroEditarLaboratorioDialog').hide()");
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Error al guardar"));
        }
        PrimeFaces.current().ajax().update(":form-principal:dtLaboratorio");
    }

    
}
