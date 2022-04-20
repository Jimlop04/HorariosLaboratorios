
package login.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ebert
 */
public class Profesor implements Serializable{

    int idProfesor;
    String nombre_profesor,
            apellido_profesor,
            cedula_profesor,
            genero_profesor,
            usuario_profesor,
            password_profesor;
    Date fnacimiento_profesor;
    boolean estado;

    //contructores
    public Profesor() {
    }

    public Profesor(String usuario_profesor, String password_profesor) {
        this.usuario_profesor = usuario_profesor;
        this.password_profesor = password_profesor;
    }

    public Profesor(String nombre_profesor, String apellido_profesor, String cedula_profesor, String genero_profesor, String usuario_profesor, String password_profesor, Date fnacimiento_profesor, boolean estado) {
        this.nombre_profesor = nombre_profesor;
        this.apellido_profesor = apellido_profesor;
        this.cedula_profesor = cedula_profesor;
        this.genero_profesor = genero_profesor;
        this.usuario_profesor = usuario_profesor;
        this.password_profesor = password_profesor;
        this.fnacimiento_profesor = fnacimiento_profesor;
        this.estado = estado;
    }

    /**
     * Métodos GET
     * @return idProfesor
     */
    public int getIdProfesor() {
        return idProfesor;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public String getApellido_profesor() {
        return apellido_profesor;
    }

    public String getCedula_profesor() {
        return cedula_profesor;
    }

    public String getGenero_profesor() {
        return genero_profesor;
    }

    public String getUsuario_profesor() {
        return usuario_profesor;
    }

    public String getPassword_profesor() {
        return password_profesor;
    }

    public Date getFnacimiento_profesor() {
        return fnacimiento_profesor;
    }

    public boolean isEstado() {
        return estado;
    }

    /**
     * Métodos SET
     *
     * @param idProfesor
     */
    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public void setApellido_profesor(String apellido_profesor) {
        this.apellido_profesor = apellido_profesor;
    }

    public void setCedula_profesor(String cedula_profesor) {
        this.cedula_profesor = cedula_profesor;
    }

    public void setGenero_profesor(String genero_profesor) {
        this.genero_profesor = genero_profesor;
    }

    public void setUsuario_profesor(String usuario_profesor) {
        this.usuario_profesor = usuario_profesor;
    }

    public void setPassword_profesor(String password_profesor) {
        this.password_profesor = password_profesor;
    }

    public void setFnacimiento_profesor(Date fnacimiento_profesor) {
        this.fnacimiento_profesor = fnacimiento_profesor;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
