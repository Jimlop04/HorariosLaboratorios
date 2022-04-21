
package Administracion.model;

import java.util.Date;
/**
 *
 * @author Jimmy
 */
public class Encargado {
    private int idEncargado;
    private String nombre_encargado;
    private String apellidos_encargado;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean estado;
    private int idRoles;
    private String nombre_rol;
    private int er_idRol;
    private int er_idEncargado;

    public Encargado() {
    }

    public Encargado(
            int idEncargado, 
            String nombre_encargado, 
            String apellidos_encargado, 
            Date fecha_inicio, Date fecha_fin, 
            boolean estado, 
            int idRoles, 
            String nombre_rol) {
        this.idEncargado = idEncargado;
        this.nombre_encargado = nombre_encargado;
        this.apellidos_encargado = apellidos_encargado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.idRoles = idRoles;
        this.nombre_rol = nombre_rol;
    }

    public Encargado(int er_idRol, int er_idEncargado) {
        this.er_idRol = er_idRol;
        this.er_idEncargado = er_idEncargado;
    }
    
    
    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }
    
  
    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getNombre_encargado() {
        return nombre_encargado;
    }

    public void setNombre_encargado(String nombre_encargado) {
        this.nombre_encargado = nombre_encargado;
    }

    public String getApellidos_encargado() {
        return apellidos_encargado;
    }

    public void setApellidos_encargado(String apellidos_encargado) {
        this.apellidos_encargado = apellidos_encargado;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getEr_idRol() {
        return er_idRol;
    }

    public void setEr_idRol(int er_idRol) {
        this.er_idRol = er_idRol;
    }

    public int getEr_idEncargado() {
        return er_idEncargado;
    }

    public void setEr_idEncargado(int er_idEncargado) {
        this.er_idEncargado = er_idEncargado;
    }

   


  
    
    
    
    
    
    
    
    
    
    
    
    

}
