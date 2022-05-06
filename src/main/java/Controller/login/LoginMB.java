/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.login;

import Model.administracion.Usuario;
import Model.login.UsuarioRol;
import Model.login.UsuarioSession;
import global.Mensajes;
import DAO.login.LoginDAO;
;
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

@ManagedBean
@ViewScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginMB extends Mensajes {
    public UsuarioRol usuarioRol;
    public boolean band;
    private LoginDAO loginDAO;
    private Usuario usuario;
    private final FacesContext facesContext = FacesContext.getCurrentInstance();
    public HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);

    //Controla que en caso de que no haya un usuario conectado entonces redirija al incio.
    @PostConstruct
    public void init() {
        try {
            band = false;
            usuarioRol = new UsuarioRol();
            loginDAO = new LoginDAO();
        } catch (Exception e) {

        }
    }

    public void iniciarSesion() throws Exception {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        UsuarioSession usuarioSesion = new UsuarioSession();
        if ("".equals(this.usuario.getNombreUsuario())) {
            mensajeDeAdvertencia("Ingrese un usuario");
        }
        if ("".equals(this.usuario.getPassword())) {
            mensajeDeAdvertencia("Ingrese una contraseña");
        }
        if (!this.usuario.getNombreUsuario().isEmpty() && !this.usuario.getPassword().isEmpty()) {
            if ("".equals(this.usuario.getIdUsuarioRol())) {
                mensajeDeAdvertencia("Ingrese un rol");

            } else {
                usuarioSesion = loginDAO.iniciarSesion(usuario.getIdUsuarioRol());
                if (usuarioSesion != null) {
                    if (usuarioSesion.getIdPersona() < 1) {
                        mensajeDeAdvertencia(usuarioSesion.getNombrePersona());

                    } else {
                        //Registrar usuario en HttpSession
                        httpSession.setAttribute("username", usuarioSesion);

                        //Registrar usuario en Session de JSF
                        FacesContext.getCurrentInstance().getExternalContext()
                                .getSessionMap().put("usuario", usuarioSesion);

                        if (loginDAO.masRol(usuarioSesion.getIdPersona())) {

                            FacesContext.getCurrentInstance().getExternalContext()
                                    .getSessionMap().put("chiquito", usuarioSesion.getIdPersona());
                            //facesContext.getExternalContext().redirect(ex.getRequestContextPath()+"/faces/View/Global/AsignacionRol.xhtml");
                            RolMB rm = new RolMB();
                            rm.listarRolesID(usuarioSesion.getIdPersona());
                            rm.listarRolSession(usuarioSesion.getIdUsuarioRol());
                            band = true;
                            PrimeFaces.current().ajax().update("form:panelss");
                            if (usuarioRol.getIdUsuarioRol() == 0) {
                                mensajeDeAdvertencia("Seleccione un rol:");

                                PrimeFaces.current().ajax().update("form:panelss");


                            } else {


                                facesContext.getExternalContext()
                                        .redirect(ex.getRequestContextPath() + "/faces/View/Global/Principal.xhtml");

                            }

                        } else {
                            facesContext.getExternalContext()
                                    .redirect(ex.getRequestContextPath() + "/faces/View/Global/Principal.xhtml");


                        }

                    }
                } else {
                    mensajeDeAdvertencia("Error de conexión en tus tapas al intentar iniciar sesión.");
                }
            }


        }
    }

    public void verificarInicioSesion() {

    }

    public void cerrarSession() throws IOException {

    }

}
