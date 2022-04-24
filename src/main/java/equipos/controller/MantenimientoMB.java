package equipos.controller;

import Administracion.model.Encargado;
import equipos.DAO.EquipoDAO;
import equipos.DAO.ManteimientoDAO;
import equipos.model.CategoriaEquipo;
import equipos.model.DetalleMantenimiento;
import equipos.model.Equipo;
import equipos.model.Mantenimiento;
import laboratorios.model.AreaAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
@SessionScoped
public class MantenimientoMB implements Serializable {

    private Mantenimiento mantenimiento;

    ManteimientoDAO dao;
    List<DetalleMantenimiento> listaDetalle;
    List<Encargado> listaEncargadosLaboratorio;
    private int  idEquipo;

    public void agregar(){
    DetalleMantenimiento det = new DetalleMantenimiento();
        det.setIdEquipo(idEquipo);
        listaDetalle.add(det);
    }

    @PostConstruct
    public void init() {
        this.mantenimiento= new Mantenimiento();
        this.listaDetalle = new ArrayList<>();
        this.listaEncargadosLaboratorio = new ArrayList<>();
    }

    //        LISTAR
    public void listar() throws Exception {
        try {
            dao = new ManteimientoDAO();
            listaEncargadosLaboratorio = dao.listarEncargados();
        }catch( Exception e){
            throw e;
        }
    }

}