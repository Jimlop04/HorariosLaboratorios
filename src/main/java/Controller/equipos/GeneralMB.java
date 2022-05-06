package Controller.equipos;

import Model.administracion.Encargado;
import DAO.equipos.GeneralDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class GeneralMB implements Serializable {

    private GeneralDAO generalDao;
    private Encargado encargado;
    private List<Encargado> listaEncargados;

    public void listarEncargados() throws Exception {
        try {
            generalDao = new GeneralDAO();
            listaEncargados = generalDao.listarEncargados();
        } catch (Exception e) {
            throw e;
        }
    }

    @PostConstruct
    public void init() {
        this.listaEncargados = new ArrayList<>();
        this.encargado = new Encargado();
    }


}
