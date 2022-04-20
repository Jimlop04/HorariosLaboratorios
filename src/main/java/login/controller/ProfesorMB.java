
package login.controller;

import global.Mensajes;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import login.dao.ProfesorDAO;
import login.model.Profesor;

/**
 *
 * @author ebert
 */
@Named(value = "profesorMB")
@SessionScoped
public class ProfesorMB implements Serializable {
    Profesor profesor;
    List<Profesor> profesores;
    Mensajes mensajesJSF;
    boolean status;
    ProfesorDAO profesorDAO;
    //Constructores
    public ProfesorMB() {
        profesor = new Profesor();
        profesores = new ArrayList<>();
        profesorDAO = new ProfesorDAO();
         this.status=true;
    }
    //Metodos GET

    public Mensajes getMensajesJSF() {
        return mensajesJSF;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public boolean isStatus() {
        return status;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public ProfesorDAO getProfesorDAO() {
        return profesorDAO;
    }
    //Metodos SET

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void setProfesorDAO(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }
    
    public void registrarProfesor() throws Exception{        
      
        try {
          
            if ("".equals(profesor.getNombre_profesor())) {
                mensajesJSF.mensajeDeAdvertencia("Ingrese un Nombre");

                //  PrimeFaces.current().ajax().update("form:messages");
            } else if ("".equals(profesor.getApellido_profesor())) {
                 mensajesJSF.mensajeDeAdvertencia("Ingrese un Apellido");

            } else if ("".equals(profesor.getCedula_profesor())) {
                 mensajesJSF.mensajeDeAdvertencia("Ingrese una cédula");

            } else if ("".equals(profesor.getGenero_profesor())) {
                  mensajesJSF.mensajeDeAdvertencia("Ingrese un género");
            } else if (status== false) {
                  mensajesJSF.mensajeDeAdvertencia("Aceptar los terminos y condiciones");

            }  else if (profesor.getFnacimiento_profesor()==null) {
                 mensajesJSF.mensajeDeAdvertencia("Ingrese una fecha de nacimiento");
            } else if ("".equals(profesor.getUsuario_profesor())) {
                 mensajesJSF.mensajeDeAdvertencia("Ingrese un usuario");
            } else if ("".equals(profesor.getPassword_profesor())) {
                 mensajesJSF.mensajeDeAdvertencia("Ingrese una contraseña");
            }
            else {
              this.profesorDAO.registrarProfesor(profesor);
                  mensajesJSF.mensajeDeAdvertencia(profesorDAO.registrarProfesor(profesor).toString());
            }
            System.out.println(profesor.getApellido_profesor());
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
