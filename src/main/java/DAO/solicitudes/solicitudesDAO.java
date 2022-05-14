/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.solicitudes;

import Model.laboratorios.Laboratorio;
import Model.solicitudes.Carrera;
import Model.solicitudes.Facultad;
import global.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebert
 */
public class solicitudesDAO extends Conexion {

    public List<Facultad> getfacultades() {
        List<Facultad> facultades = new ArrayList<>();
        ResultSet resultSet;
        String sql = String.format("SELECT * FROM laboratorio.facultad");
        try {
            conectar();
            resultSet = ejecutarSql(sql);

            while (resultSet.next()) {
                facultades.add(new Facultad(
                        resultSet.getInt("id_facultad"),
                        resultSet.getString("nombre_facultad")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
        return facultades;
    }

    public List<Carrera> listCarreraByFacultad(int idFacultad) throws Exception {
        List<Carrera> lista;
        ResultSet rs;
        try {
            conectar();
            String sql = "select id_carrera,nombre_carrera from laboratorio.carrera where facultad_id_facultad =" + idFacultad;
            PreparedStatement st = this.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Carrera obj = new Carrera();
                obj.setIdCarrera(rs.getInt("id_carrera"));
                obj.setNombreCarrera(rs.getString("nombre_carrera"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            desconectar();
        }
        return lista;
    }

    public List<Laboratorio> getCodigoLab(int idLab) throws Exception {
        List<Laboratorio> lista;
        ResultSet rs;
        try {
            String sql = "select*from laboratorio.laboratorio where id_laboratorio="+idLab;
            conectar();
            PreparedStatement st = getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Laboratorio obj = new Laboratorio();
                obj.setCodigo_laboratorio(rs.getNString("codigo_laboratorio"));

                lista.add(obj);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return lista;
    }

}
