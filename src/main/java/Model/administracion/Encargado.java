
package Model.administracion;

import java.util.Date;
/**
 *
 * @author Jimmy
 */
public class Encargado {
    private int idUsuario;
    private String nombre_usuario;
    private String password_usuario;
    private Date fechacreacion_usuario;
    private boolean estado_usuario;
   
    private int idPersona;
    private String nombre_persona;
    private String apellido_persona;
    private String dni_persona;
    private Date fechanacimiento_persona;
    private String genero_persona;
    private String correo_persona;
    private String celular_persona;
    
    private int idUsuaRoles;
    
    private int idRoles;
    private String nombre_rol;
    private String descripcion_rol;
    private boolean estado_rol;
    
    private int idEncargado;
    
    private int idEncargadoLaboratorio;
    private Date fecha_inicio;
    private Date fecha_fin;
     private boolean estado_EncargadoLaboratorio;
    
    private int idLaboratorio;
    private String nombre_laboratorio;
    private String codigo_laboratorio;
    
    private int  idencargadoLaboratorioDetalle;
    
    private Date fecha_inicio_registro;
    private Date fecha_fin_registro;
    private boolean estado_rel = true;


    public Encargado() {
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

    public Encargado(int idUsuario, 
            String nombre_usuario, 
            String password_usuario, 
            Date fechacreacion_usuario, 
            boolean estado_usuario, 
            int idPersona, 
            String nombre_persona, 
            String apellido_persona, 
            String dni_persona, 
            Date fechanacimiento_persona, 
            String genero_persona, 
            String correo_persona, 
            String celular_persona, 
            int idUsuaRoles, 
            int idRoles, 
            String nombre_rol, 
            int idEncargado, 
            int idEncargadoLaboratorio, 
            Date fecha_inicio, 
            Date fecha_fin, 
            boolean estado_EncargadoLaboratorio,
            int idLaboratorio, 
            String nombre_laboratorio, 
            String codigo_laboratorio) {
        this.idUsuario = idUsuario;
        this.nombre_usuario = nombre_usuario;
        this.password_usuario = password_usuario;
        this.fechacreacion_usuario = fechacreacion_usuario;
        this.estado_usuario = estado_usuario;
        this.idPersona = idPersona;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.dni_persona = dni_persona;
        this.fechanacimiento_persona = fechanacimiento_persona;
        this.genero_persona = genero_persona;
        this.correo_persona = correo_persona;
        this.celular_persona = celular_persona;
        this.idUsuaRoles = idUsuaRoles;
        this.idRoles = idRoles;
        this.nombre_rol = nombre_rol;
        this.idEncargado = idEncargado;
        this.idEncargadoLaboratorio = idEncargadoLaboratorio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado_EncargadoLaboratorio = estado_EncargadoLaboratorio;
        this.idLaboratorio = idLaboratorio;
        this.nombre_laboratorio = nombre_laboratorio;
        this.codigo_laboratorio = codigo_laboratorio;
    }

    public Encargado(int idRoles, String nombre_rol, String descripcion_rol, boolean estado_rol) {
        this.idRoles = idRoles;
        this.nombre_rol = nombre_rol;
        this.descripcion_rol = descripcion_rol;
        this.estado_rol = estado_rol;
    }

    public Encargado(int idRoles, String nombre_rol) {
        this.idRoles = idRoles;
        this.nombre_rol = nombre_rol;
    }

    public Encargado(int idPersona, String nombre_persona, String apellido_persona) {
        this.idPersona = idPersona;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
    }

    
    
    public Encargado(int idUsuario, String nombre_usuario, 
            String password_usuario, Date fechacreacion_usuario, 
            boolean estado_usuario, int idPersona, String nombre_persona, 
            String apellido_persona, String dni_persona, 
            Date fechanacimiento_persona, String genero_persona, 
            String correo_persona, String celular_persona, int idUsuaRoles, 
            int idRoles, String nombre_rol, boolean estado_rol) {
        this.idUsuario = idUsuario;
        this.nombre_usuario = nombre_usuario;
        this.password_usuario = password_usuario;
        this.fechacreacion_usuario = fechacreacion_usuario;
        this.estado_usuario = estado_usuario;
        this.idPersona = idPersona;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.dni_persona = dni_persona;
        this.fechanacimiento_persona = fechanacimiento_persona;
        this.genero_persona = genero_persona;
        this.correo_persona = correo_persona;
        this.celular_persona = celular_persona;
        this.idUsuaRoles = idUsuaRoles;
        this.idRoles = idRoles;
        this.nombre_rol = nombre_rol;
        this.estado_rol = estado_rol;
    }
    
    
    
    
    public int getIdencargadoLaboratorioDetalle() {
        return idencargadoLaboratorioDetalle;
    }

    public void setIdencargadoLaboratorioDetalle(int idencargadoLaboratorioDetalle) {
        this.idencargadoLaboratorioDetalle = idencargadoLaboratorioDetalle;
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

    
    public String getCodigo_laboratorio() {
        return codigo_laboratorio;
    }

    public void setCodigo_laboratorio(String codigo_laboratorio) {
        this.codigo_laboratorio = codigo_laboratorio;
    }

    public String getDni_persona() {
        return dni_persona;
    }

    public void setDni_persona(String dni_persona) {
        this.dni_persona = dni_persona;
    }

    public Date getFechanacimiento_persona() {
        return fechanacimiento_persona;
    }

    public void setFechanacimiento_persona(Date fechanacimiento_persona) {
        this.fechanacimiento_persona = fechanacimiento_persona;
    }

    public String getGenero_persona() {
        return genero_persona;
    }

    public void setGenero_persona(String genero_persona) {
        this.genero_persona = genero_persona;
    }

    public String getCorreo_persona() {
        return correo_persona;
    }

    public void setCorreo_persona(String correo_persona) {
        this.correo_persona = correo_persona;
    }

    public String getCelular_persona() {
        return celular_persona;
    }

    public void setCelular_persona(String celular_persona) {
        this.celular_persona = celular_persona;
    }

    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword_usuario() {
        return password_usuario;
    }

    public void setPassword_usuario(String password_usuario) {
        this.password_usuario = password_usuario;
    }

    public Date getFechacreacion_usuario() {
        return fechacreacion_usuario;
    }

    public void setFechacreacion_usuario(Date fechacreacion_usuario) {
        this.fechacreacion_usuario = fechacreacion_usuario;
    }

    public boolean getEstado_usuario() {
        return estado_usuario;
    }

    public void setEstado_usuario(boolean estado_usuario) {
        this.estado_usuario = estado_usuario;
    }

    public int getIdUsuaRoles() {
        return idUsuaRoles;
    }

    public void setIdUsuaRoles(int idUsuaRoles) {
        this.idUsuaRoles = idUsuaRoles;
    }

    public String getDescripcion_rol() {
        return descripcion_rol;
    }

    public void setDescripcion_rol(String descripcion_rol) {
        this.descripcion_rol = descripcion_rol;
    }

    public boolean getEstado_rol() {
        return estado_rol;
    }

    public void setEstado_rol(boolean estado_rol) {
        this.estado_rol = estado_rol;
    }

    public boolean getEstado_EncargadoLaboratorio() {
        return estado_EncargadoLaboratorio;
    }

    public void setEstado_EncargadoLaboratorio(boolean estado_EncargadoLaboratorio) {
        this.estado_EncargadoLaboratorio = estado_EncargadoLaboratorio;
    }

    public Date getFecha_inicio_registro() {
        return fecha_inicio_registro;
    }

    public void setFecha_inicio_registro(Date fecha_inicio_registro) {
        this.fecha_inicio_registro = fecha_inicio_registro;
    }

    public Date getFecha_fin_registro() {
        return fecha_fin_registro;
    }

    public void setFecha_fin_registro(Date fecha_fin_registro) {
        this.fecha_fin_registro = fecha_fin_registro;
    }

    public boolean getEstado_rel() {
        return estado_rel;
    }

    public void setEstado_rel(boolean estado_rel) {
        this.estado_rel = estado_rel;
    }
    
    
    

    
   
    }

   