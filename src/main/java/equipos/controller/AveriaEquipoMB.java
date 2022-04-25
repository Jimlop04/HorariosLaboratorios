package equipos.controller;

import equipos.DAO.AveriaEquipoDAO;
import equipos.DAO.EquipoDAO;
import equipos.model.AveriaEquipo;
import equipos.model.CategoriaEquipo;
import equipos.model.Equipo;
import laboratorios.model.AreaAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
@SessionScoped
public class AveriaEquipoMB {

    private AveriaEquipoDAO dao;


    private AveriaEquipo AveriaEquipo;

    public void registrar()  {
        try {
            dao = new AveriaEquipoDAO();
            dao.resgistrar(AveriaEquipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
        }catch( Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" +e));
        }
    }

    @PostConstruct
    public void init() {
        this.AveriaEquipo = new AveriaEquipo();
    }
}
