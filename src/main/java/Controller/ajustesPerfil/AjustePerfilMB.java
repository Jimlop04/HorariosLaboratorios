/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ajustesPerfil;

import DAO.ajustesPerfil.AjustePerfilDAO;
import Model.login.Rol;
import global.AjustePerfil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ebert
 */
@ManagedBean
@ViewScoped
@Getter
@Setter
public class AjustePerfilMB {

    AjustePerfil ajustePerfil;
    List<AjustePerfil> listaAjustePefil;
    AjustePerfilDAO daoPerfil;
    Rol rol;

    @PostConstruct
    public void init() {
        ajustePerfil = new AjustePerfil();
        listaAjustePefil = new ArrayList<>();        
        daoPerfil = new AjustePerfilDAO(); 
        rol = new Rol();
    }
    
    public void listarPerfil(int usuarioRol, int idPersona, int idUsuario){
        try{
            ajustePerfil = daoPerfil.ajustePerfil(usuarioRol, idPersona, idUsuario);
            
        }catch(Exception e){
            
        }
    }
    
    public void listaRol(int idRol){
        try{
            rol = daoPerfil.roles(idRol);
            
        }catch(Exception e){
            
        }
    }

}
