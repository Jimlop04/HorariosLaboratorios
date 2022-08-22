package Controller.administracion;

import DAO.administracion.ProfesorDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import Model.administracion.Profesor;
import global.Mensajes;
import java.sql.SQLException;
import Controller.login.LoginMB;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ebert
 */
@Getter
@Setter
@ManagedBean
@javax.faces.view.ViewScoped
public class ProfesorMB extends Mensajes implements Serializable {

    static final String NUEVO = "Nuevo";
    LoginMB prueba = new LoginMB();
    static final String EDITAR = "Editar";
    List<Profesor> listarProfesor;
    Profesor profesor;
    String profesorModo;
    ProfesorDAO profesorDAO;

    @PostConstruct
    public void init() {
         System.out.println("1");
        try {
            int var = (Integer) prueba.httpSession.getAttribute("chiquito");            
        } catch (Exception ex) {
          
        }
         System.out.println("2");
        profesorDAO = new ProfesorDAO();
        profesor = new Profesor();
         System.out.println("3");
        listarProfesores();

    }

    public void listarProfesores() {
        try {
            listarProfesor = new ArrayList<>();
            listarProfesor = profesorDAO.listarProfesor();
        
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void nuevoProfesor() {
        this.profesorModo = NUEVO;
        this.profesor = new Profesor();
        PrimeFaces.current().ajax().update("form:dialogo_profesor");

    }

    public void editarProfesor(Profesor pro) {
        this.setProfesor(pro);
        this.profesorModo = EDITAR;
        PrimeFaces.current().ajax().update(":form:dialogo_profesor");
    }

    public boolean es_editar() {
        return this.profesorModo.equals(EDITAR);
    }

    public void guardar() throws Exception {
        try {
            if (this.profesorModo.equals(NUEVO)) {
                profesorDAO.insertarProfesor(profesor);
                mensajeDeExito("Éxito: Docente agregado");

            }
            if (this.profesorModo.equals(EDITAR)) {
                profesorDAO.editarProfesor(this.profesor.getIdPersona(), this.profesor.getIdUsuario(), this.profesor);
                mensajeDeExito("Éxito: Docente editado");
            }
            PrimeFaces.current().executeScript("PF('detalleProfesor').hide()");
            PrimeFaces.current().ajax().update(":form:dtProfesor");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean validarInfo() throws SQLException {
        Profesor oldProfesor = profesorDAO.getProfesorById(profesor.getIdPersona());
        if (oldProfesor.getNombre_persona().equals(profesor.getNombre_persona())
                && oldProfesor.getApellido_persona().equals(profesor.getApellido_persona())
                && oldProfesor.getDni_persona().equals(profesor.getDni_persona())) {
            return false;
        } else {
            return true;
        }
    }
    
}
