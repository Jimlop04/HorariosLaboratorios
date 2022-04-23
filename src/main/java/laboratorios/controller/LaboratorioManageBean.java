
package laboratorios.controller;

import global.Mensajes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import laboratorios.DAO.LaboratorioDAO;
import laboratorios.model.Laboratorio;

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

}
