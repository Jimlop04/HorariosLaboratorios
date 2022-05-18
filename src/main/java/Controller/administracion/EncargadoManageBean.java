package Controller.administracion;

import DAO.administracion.EncargadoDAO;
import Model.administracion.Encargado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import global.Mensajes;
import java.sql.SQLException;
import java.util.Date;
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
    private List<Encargado> listaLaboratoriosXencargado = new ArrayList<>();
    private List<Encargado> listaRoles = new ArrayList<>();
    private List<Encargado> listaRolesTRUE = new ArrayList<>();
    private List<Encargado> listaUsuarios = new ArrayList<>();
    Mensajes mensajesJSF;
    String dni = "";

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
        listaEncargados = encargadoDAO.getEncargados();
        listaLaboratoriosXencargado = encargadoDAO.getListaLaboratoriosXencargado(dni);
        listaRoles = encargadoDAO.getListRoles();
        listaRolesTRUE = encargadoDAO.getListRolesTRUE();
        mensajesJSF = new Mensajes();
        encargado = new Encargado();
        listaUsuarios = encargadoDAO.getUsuarios();

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

    public List<Encargado> getListaRolesTRUE() {
        return listaRolesTRUE;
    }

    public void setListaRolesTRUE(List<Encargado> listaRolesTRUE) {
        this.listaRolesTRUE = listaRolesTRUE;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Encargado> getListaLaboratoriosXencargado() {
        return listaLaboratoriosXencargado;
    }

    public void setListaLaboratoriosXencargado(List<Encargado> listaLaboratoriosXencargado) {
        this.listaLaboratoriosXencargado = listaLaboratoriosXencargado;
    }

    public List<Encargado> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Encargado> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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
                listaRoles = encargadoDAO.getListRoles();
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Rol Editado Correctamante"));

                PrimeFaces.current().executeScript("PF('centroEditarRolDialog').hide()");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));
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
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Nombre Rol"));
            } else if ("".equals(encargado.getDescripcion_rol())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Descripción"));
            } else if ("".equals(encargado.getEstado_rol())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Habilite Rol si Desea"));
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
            encargadoDAO.updateEncargado(encargado);
            listaEncargados = encargadoDAO.getEncargados();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Encargado Editado"));
            PrimeFaces.current().executeScript("PF('centroEditarEncargadoDialog').hide()");

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));
        }
        PrimeFaces.current().ajax().update(":form-principal:dtEncargados");
    }

    public void encargadoExistente() {
        System.out.println("Voy a verificar pilas");
        System.out.println(dni);
        encargadoDAO = new EncargadoDAO();
        encargado = new Encargado();
        encargado = encargadoDAO.verificarExisteEncargado(dni);
        listaLaboratoriosXencargado = encargadoDAO.getListaLaboratoriosXencargado(dni);
    }

    public void eliminarAsignacionLaboratorio() {
        try {
            this.encargadoDAO.deleteAsignacionLaboratorio(encargado.getIdEncargadoLaboratorio());
            listaEncargados = encargadoDAO.getEncargados();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Asignacion de Laboratorio Eliminada"));
            PrimeFaces.current().executeScript("PF('dtEncargados').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al eliminar"));
        }
    }

    public void registrarEncargadoLaboratorio() throws Exception {

        try {

            if (encargado.getFecha_inicio_registro() == null) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Fecha Inicio");
            } else if (encargado.getFecha_fin_registro() == null) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Fecha Fin");
            } else if ("".equals(encargado.getIdLaboratorio())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese Laboratorio");
            } else if ("".equals(encargado.getIdEncargado())) {
                mensajesJSF.mensajeDeAdvertencia("Ingresar identificacion Encargado");
            } else if (encargado.getEstado_rel()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Habilite Estado"));
            } else {
                this.encargadoDAO.insertEncargadolaboratorio(encargado);
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Registro Exitoso"));

                PrimeFaces.current().executeScript("PF('dtEncargados').hide()");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Error al guardar"));
        }
    }

    public void editarUsuarios() {

        try {
            encargadoDAO.updateusuario(encargado);
            listaUsuarios = encargadoDAO.getUsuarios();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Usuario Editado"));
            PrimeFaces.current().executeScript("PF('centroEditarUsuarioDialog').hide()");

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al guardar"));
        }
        PrimeFaces.current().ajax().update(":form-prin-usuario:dtUsuario");
    }

    public void registrarUsuario() throws Exception {
        try {
            if ("".equals(encargado.getNombre_persona())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Nombre"));
            } else if ("".equals(encargado.getApellido_persona())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Apellidos"));
            } else if ("".equals(encargado.getDni_persona())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese DNI"));
            } else if (encargado.getFechanacimiento_persona() == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Fecha de Nacimiento"));
            } else if ("".equals(encargado.getGenero_persona())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Elija Genero"));
            } else if ("".equals(encargado.getCorreo_persona())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Correo Electronico"));
            } else if ("".equals(encargado.getCelular_persona())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese numero de Celular"));
            } else if ("".equals(encargado.getNombre_usuario())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese nombre usuario"));
            } else if ("".equals(encargado.getPassword_usuario())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Contraseña"));
            } else if ("".equals(encargado.getFechacreacion_usuario())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese Contraseña"));
            } else if ("".equals(encargado.getEstado_usuario())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Su estado de Usuario esta Desactivado"));
                } else if ("".equals(encargado.getIdRoles())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Elija un Rol"));
                
            } else {
                this.encargadoDAO.insertUsuario(encargado);
                mensajesJSF.mensajeDeExito("Registro Exitoso");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
