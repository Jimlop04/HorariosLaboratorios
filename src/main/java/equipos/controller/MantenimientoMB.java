package equipos.controller;

import Administracion.model.Encargado;
import equipos.DAO.EquipoDAO;
import equipos.DAO.MantenimientoDAO;
import equipos.model.DetalleMantenimiento;
import equipos.model.Mantenimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.*;
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

    MantenimientoDAO dao;
    List<DetalleMantenimiento> listaDetalle;
    List<Encargado> listaEncargadosLaboratorio;
//    private int  idEquipo;





    //        LISTAR
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
            System.out.println(listaDetalle);
            dao.resgistrar(mantenimiento, listaDetalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" + e));
        }
    }

    public void agregarEquipo( int idEquipo) {
        DetalleMantenimiento det = new DetalleMantenimiento();
        det.setIdEquipo(idEquipo);
        listaDetalle.add(det);
        System.out.println(listaDetalle);
        System.out.println("CLICK PRUEBA");
        JOptionPane.showMessageDialog(null, "Mensaje de pregunta",
                "QUESTION_MESSAGE", JOptionPane.QUESTION_MESSAGE);
    }



    @PostConstruct
    public void init() {
        this.mantenimiento = new Mantenimiento();
        this.listaDetalle = new ArrayList<>();
        this.listaEncargadosLaboratorio = new ArrayList<>();
    }

}
