
package Administracion.dao;

import Administracion.model.Encargado;
import global.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jimmy
 */
public class EncargadoDAO {
  
   Conexion conexion = new Conexion();
   private Encargado encargado;
   private ResultSet resultSet;
   private List<Encargado> listaEncargados;
   private List<Encargado> listaRoles;

    public EncargadoDAO() {
        encargado = new Encargado();
        conexion = new Conexion();
        listaEncargados = new ArrayList<>();
        listaRoles = new ArrayList<>();
    }

    public EncargadoDAO(Encargado encargado) {
        conexion = new Conexion();
        this.encargado = encargado;
    }

   
  public List<Encargado> getEncargados(){
  List<Encargado> Encargados = new ArrayList<>();
  
  String sql = String.format("select * from laboratorio.usuario u inner join laboratorio.persona p on u.\"persona_idPersona\" = p.\"idPersona\" inner join laboratorio.usuarioroles ur on ur.\"usuario_idUsuario\" = u.\"idUsuario\"\n" +
"inner join laboratorio.roles r on r.\"idRoles\" = ur.\"roles_idRoles\" inner join laboratorio.encargado en on en.\"persona_idPersona\" = p.\"idPersona\" \n" +
"inner join laboratorio.encargado_laboratorio el on el.\"encargado_idEncargado\" = en.\"idEncargado\" inner join laboratorio.laboratorio labo on labo.\"idLaboratorio\" = el.\"laboratoro_idLaboratorio\"");
  try{
       conexion.conectar();
       resultSet = conexion.ejecutarSql(sql);
       
       while(resultSet.next()){
       Encargados.add(new Encargado(
               resultSet.getInt("idPersona"),
               resultSet.getString("nombre_persona"),
               resultSet.getString("apellido_persona"),
               resultSet.getInt("idLaboratorio"),
               resultSet.getString("nombre_laboratorio"),
               resultSet.getInt("idRoles"),
               resultSet.getString("nombre_rol"),
               resultSet.getInt("idEncargadoLaboratorio"),
               resultSet.getDate("fecha_inicio"),
               resultSet.getDate("fecha_fin"),
               resultSet.getBoolean("estado")));
       }
  } catch (SQLException e) {
      System.out.println(e.getMessage());
  } finally{
     conexion.desconectar();
  }
  return Encargados; 
  }
  
    public List<Encargado> getRoles(){
  List<Encargado> roles = new ArrayList<>();
  
  String sql = String.format("SELECT * from laboratorio.lista_tecnicos()");
  try{
       conexion.conectar();
       resultSet = conexion.ejecutarSql(sql);
       
       while(resultSet.next()){
       roles.add(new Encargado(
               resultSet.getInt("idrol"),
               resultSet.getString("nomrol")));
       }
  } catch (SQLException e) {
      System.out.println(e.getMessage());
  } finally{
     conexion.desconectar();
  }
  return roles; 
  }
    
       public List<Encargado> getListRoles(){
  List<Encargado> roles = new ArrayList<>();
  
  String sql = String.format("SELECT * from laboratorio.listar_roles()");
  try{
       conexion.conectar();
       resultSet = conexion.ejecutarSql(sql);
       
       while(resultSet.next()){
       roles.add(new Encargado(
               resultSet.getInt("idrol"),
               resultSet.getString("nomrol")));
       }
  } catch (SQLException e) {
      System.out.println(e.getMessage());
  } finally{
     conexion.desconectar();
  }
  return roles; 
  }
       
       public void modificarRol(Encargado encargado) throws SQLException {
        try {

            String sentencia = String.format("SELECT laboratorio.editar_rol("
                    + "'" + encargado.getIdRoles() + "',"
                    + "'" + encargado.getNombre_rol() + "')");
            System.out.println(sentencia);
            conexion.ejecutarSql(sentencia);
            System.out.println(sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.desconectar();
        }
    }
       
       
    /**
     public Encargado registrarEncargado(Encargado encargado) throws SQLException {
        try {
            conexion.conectar();
            String sentencia = "Select public.registrarencargados('" + encargado.getNombre_encargado() + "',\n"
                    + "'" + encargado.getApellidos_encargado() + "',\n"
                    + "'" + encargado.getFecha_inicio() + "',\n"
                    + "'" + encargado.getFecha_fin() + "',\n"
                    + "'" + encargado.isEstado() + "',\n"
                    + "'" + encargado.getNombre_rol() + "');";
            conexion.ejecutarSql(sentencia);
            
            String sentencia2 = "INSERT INTO public.encargado_rol(\n" +
"	 \"rol_idRol\", \"encargado_idEncargado\")\n" +
"	VALUES ((select MAX(\"idRoles\") from public.roles),\n" +
"			(select MAX(\"idEncargado\") from public.encargado));";
            conexion.ejecutarSql(sentencia2);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return encargado;
    } **/
    
    
    
    
 
}
