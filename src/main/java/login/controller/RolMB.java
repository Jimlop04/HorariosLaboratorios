package login.controller;

import global.Mensajes;
import login.dao.RolDAO;
import login.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class RolMB extends Mensajes {
    private RolDAO dao;
    private  Rol rol;


    public List<Rol> listaRolresUsuarios;


    public void listarRolesID(int id) throws Exception {
        try {
            dao = new RolDAO();
            listaRolresUsuarios = dao.listarRoles(id);
        } catch (Exception e) {
            throw e;
        }
        System.out.println(listaRolresUsuarios);
    }
    public void listarRolObt(int id){
        try{
            dao = new RolDAO();
            this.rol = dao.listarRoleObtenido(id);

        }catch (Exception e){

        }

    }

    @PostConstruct
    public void init() {
        this.rol = new Rol();
        this.listaRolresUsuarios = new ArrayList<>();
    }
}
