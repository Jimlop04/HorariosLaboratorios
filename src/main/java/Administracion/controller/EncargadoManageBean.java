package Administracion.controller;

import Administracion.dao.EncargadoDAO;
import Administracion.model.Encargado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import global.Mensajes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import javax.faces.view.ViewScoped;
import org.eclipse.jdt.internal.compiler.util.Messages;

/**
 *
 * @author Jimmy
 */
@ManagedBean(name = "encargadoMB")
@ViewScoped

public class EncargadoManageBean implements Serializable {

    private Encargado encargado = new Encargado();
    private EncargadoDAO encargadoDAO = new EncargadoDAO();
    private List<Encargado> listaEncargados = new ArrayList<>();
    private List<Encargado> listaRoles = new ArrayList<>();
    private List<Encargado> ListTotalRoles = new ArrayList<>();
    Mensajes mensajesJSF;

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
        listaEncargados = encargadoDAO.getEncargados();
        listaRoles = encargadoDAO.getRoles();
        ListTotalRoles = encargadoDAO.getListRoles();
        mensajesJSF = new Mensajes();
        encargado = new Encargado();
      
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }

    public EncargadoDAO getEncargadoDAO() {
        return encargadoDAO;
    }

    public void setEncargadoDAO(EncargadoDAO encargadoDAO) {
        this.encargadoDAO = encargadoDAO;
    }

    public List<Encargado> getListaEncargados() {
        return listaEncargados;
    }

    public void setListaEncargados(List<Encargado> listaEncargados) {
        this.listaEncargados = listaEncargados;
    }

    public Mensajes getMensajesJSF() {
        return mensajesJSF;
    }

    public void setMensajesJSF(Mensajes mensajesJSF) {
        this.mensajesJSF = mensajesJSF;
    }
    
    
    public List<Encargado> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Encargado> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Encargado> getListTotalRoles() {
        return ListTotalRoles;
    }

    public void setListTotalRoles(List<Encargado> ListTotalRoles) {
        this.ListTotalRoles = ListTotalRoles;
    }    


    
        public void editarRol() {
        try {
            if ("".equals(encargado.getNombre_rol())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Nombre Rol"));
            } else {
                this.encargadoDAO.modificarRol(encargado);
                ListTotalRoles = encargadoDAO.getListRoles();
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Rol Editado Correctamante"));
                
                PrimeFaces.current().executeScript("PF('centroEditarRolDialog').hide()");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Error al guardar"));
        }
        PrimeFaces.current().ajax().update(":form-principal:dtRol");
    } 
    
        public void idRolEditable(boolean editable) {
        if (!editable) {
            encargado = new Encargado();
            int cod = encargadoDAO.getultimoIdRol();
            if (cod > 0) {
                encargado.setIdRoles(cod + 1);
            }
        } 
        PrimeFaces.current().executeScript("PF('DialogEditarRol').show();");
    }
    
        public void registrarRoles() throws Exception {

        try {
            if ("".equals(encargado.getNombre_rol())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Nombre");
            } else {
                this.encargadoDAO.registrarRol(encargado);
                mensajesJSF.mensajeDeExito("Registro Exitoso");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
        
/**
    public void registrarEncargado() throws Exception {

        try {

            if ("".equals(encargado.getNombre_encargado())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese un Nombre");
                //  PrimeFaces.current().ajax().update("form:messages");
            } else if ("".equals(encargado.getApellidos_encargado())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese un Apellido");
            } else if ("".equals(encargado.getNombre_rol())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Cargo");

            } else if ("".equals(encargado.getFecha_inicio())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Fecha Inicio");

            } else if ("".equals(encargado.getFecha_fin())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Fecha Fin");

            } else if ("".equals(encargado.isEstado())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Estado");

            } else {
                this.encargadoDAO.registrarEncargado(encargado);
                mensajesJSF.mensajeDeExito("Registro Exitoso");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }**/

}
