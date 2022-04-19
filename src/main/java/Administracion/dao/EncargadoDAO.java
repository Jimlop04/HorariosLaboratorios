
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
    
  private Conexion conexion;
  private ResultSet resultSet;

    public EncargadoDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    public EncargadoDAO() {
       
    }
  
  public List<Encargado> getEncargados(){
  List<Encargado> Encargados = new ArrayList<>();
  
  String sql = String.format("SELECT * FROM encargado;");
  try{
       resultSet = conexion.ejecutarSql(sql);
       
       while(resultSet.next()){
       Encargados.add(new Encargado(resultSet.getInt("id_encargado"),resultSet.getString("nombre_encargado"),
               resultSet.getDate("fecha_inicio"),resultSet.getDate("fecha_fin"),resultSet.getBoolean("activo")));
       }
  } catch (SQLException e) {
      System.out.println(e.getMessage());
  } finally{
     conexion.desconectar();
  }
  return Encargados;
      
  }
  
    
    
    
    
}
