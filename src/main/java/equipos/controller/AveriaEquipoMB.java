package equipos.controller;

import equipos.DAO.AveriaEquipoDAO;
import equipos.model.AveriaEquipo;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


import java.io.Serializable;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class AveriaEquipoMB implements Serializable {

    private AveriaEquipoDAO dao;


    private AveriaEquipo averiaEquipo;

    public void registrar() throws Exception {
        try {
            dao = new AveriaEquipoDAO();
            System.out.println(averiaEquipo);
            dao.resgistrar(averiaEquipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
        } catch (Exception e) {
            System.out.println(averiaEquipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" + e));
        }
    }

    @PostConstruct
    public void init() {
        this.averiaEquipo = new AveriaEquipo();
    }
}
