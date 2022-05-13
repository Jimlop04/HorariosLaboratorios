package Controller.equipos;

import Model.administracion.Encargado;
import DAO.equipos.GeneralDAO;
import Model.equipos.CategoriaEquipo;
import Model.laboratorios.AreaAula;
import Model.laboratorios.Laboratorio;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import lombok.Data;
import lombok.NoArgsConstructor;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class GeneralMB implements Serializable {

    GeneralDAO generalDao;
    Encargado encargado;
    List<Encargado> listaEncargados;
    List<Laboratorio> listaLaboratoriosEncargado;
    List<CategoriaEquipo> listaCategoriaEquipos;
    List<AreaAula> listAreasLaboratorio;
    int idLaboratorio = 0;

    public void listarEncargados() throws Exception {
        try {
            generalDao = new GeneralDAO();
            listaEncargados = generalDao.listarEncargados();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarCategoriaEquipos() throws Exception {
        try {
            generalDao = new GeneralDAO();
            listaCategoriaEquipos = generalDao.listarCategoriasEquipos();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarLaboratoriosByEncargadoId(int idPersona) throws Exception {
        try {
            generalDao = new GeneralDAO();
            listaLaboratoriosEncargado = generalDao.listarLaboratoriosByEncargadoId(idPersona);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarAreaByLaboratorioId() throws Exception {
        if(idLaboratorio==0){
            listAreasLaboratorio = null;
        }
        try {
            generalDao = new GeneralDAO();
            listAreasLaboratorio = generalDao.listarAreasByLaboratorioId(idLaboratorio);
        } catch (Exception e) {
            throw e;
        }
    }

}
