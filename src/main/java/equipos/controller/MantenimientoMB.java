package equipos.controller;

import Administracion.model.Encargado;
import equipos.DAO.MantenimientoDAO;
import equipos.model.DetalleMantenimiento;
import equipos.model.Equipo;
import equipos.model.Mantenimiento;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
@Getter
@Setter
public class MantenimientoMB implements Serializable {


    private Mantenimiento mantenimiento;
    private Encargado encargado;

    MantenimientoDAO dao;
    List<DetalleMantenimiento> listaDetalle;
    private List<String> listaa;
    List<Encargado> listaEncargadosLaboratorio;
    List<Equipo> listaEquipos;


    private Equipo equipo;
    private int var;

    private String codigo;


    //        LISTAR
    public void listarEncargadosLaboratorio() throws Exception {
        try {
            dao = new MantenimientoDAO();
            listaEncargadosLaboratorio = dao.listarEncargados();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            dao = new MantenimientoDAO();
            listaEncargadosLaboratorio = dao.listarEncargados();
        } catch (Exception e) {
            throw e;
        }
    }


    public void registrar() {
        try {
            dao = new MantenimientoDAO();

            dao.resgistrar(mantenimiento, listaDetalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.
                    SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" + e));
        }
    }

    public void agregarEquipo() {
        listaa.add(codigo);
        System.out.println("CLICK PRUEBA");
    }


    public void listarEquipos() throws Exception {
        try {
            dao = new MantenimientoDAO();
            listaEquipos = dao.listarEquipos();
        } catch (Exception e) {
            throw e;
        }
    }


    @PostConstruct
    public void init() {
        this.mantenimiento = new Mantenimiento();
        this.listaDetalle = new ArrayList<>();
        this.listaEncargadosLaboratorio = new ArrayList<>();
        this.equipo = new Equipo();
        this.listaEquipos = new ArrayList<>();
        this.listaa = new ArrayList<>();
        this.encargado = new Encargado();
    }


}
