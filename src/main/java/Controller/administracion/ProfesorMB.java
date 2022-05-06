package Controller.administracion;

import DAO.administracion.ProfesorDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import Model.administracion.Profesor;
import global.Mensajes;
import java.sql.SQLException;
import javax.faces.bean.ViewScoped;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;

import Controller.login.LoginMB;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ebert
 */
@ManagedBean
@ViewScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorMB extends Mensajes implements Serializable {

    static final String NUEVO = "Nuevo";
    LoginMB prueba = new LoginMB();
    static final String EDITAR = "Editar";
    List<Profesor> listarProfesor;
    List<Profesor> profesores;
    Profesor profesor;
    String profesorModo;
    ProfesorDAO profesorDAO;

    @PostConstruct
    public void init() {
        try {
            int var = (Integer) prueba.httpSession.getAttribute("chiquito");
            listarProfesor = new ArrayList<>();
            profesor = new Profesor();
            listarProfesores();
        } catch (Exception ex) {
            Logger.getLogger(ProfesorMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Asynchronous
    public void listarProfesores() {
        try {
            profesorDAO = new ProfesorDAO();
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
        System.out.println(profesor+ "   SIN THIS");
        System.out.println(this.profesor+"  CON THIS");
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
