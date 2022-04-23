package Administracion.controller;

import Administracion.dao.ProfesorDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import Administracion.model.Profesor;
import java.sql.SQLException;
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
}
