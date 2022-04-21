package equipos.controller;

import equipos.DAO.CategoriaEquipoDAO;
import equipos.DAO.EquipoDAO;
import equipos.model.CategoriaEquipo;
import equipos.model.Equipo;
import laboratorios.model.AreaAula;
import laboratorios.model.Laboratorio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEquipoMB implements Serializable {
    private CategoriaEquipo categoriaEquipo = new CategoriaEquipo();
    private List<CategoriaEquipo> listaCategoriaEquipos;

    private List<Laboratorio> listaLaboratorios;

    private List<AreaAula> listaAreasAulas;

    public void registrar() throws SQLException {
        CategoriaEquipoDAO dao;
        try {
            dao = new CategoriaEquipoDAO();
//            dao.resgistrar(equipo);
        }catch( Exception e){
            throw e;
        }
    }


    //        LISTAR
    public void listar() throws Exception {
        CategoriaEquipoDAO dao;
        try {
            dao = new CategoriaEquipoDAO();
            listaCategoriaEquipos = dao.listar();
        }catch( Exception e){
            throw e;
        }
    }

    public void listarLaboratorios() throws Exception {
        EquipoDAO dao;
        try {
            dao = new EquipoDAO();
            listaLaboratorios = dao.listarLaboratorios();
        }catch( Exception e){
            throw e;
        }
    }

    public void listarAreasAulas(int idLaboratorio) throws Exception {
        EquipoDAO dao;
        try {
            dao = new EquipoDAO();
            listaAreasAulas = dao.listarAreasAulas(idLaboratorio);
        }catch( Exception e){
            throw e;
        }
    }
}
