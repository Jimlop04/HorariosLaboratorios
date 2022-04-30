
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
  
  String sql = String.format("SELECT * from laboratorio.listar_encargados()");
  try{
       conexion.conectar();
       resultSet = conexion.ejecutarSql(sql);
       
       while(resultSet.next()){
       Encargados.add(new Encargado(
               resultSet.getString("nomper"),
               resultSet.getString("apeper"),
               resultSet.getString("nomlabo"),
               resultSet.getString("nomrol"),
               resultSet.getDate("fini"),
               resultSet.getDate("ffin"),
               resultSet.getBoolean("estad")));
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
       
       public void modificarRol(Encargado encargado) throws SQLException{
        try {
            String sentencia = String.format("SELECT laboratorio.editar_rol("
                    + "'" + encargado.getIdRoles() + "',"
                    + "'" + encargado.getNombre_rol() + "')");
            conexion.ejecutarSql(sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.desconectar();
        }
    }
       
     public int getultimoIdRol(){
         try {
             conexion.conectar();
             resultSet = conexion.ejecutarSql("SELECT laboratorio.obtenerultimoidroles()");
             if(resultSet.next()){
             return resultSet.getInt("obtenerultimoidroles");
             }
         } catch (Exception e) {
             System.out.println("Error" + e.getMessage());
         } finally{
         conexion.desconectar();
         }
     return -1;
     }
     
     public Encargado registrarRol(Encargado encargado) {
        try {
            String sentencia = String.format("SELECT laboratorio.registrar_rol(\n"
                    + "	'" + encargado.getNombre_rol() + "')");
            conexion.ejecutarSql(sentencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return encargado;
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
