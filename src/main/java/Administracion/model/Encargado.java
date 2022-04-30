
package Administracion.model;

import java.util.Date;
/**
 *
 * @author Jimmy
 */
public class Encargado {
    private int idPersona;
    private String nombre_persona;
    private String apellido_persona;
    
    private int idLaboratorio;
    private String nombre_laboratorio;
    private String codigo_laboratorio;
    
    private int idRoles;
    private String nombre_rol;
    
    private int idEncargadoLaboratorio;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Boolean estado;

    private int  idencargadoLaboratorioDetalle;


    public Encargado() {
    }

    public Encargado(String nombre_persona, 
            String apellido_persona,
            String nombre_laboratorio,
            String nombre_rol, 
            Date fecha_inicio, 
            Date fecha_fin, 
            Boolean estado) {
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.nombre_laboratorio = nombre_laboratorio;
        this.nombre_rol = nombre_rol;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.idEncargadoLaboratorio = idencargadoLaboratorioDetalle;
    }

    public Encargado(int idPersona, 
            String nombre_persona, 
            String apellido_persona, 
            int idLaboratorio, 
            String nombre_laboratorio, 
            String codigo_laboratorio) {
        this.idPersona = idPersona;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.idLaboratorio = idLaboratorio;
        this.nombre_laboratorio = nombre_laboratorio;
        this.codigo_laboratorio = codigo_laboratorio;
    }
    
    

    public int getIdencargadoLaboratorioDetalle() {
        return idencargadoLaboratorioDetalle;
    }

    public void setIdencargadoLaboratorioDetalle(int idencargadoLaboratorioDetalle) {
        this.idencargadoLaboratorioDetalle = idencargadoLaboratorioDetalle;
    }


    public Encargado(int idRoles, String nombre_rol) {
        this.idRoles = idRoles;
        this.nombre_rol = nombre_rol;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellido_persona() {
        return apellido_persona;
    }

    public void setApellido_persona(String apellido_persona) {
        this.apellido_persona = apellido_persona;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre_laboratorio() {
        return nombre_laboratorio;
    }

    public void setNombre_laboratorio(String nombre_laboratorio) {
        this.nombre_laboratorio = nombre_laboratorio;
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

    public int getIdEncargadoLaboratorio() {
        return idEncargadoLaboratorio;
    }

    public void setIdEncargadoLaboratorio(int idEncargadoLaboratorio) {
        this.idEncargadoLaboratorio = idEncargadoLaboratorio;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getCodigo_laboratorio() {
        return codigo_laboratorio;
    }

    public void setCodigo_laboratorio(String codigo_laboratorio) {
        this.codigo_laboratorio = codigo_laboratorio;
    }
    
    

    
   
    }

   