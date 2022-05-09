package Controller.equipos;

import DAO.equipos.EquipoDAO;
import Model.equipos.CategoriaEquipo;
import Model.equipos.Equipo;
import Model.laboratorios.Area;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class EquipoMB implements Serializable {

    private List<Equipo> listaEquipos;
    private Equipo equipo;
    private Area areaAula;
    private CategoriaEquipo categoriaEquipo;
    String msj = "";

    //    @Inject
    private EquipoDAO dao;


//    public void registrar() {
//        try {
//            dao = new EquipoDAO();
//            dao.resgistrar(equipo);
//            this.msj = "Registro guardado con éxito";
//            System.out.println(equipo);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
//        } catch (Exception e) {
//            this.msj = "Se produjo un error: " + e.getMessage();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo"));
//        }
//    }


    //        LISTAR
//    public void listar() throws Exception {
//        try {
//            dao = new EquipoDAO();
//            listaEquipos = dao.listar();
//        } catch (Exception e) {
//            throw e;
//        }
//    }

//    public void listarEquipos() throws Exception {
//        try {
//            dao = new EquipoDAO();
//            listaEquipos = dao.listarEquipos();
//        } catch (Exception e) {
//            throw e;
//        }
//    }


    @PostConstruct
    public void init() {
        this.equipo = new Equipo();
        this.listaEquipos = new ArrayList<>();
    }
}
