package equipos.controller;

import equipos.DAO.EquipoDAO;
import equipos.model.Equipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@RequestScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoMB implements Serializable {

    private Equipo equipo = new Equipo();
    private List<Equipo> listaEquipos;

    public void registrar() throws SQLException {
        EquipoDAO dao;
        try {
            dao = new EquipoDAO();
//            dao.resgistrar(equipo);
        }catch( Exception e){
            throw e;
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
}
