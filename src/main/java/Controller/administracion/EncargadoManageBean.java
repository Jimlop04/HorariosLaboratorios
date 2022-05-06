package Controller.administracion;

import DAO.administracion.EncargadoDAO;
import Model.administracion.Encargado;
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
                } else if ("".equals(encargado.getDescripcion_rol())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Descripción"));
                } else if ("".equals(encargado.getEstado_rol())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Estado"));
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
        
    public void editarEncargado() {
        try {
            if ("".equals(encargado.getNombre_usuario())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Usuario"));
            } else if ("".equals(encargado.getPassword_usuario())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Contraseña"));
                 } else if (encargado.getFechacreacion_usuario() == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", ""));
                 } else if (encargado.getEstado_usuario()) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Estado Usuario"));
                 } else if ("".equals(encargado.getIdPersona())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese "));
                 } else if ("".equals(encargado.getNombre_persona())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getApellido_persona())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese "));
                 } else if ("".equals(encargado.getDni_persona())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if (encargado.getFechanacimiento_persona() == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getGenero_persona())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getCorreo_persona())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getCelular_persona())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getIdRoles())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getIdUsuario())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getNombre_rol())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getFecha_inicio())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getFecha_fin())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getIdLaboratorio())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getIdEncargado())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
                 } else if ("".equals(encargado.getNombre_laboratorio())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
            } else if ("".equals(encargado.getCodigo_laboratorio())) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese"));
            } else {
                this.encargadoDAO.editarEncargado(encargado);
                listaEncargados = encargadoDAO.getEncargados();
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Encargado Editado"));
                
                PrimeFaces.current().executeScript("PF('centroEditarEncargadoDialog').hide()");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Error al guardar"));
        }
        PrimeFaces.current().ajax().update(":form-principal:dtEncargados");
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
