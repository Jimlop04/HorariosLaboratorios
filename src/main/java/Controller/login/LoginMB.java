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
import org.jfree.chart.axis.SymbolAxis;
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

    //Variables para controlar acceso a los modulos correspondientes al rol
    private boolean isDecano=false;
    private boolean isDocente=false;
    private boolean isTecnicoRumiologia=false;
    private boolean isTecnicoQuimicaBioquimica =false;
    private boolean isTecnicoBiotecniologia=false;
    private boolean isTecnicoBromatologia=false;
    private boolean isTecnicoSuelosAguas=false;
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
                    redireccioarMenuRol(usuarioSesion);
                    System.out.println(usuarioSesion);
                     FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("idPersona", usuarioSesion.getIdPersona());
                     FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("idRol", usuarioSesion.getIdRol());
                     FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("idUsuario", usuarioSesion.getIdUsuario());
                     FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("idUsuarioRol", usuarioSesion.getIdUsuarioRol());
                     FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("nombrePersona", usuarioSesion.getNombrePersona());
                     FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("nombreRol", usuarioSesion.getNombreRol());
                  
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

    public void redireccioarMenuRol ( UsuarioSession usuarioSesion){
        String rol = usuarioSesion.getNombreRol();
        switch(rol.trim())
        {
            case "Docente":
                isDocente=true;
            case "Decano":
                isDecano=true;
            case "Técnico-Rumiología":
                isTecnicoRumiologia=true;
            case "Técnico-Quimica-Biologia":
                isTecnicoQuimicaBioquimica =true;
            case "Técnico-Bromatología":
                isTecnicoBromatologia=true;
            case "Técnico-Biotecnología":
                isTecnicoBiotecniologia=true;
            case "Técnico-Suelos-Aguas":
                isTecnicoSuelosAguas=true;
           //default;
        }
        System.out.println(isDocente);
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
