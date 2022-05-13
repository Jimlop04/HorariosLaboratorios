package Controller.equipos;

import Controller.login.LoginMB;
import DAO.equipos.AveriaEquipoDAO;
import Model.equipos.AveriaEquipo;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import java.io.Serializable;
import lombok.Data;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class AveriaEquipoMB implements Serializable {

    private AveriaEquipoDAO dao;
    int idEncargadoLaboratorio=0;


    

    private AveriaEquipo averiaEquipo;

//    public void registrar() throws Exception {
//        try {
//            dao = new AveriaEquipoDAO();
//            System.out.println(averiaEquipo);
//            dao.resgistrar(averiaEquipo);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
//        } catch (Exception e) {
//            System.out.println(averiaEquipo);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" + e));
//        }
//    }
    
    
        public void registrar() {

        try {
            dao = new AveriaEquipoDAO();
            
            dao.resgistrar(averiaEquipo,  idEncargadoLaboratorio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" + e));
        }

    }

    @PostConstruct
    public void init() {
        this.averiaEquipo = new AveriaEquipo();
    }
    
    public void asignarIdEncargadoLaboratorio(int id){
        this.idEncargadoLaboratorio = id;
    }
}
