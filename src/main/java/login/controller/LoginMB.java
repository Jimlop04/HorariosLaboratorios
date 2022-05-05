/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.controller;

import global.Mensajes;
import login.dao.LoginDAO;
import login.model.Login;
import login.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean
@ViewScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginMB extends Mensajes {
    public Login usuario;
    public boolean band;
    public int idUsuarioRol;
    private LoginDAO usuarioDAO;
    private final FacesContext facesContext = FacesContext.getCurrentInstance();
    public HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);

    //Controla que en caso de que no haya un usuario conectado entonces redirija al incio.
    @PostConstruct
    public void init() {
        try {
            idUsuarioRol=0;
            band = false;
            usuario = new Login();
            usuarioDAO = new LoginDAO();
        } catch (Exception e) {

        }
    }

    public void iniciarSesion() throws Exception {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        Login usuarioSesion = new Login();
        if ("".equals(usuario.getNombre_usuario())) {
            mensajeDeAdvertencia("Ingrese un usuario");
        }
        if ("".equals(usuario.getPassword_usuario())) {
            mensajeDeAdvertencia("Ingrese una contraseña");
        }
        if (!usuario.getNombre_usuario().isEmpty() && !usuario.getPassword_usuario().isEmpty()) {
            usuarioSesion = usuarioDAO.iniciarSesion(usuario);
            System.out.println(usuarioSesion.getApellido_persona());
            if (usuarioSesion != null) {
                if (usuarioSesion.getCode() < 1) {
                    mensajeDeAdvertencia(usuarioSesion.getReslt());

                } else {
                    usuario = usuarioSesion;
                    //Registrar usuario en HttpSession
                    httpSession.setAttribute("username", usuarioSesion);
                    usuario = usuarioSesion;
                    //Registrar usuario en Session de JSF
                    FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("usuario", usuarioSesion);
                    System.out.println(band + "1");
                    if (usuarioDAO.masRol(usuario.getPersona_idPersona())) {

                        FacesContext.getCurrentInstance().getExternalContext()
                                .getSessionMap().put("chiquito", usuarioSesion.getPersona_idPersona());
                        //facesContext.getExternalContext().redirect(ex.getRequestContextPath()+"/faces/View/Global/AsignacionRol.xhtml");
                        RolMB rm = new RolMB();
                        rm.listarRolesID(usuario.getPersona_idPersona());
                        band = true;
                        System.out.println(band);
                        System.out.println(usuario.getPersona_idPersona());
                        PrimeFaces.current().ajax().update("form:panelss");
                        if (idUsuarioRol == 0 ) {
                            mensajeDeAdvertencia("Seleccione un rol: "+ idUsuarioRol);

                            PrimeFaces.current().ajax().update("form:panelss");


                        } else {


                            facesContext.getExternalContext()
                                    .redirect(ex.getRequestContextPath() + "/faces/View/Global/Principal.xhtml");
                            System.out.println(idUsuarioRol);
                        }

                    }else{
                        facesContext.getExternalContext()
                                .redirect(ex.getRequestContextPath() + "/faces/View/Global/Principal.xhtml");
                        System.out.println(idUsuarioRol);

                    }

                }
            } else {
                mensajeDeAdvertencia("Error de conexión al intentar iniciar sesión.");
            }
        }
    }

    public void msh() {
        PrimeFaces.current().ajax().update("form:panelss, :form:rolRender");

    }

    public void verificarInicioSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        Login usuarioSesion = (Login) context
                .getExternalContext().getSessionMap().get("usuario");
        try {
            if (usuarioSesion == null || usuarioSesion.getIdUsuario() < 1) {
                context.getExternalContext()
                        .redirect(facesContext.getExternalContext().getRequestContextPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean renderRoles(boolean b) {
        return usuarioDAO.masRol(10);
    }

    public void cerrarSession() throws IOException {
        System.out.println(httpSession.getAttribute(
                "usuario") + "Holas CESION");
        httpSession.removeAttribute("usuario");
        System.out.println(httpSession.getAttribute(
                "usuario") + "Holas CESION");
        facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath());
        usuario.setPassword_usuario("");
        usuario.setNombre_usuario("");
        System.out.println(httpSession.getAttribute(
                "usuario") + "Holas CESION");
    }

    //Verifica si la sesion tiene un usuario, devuelve un booleano
    public boolean verificarSesion() {
        Login user = new Login();
        try {
            user = (Login) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("usuario");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null && user.getIdUsuario() > 0) {
            return true;
        }
        return false;
    }

}
