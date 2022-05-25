package Controller.equipos;

import Controller.login.LoginMB;
import DAO.equipos.EquipoDAO;
import Model.administracion.Usuario;
import Model.equipos.CategoriaEquipo;
import Model.equipos.Equipo;
import Model.laboratorios.AreaAula;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class EquipoMB implements Serializable {

    private List<Equipo> listaEquipos;
    private List<Equipo> listaEquiposFiltro = new ArrayList<>();
    private Equipo equipo;
    
    private String accion;

    private AreaAula areaAula;
    private CategoriaEquipo categoriaEquipo;
    String msj = "";

    int idLaboratorio = 0;

    private EquipoDAO dao;

    public void registrar() {

        try {
            dao = new EquipoDAO();
            dao.resgistrar(equipo);
            equipo = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" + e));
        }

    }

    public void listarEquiposByLaboratorioId() throws Exception {
        try {
            dao = new EquipoDAO();
            listaEquipos = dao.listarEquipos(idLaboratorio);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarEquiposByEncargado(int idPersona) throws Exception {
        try {
            dao = new EquipoDAO();
            listaEquipos = dao.listarEquiposByEncargado(idPersona);
        } catch (Exception e) {
            throw e;
        }
    }

    public void equipoByID(Equipo equipo) throws Exception {
        Equipo temp;
        try {
            dao = new EquipoDAO();
            temp= dao.equipoByID(equipo);
            
            if(temp != null){
                this.equipo=temp;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
        public void modificarEquipo() throws Exception {
        try {
            dao = new EquipoDAO();
            dao.equipoByID(equipo);

        } catch (Exception e) {
            throw e;
        }
    }
    
    

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
        this.areaAula = new AreaAula();
        this.categoriaEquipo = new CategoriaEquipo();
        this.listaEquipos = new ArrayList<>();

    }

}
