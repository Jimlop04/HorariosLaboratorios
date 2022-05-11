package Model.laboratorios;

/**
 *
 * @author Jimmy
 */
public class Laboratorio {
    private int idFacultad;
    private String nombre_facultad;
    private int idLaboratorio;
    private int facultad_idfacultad;
    private String nombre_laboratorio;
    private String codigo_laboratorio;
    private int capacidad;
    
 

    public Laboratorio() {
        
    }

    public Laboratorio(int idFacultad, 
            String nombre_facultad, 
            int idLaboratorio, 
            int facultad_idfacultad, 
            String nombre_laboratorio, 
            String codigo_laboratorio) {
        this.idFacultad = idFacultad;
        this.nombre_facultad = nombre_facultad;
        this.idLaboratorio = idLaboratorio;
        this.facultad_idfacultad = facultad_idfacultad;
        this.nombre_laboratorio = nombre_laboratorio;
        this.codigo_laboratorio = codigo_laboratorio;
    }

    public Laboratorio(int idLaboratorio, String nombre_laboratorio, String codigo_laboratorio) {
        this.idLaboratorio = idLaboratorio;
        this.nombre_laboratorio = nombre_laboratorio;
        this.codigo_laboratorio = codigo_laboratorio;
    }

    public Laboratorio(int idLaboratorio, String codigo_laboratorio,String nombre_laboratorio, int capacidad) {
        this.idLaboratorio = idLaboratorio;
        this.codigo_laboratorio = codigo_laboratorio;
        this.nombre_laboratorio = nombre_laboratorio;
        this.capacidad = capacidad;
    }
    
    
    public Laboratorio(int idFacultad, String nombre_facultad) {
        this.idFacultad = idFacultad;
        this.nombre_facultad = nombre_facultad;
    }
    
    
    
   

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNombre_facultad() {
        return nombre_facultad;
    }

    public void setNombre_facultad(String nombre_facultad) {
        this.nombre_facultad = nombre_facultad;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public int getFacultad_idfacultad() {
        return facultad_idfacultad;
    }

    public void setFacultad_idfacultad(int facultad_idfacultad) {
        this.facultad_idfacultad = facultad_idfacultad;
    }

    public String getNombre_laboratorio() {
        return nombre_laboratorio;
    }

    public void setNombre_laboratorio(String nombre_laboratorio) {
        this.nombre_laboratorio = nombre_laboratorio;
    }

    public String getCodigo_laboratorio() {
        return codigo_laboratorio;
    }

    public void setCodigo_laboratorio(String codigo_laboratorio) {
        this.codigo_laboratorio = codigo_laboratorio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
    @Override
    public String toString() {
        return "Laboratorio{" + "idLaboratorio=" + idLaboratorio + ", "
                + "nombre_laboratorio=" + nombre_laboratorio + ", "
                + "codigo_laboratorio=" + codigo_laboratorio + '}';
    }
    
}
