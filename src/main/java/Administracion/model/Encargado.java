
package Administracion.model;

import java.util.Date;
/**
 *
 * @author Jimmy
 */
public class Encargado {
    private int idEncargado;
    private String nombre_encargado;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean activo;
    private int idRoles;
    private String nombre_rol;

    public Encargado() {
    }
  
    public Encargado(int idEncargado, String nombre_encargado, Date fecha_inicio, Date fecha_fin, boolean activo, int idRoles, String nombre_rol) {
        this.idEncargado = idEncargado;
        this.nombre_encargado = nombre_encargado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activo = activo;
        this.idRoles = idRoles;
        this.nombre_rol = nombre_rol;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


  
    
    
    
    
    
    
    
    
    
    
    
    

}
