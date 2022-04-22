package equipos.controller;

import equipos.DAO.EquipoDAO;
import equipos.model.CategoriaEquipo;
import equipos.model.Equipo;
import laboratorios.model.AreaAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.primefaces.model.file.UploadedFile;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoMB implements Serializable {

    private UploadedFile file;
    private List<Equipo> listaEquipos;
    private Equipo equipo = new Equipo();
    private AreaAula areaAul = new AreaAula();
    private CategoriaEquipo categoriaEquipo = new CategoriaEquipo();



    public void registrar() throws Exception {
        EquipoDAO dao;
        try {
            dao = new EquipoDAO();
            dao.resgistrar(equipo);
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

//  @PostConstruct
//   public void init() {
//        this.areaAula= new AreaAula();
//        this.categoriaEquipo = new CategoriaEquipo();
//        this.equipo = new Equipo();
//   }
}
