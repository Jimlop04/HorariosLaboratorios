
package Administracion.controller;

import Administracion.dao.EncargadoDAO;
import Administracion.model.Encargado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;


/**
 *
 * @author Jimmy
 */
@ManagedBean(name = "encargadoMB")
@ViewScoped
public class EncargadoManageBean implements Serializable {
    
    private List<Encargado> listaEncargados = new ArrayList<>();
     private EncargadoDAO encargadoDAO;
     private Encargado encargado;

     
    public EncargadoManageBean() {
        encargadoDAO = new EncargadoDAO();
        encargado = new Encargado();
    }

    @PostConstruct
    public void init(){
    System.out.println("PostConstruct");
    listaEncargados = encargadoDAO.getEncargados();
    }
    
   
    public List<Encargado> getListaEncargados() {
        return listaEncargados;
    }

    public void setListaEncargados(List<Encargado> listaEncargados) {
        this.listaEncargados = listaEncargados;
    }

    public EncargadoDAO getEncargadoDAO() {
        return encargadoDAO;
    }

    public void setEncargadoDAO(EncargadoDAO encargadoDAO) {
        this.encargadoDAO = encargadoDAO;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }
    
    
    
     
     
   
    
    
    
}
