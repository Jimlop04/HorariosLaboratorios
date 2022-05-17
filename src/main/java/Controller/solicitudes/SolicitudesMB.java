/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.solicitudes;

import Controller.login.LoginMB;
import DAO.laboratorios.LaboratorioDAO;
import DAO.solicitudes.solicitudesDAO;
import Model.administracion.Encargado;
import Model.equipos.Equipo;
import Model.laboratorios.Laboratorio;
import Model.login.Login;
import Model.solicitudes.Asignatura;
import Model.solicitudes.Carrera;
import Model.solicitudes.Facultad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import sun.util.logging.resources.logging;

/**
 *
 * @author ebert
 */
@ManagedBean
@ViewScoped
@Getter
@Setter
public class SolicitudesMB implements Serializable {

    private LaboratorioDAO laboratorioDAO;    
    private List<Facultad> listafacultades;
    private List<Carrera> listaCarrera ;
    private List<Asignatura> listAsignatura ;
    private List<Laboratorio> listaLaboratorio ;
    
    
    private List<Equipo> listaEquipos;
            
    private Facultad facultad;
    private Asignatura asignatura;
    
    private Carrera carrera;
    private Encargado encargado;
    private Laboratorio laboratorio;
    private solicitudesDAO dao;
    int idFacultad = 0;
    int idLaboratorio = 0;
     LoginMB l ;
     
     
     
     @PostConstruct
     public void init(){
         asignatura = new Asignatura();
         listaEquipos = new ArrayList<>();
     }

  
    public void listarFacultades(){
        try{
             dao= new solicitudesDAO();
        listafacultades = dao.getfacultades();
        }catch(Exception e){
            throw e;
        }
       
    }

    public void listarCareraByFacultadId() throws Exception {
           if(idFacultad==0){
            listaCarrera=null;
        }
   
        try {
            dao = new solicitudesDAO();
            listaCarrera = dao.listCarreraByFacultad(idFacultad);
        } catch (Exception e) {
            throw e;
        }
    }
      public void generarCodLab() throws Exception {
   
        try {
            dao = new solicitudesDAO();
           listaLaboratorio = dao.getCodigoLab(idLaboratorio);
        } catch (Exception e) {
            throw e;
        }
    }
    public void listaAsignatura( int post) throws Exception {

        try {
            dao = new solicitudesDAO();
            listAsignatura = dao.listMateriaDocente(post);
        } catch (Exception e) {
            throw e;
        }
    }

}
