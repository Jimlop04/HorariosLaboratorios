/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.login;

import DAO.login.LoginDAO;
import DAO.login.RolDAO;
import Model.administracion.Usuario;
import Model.login.UsuarioRol;
import Model.login.UsuarioSession;
import global.Mensajes;
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

;

@ManagedBean
@ViewScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginMB extends Mensajes {
    public UsuarioRol usuarioRol;
    public RolDAO rolDAO;
    public RolMB rolMB;
    public boolean band;
    private LoginDAO loginDAO;
    private Usuario usuario;
    private final FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();

    public HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);

    //Controla que en caso de que no haya un usuario conectado entonces redirija al incio.
    @PostConstruct
    public void init() {
        try {
            idPersona = 0;
            rolMB = new RolMB();
            band = false;
            usuarioSesion = new UsuarioSession();
            rolDAO = new RolDAO();
            usuarioRol = new UsuarioRol();
            loginDAO = new LoginDAO();
            this.usuario = new Usuario();
        } catch (Exception e) {

        }
    }

    UsuarioSession usuarioSesion;

    public void iniciarSesion() throws Exception {

        if ("".equals(this.usuario.getNombreUsuario())) {
            mensajeDeAdvertencia("Ingrese un usuario");
        }
        if ("".equals(this.usuario.getPassword())) {
            mensajeDeAdvertencia("Ingrese una contraseña");
        }
        if (!this.usuario.getNombreUsuario().isEmpty() && !this.usuario.getPassword().isEmpty()) {

            while (loginDAO.existeUsuario(usuario)) {
                if (loginDAO.masDeUnRol(usuario)) {
                    band = true;
                    rolMB.listarRolesNombre(usuario);
                    PrimeFaces.current().ajax().update("form:panelss");
                }

                if (usuarioSesion.getIdUsuarioRol() > 0) {
                    usuarioSesion = loginDAO.iniciarSesion(usuario);
                    FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("chiquito", usuarioSesion.getNombrePersona() +" - "+ usuarioSesion.getNombreRol());
                    facesContext.getExternalContext()
                            .redirect(ex.getRequestContextPath() + "/faces/View/Global/Principal.xhtml");

                } else {
                    mensajeDeAdvertencia("Seleccione un rol");
                    usuarioSesion = loginDAO.iniciarSesion(usuario);
                    System.out.println(usuarioSesion + "2 do");
                }
                break;
            }
            if(!loginDAO.existeUsuario(usuario)){
                mensajeDeAdvertencia("Usuario no existe o las credenciales son incorrectas");
            }

        }
    }

    public void verificarInicioSesion() {

    }

    int idPersona;

    public void llamaRol() throws Exception {
        try {
            idPersona = rolDAO.personaByUserName(this.usuario);
            System.out.println(rolDAO.personaByUserName(this.usuario));

        } catch (Exception e) {

        }
    }

    public void cerrarSession() throws IOException {
       httpSession.removeAttribute("chiquito");
       usuarioSesion = null;
        facesContext.getExternalContext()
                .redirect(ex.getRequestContextPath());

    }

}
