
package Administracion.model;

import java.util.Date;
/**
 *
 * @author Jimmy
 */
public class Encargado {
    private int id_encargado;
    private String nombre_encargado;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean activo; 
    private int id_roles;
    private String nombre_rol;

    public Encargado() {
    }

    public Encargado(int id_encargado, String nombre_encargado, Date fecha_inicio,Date fecha_fin, boolean activo) {
        this.id_encargado = id_encargado;
        this.nombre_encargado = nombre_encargado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activo = activo;
      
    }

    public int getId_encargado() {
        return id_encargado;
    }

    public void setId_encargado(int id_encargado) {
        this.id_encargado = id_encargado;
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
