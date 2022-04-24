package Administracion.controller;

import Administracion.dao.ProfesorDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import Administracion.model.Profesor;
import global.Mensajes;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ebert
 */
@ManagedBean
@ViewScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorMB implements Serializable {
    
    List<Profesor> listarProfesor;
    Profesor profesor;
    ProfesorDAO profesorDAO;
    Mensajes msj = new Mensajes();
    
    @PostConstruct
    public void init() {
        try {
            this.listarProfesor = new ArrayList<>();
            this.profesor = new Profesor();
            listarProfesores();
        } catch (Exception ex) {
            Logger.getLogger(ProfesorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void listarProfesores() throws Exception {
        try {
            profesorDAO = new ProfesorDAO();
            listarProfesor = profesorDAO.listarProfesor();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void nuevoProfesor() {
        this.profesor = new Profesor();
    }
    
    public void guardar() throws Exception {
        if (profesor.getIdPersona() == 0) {
            
            msj.mensajeDeExito("Nueva persona");
        } else {
            msj.mensajeDeAdvertencia("Editar persona");
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
