package Controller.login;

import Model.administracion.Usuario;
import global.Mensajes;
import DAO.login.RolDAO;
import Model.login.Rol;
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

    //lista los roles de un usuario  por nombre de usuario
    public void listarRolesNombre(Usuario username) throws Exception {
        try {
            dao = new RolDAO();
            listaRolresUsuarios = dao.listarRolesByUserName(username);
        } catch (Exception e) {
            throw e;
        }
        System.out.println(listaRolresUsuarios);
    }

    public void listarRolSession(int id){
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
