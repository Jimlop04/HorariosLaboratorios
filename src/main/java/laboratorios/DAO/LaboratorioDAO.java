/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorios.DAO;

import global.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import laboratorios.model.Laboratorio;


/**
 *
 * @author Jimmy
 */
public class LaboratorioDAO {
    
    Conexion conexion = new Conexion();
    private Laboratorio laboratorio;
    private ResultSet resultSet;
    private List<Laboratorio> listaLaboratorios;

    public LaboratorioDAO() {
        laboratorio = new Laboratorio();
        conexion = new Conexion();
        listaLaboratorios = new ArrayList<>();
    }
    
    public LaboratorioDAO(Laboratorio laboratorio) {
        conexion = new Conexion();
        this.laboratorio = laboratorio;
    }
    
     public List<Laboratorio> getLaboratorios(){
  List<Laboratorio> Laboratorios = new ArrayList<>();
  
  String sql = String.format("SELECT * FROM laboratorio l inner join area_aula aa on l.\"idLaboratorio\" = aa.\"laboratorio_idLaboratorio\" ");
  try{
       conexion.conectar();
       resultSet = conexion.ejecutarSql(sql);
       
       while(resultSet.next()){
      Laboratorios.add(new Laboratorio(
              resultSet.getInt("idLaboratorio"),
              resultSet.getInt("facultad_idfacultad"),
               resultSet.getString("nombre_laboratorio"),
               resultSet.getString("codigo_laboratorio"),
               resultSet.getInt("id_area_aula"),
               resultSet.getInt("laboratorio_idLaboratorio"),
               resultSet.getString("codigo_aula"),
               resultSet.getString("nombre_aula"),
               resultSet.getInt("capacidad_aula")));
  
       }
  } catch (SQLException e) {
      System.out.println(e.getMessage());
  } finally{
     conexion.desconectar();
  }
  return Laboratorios; 
  }
    
    
    
    
    
    
    
    
    
    
}
