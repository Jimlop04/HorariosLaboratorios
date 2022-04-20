
package Administracion.controller;

import Administracion.dao.EncargadoDAO;
import Administracion.model.Encargado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Jimmy
 */
@ManagedBean(name = "encargadoMB")

public class EncargadoManageBean implements Serializable {
    private Encargado encargado = new Encargado();
    private EncargadoDAO encargadoDAO = new EncargadoDAO();
    private List<Encargado> listaEncargados = new ArrayList<>();

    
    @PostConstruct
    public void init(){
    System.out.println("PostConstruct");
    listaEncargados = encargadoDAO.getEncargados();
    }
    

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }

    public EncargadoDAO getEncargadoDAO() {
        return encargadoDAO;
    }

    public void setEncargadoDAO(EncargadoDAO encargadoDAO) {
        this.encargadoDAO = encargadoDAO;
    }

    public List<Encargado> getListaEncargados() {
        return listaEncargados;
    }

    public void setListaEncargados(List<Encargado> listaEncargados) {
        this.listaEncargados = listaEncargados;
    }
    
   
  
    
    
     
     
   
    
    
    
}
