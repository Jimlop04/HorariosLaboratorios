package equipos.controller;

import equipos.DAO.EquipoDAO;
import equipos.model.CategoriaEquipo;
import equipos.model.Equipo;
import laboratorios.model.AreaAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.primefaces.model.file.UploadedFile;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoMB implements Serializable {

    private UploadedFile file;
    private List<Equipo> listaEquipos;
    private Equipo equipo = new Equipo();
    private AreaAula areaAul = new AreaAula();
    private CategoriaEquipo categoriaEquipo = new CategoriaEquipo();
    String msj = "";



    public void registrar()  {
        EquipoDAO dao;
        try {
            dao = new EquipoDAO();
            dao.resgistrar(equipo);
            this.msj="Registro guardado con éxito";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
        }catch( Exception e){
            this.msj="Se produjo un error: " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo"));
        }
    }


//        LISTAR
    public void listar() throws Exception {
        EquipoDAO dao;
        try {
            dao = new EquipoDAO();
            listaEquipos = dao.listar();
        }catch( Exception e){
            throw e;
        }
    }

//  @PostConstruct
//   public void init() {
//        this.areaAula= new AreaAula();
//        this.categoriaEquipo = new CategoriaEquipo();
//        this.equipo = new Equipo();
//   }
}
