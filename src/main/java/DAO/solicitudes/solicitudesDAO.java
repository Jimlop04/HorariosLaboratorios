/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.solicitudes;

import Model.solicitudes.Carrera;
import global.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebert
 */
public class solicitudesDAO extends Conexion{
    
    public List<Carrera> listCarreraByFacultad(int idFacultad)throws Exception{
        List<Carrera> lista;
        ResultSet rs;
        try{
            conectar();
            String sql= "select id_carrera,nombre_carrera from laboratorio.carrera where facultad_id_facultad ="+idFacultad;
             PreparedStatement st = this.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Carrera obj = new Carrera();
                obj.setIdCarrera(rs.getInt("id_carrera"));
                obj.setNombreCarrera(rs.getString("nombre_carrera"));
                lista.add(obj);
            }
        }catch(Exception e){
            throw e;
        }finally{
            desconectar();
        }
        return lista;                
    }
    
}
