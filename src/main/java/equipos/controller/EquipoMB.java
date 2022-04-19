package equipos.controller;

import equipos.DAO.EquipoDAO;
import equipos.model.Equipo;
import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@RequestScoped
@Data
public class EquipoMB {

    private Equipo equipo = new Equipo();
    private List<Equipo> listaEquipos;

    public void registrar() throws SQLException {
        EquipoDAO dao;
        try {
            dao = new EquipoDAO();
            dao.resgistrar(equipo);
        }catch( Exception e){
            throw e;
        }
    }

//    LISTAR
    public void listar() throws SQLException {
        EquipoDAO dao;
        try {
            dao = new EquipoDAO();
            listaEquipos = dao.listar();
        }catch( Exception e){
            throw e;
        }
    }
}
