
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

    public EncargadoDAO() {
        encargado = new Encargado();
        conexion = new Conexion();
        listaEncargados = new ArrayList<>();
    }

    public EncargadoDAO(Encargado encargado) {
        conexion = new Conexion();
        this.encargado = encargado;
    }

   
    public List<Encargado> getEncargados(){
  List<Encargado> Encargados = new ArrayList<>();
  
  String sql = String.format("SELECT * FROM encargado e inner join encargado_rol eo "
          + "on e.\"idEncargado\" = eo.\"encargado_idEncargado\" inner join roles r on  r.\"idRoles\" = eo.\"rol_idRol\"");
  try{
       conexion.conectar();
       resultSet = conexion.ejecutarSql(sql);
       
       while(resultSet.next()){
       Encargados.add(new Encargado(
               resultSet.getInt("idEncargado"),
               resultSet.getString("nombre_encargado"),
               resultSet.getString("apellidos_encargado"),
               resultSet.getDate("fecha_inicio"),
               resultSet.getDate("fecha_fin"),
               resultSet.getBoolean("estado"),
               resultSet.getInt("idRoles"),
               resultSet.getString("nombre_rol")));
       }
  } catch (SQLException e) {
      System.out.println(e.getMessage());
  } finally{
     conexion.desconectar();
  }
  return Encargados; 
  }
    
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
    }
    
    
    
    
 
}
