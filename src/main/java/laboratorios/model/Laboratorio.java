package laboratorios.model;

/**
 *
 * @author Jimmy
 */
public class Laboratorio {
    private int idLaboratorio;
    private int facultad_idfacultad;
    private String nombre_laboratorio;
    private String codigo_laboratorio;
    private int id_area_aula;
    private int laboratorio_idLaboratorio;
    private String codigo_aula;
    private String nombre_aula;
    private int capacidad_aula;

    public Laboratorio() {
    }

    public Laboratorio(int idLaboratorio, 
            int facultad_idfacultad, 
            String nombre_laboratorio, 
            String codigo_laboratorio, 
            int id_area_aula, 
            int laboratorio_idLaboratorio, 
            String codigo_aula, 
            String nombre_aula, 
            int capacidad_aula) {
        this.idLaboratorio = idLaboratorio;
        this.facultad_idfacultad = facultad_idfacultad;
        this.nombre_laboratorio = nombre_laboratorio;
        this.codigo_laboratorio = codigo_laboratorio;
        this.id_area_aula = id_area_aula;
        this.laboratorio_idLaboratorio = laboratorio_idLaboratorio;
        this.codigo_aula = codigo_aula;
        this.nombre_aula = nombre_aula;
        this.capacidad_aula = capacidad_aula;
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

    public int getId_area_aula() {
        return id_area_aula;
    }

    public void setId_area_aula(int id_area_aula) {
        this.id_area_aula = id_area_aula;
    }

    public int getLaboratorio_idLaboratorio() {
        return laboratorio_idLaboratorio;
    }

    public void setLaboratorio_idLaboratorio(int laboratorio_idLaboratorio) {
        this.laboratorio_idLaboratorio = laboratorio_idLaboratorio;
    }
    
    

    public String getCodigo_aula() {
        return codigo_aula;
    }

    public void setCodigo_aula(String codigo_aula) {
        this.codigo_aula = codigo_aula;
    }

    public String getNombre_aula() {
        return nombre_aula;
    }

    public void setNombre_aula(String nombre_aula) {
        this.nombre_aula = nombre_aula;
    }

    public int getCapacidad_aula() {
        return capacidad_aula;
    }

    public void setCapacidad_aula(int capacidad_aula) {
        this.capacidad_aula = capacidad_aula;
    }
    
    
    
    
    
    
    
    
    
    
    

}
