package DAO.equipos;

import Model.equipos.CategoriaEquipo;
import global.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaEquipoDAO extends Conexion {
    public ArrayList<CategoriaEquipo> listar() throws Exception {
        ArrayList<CategoriaEquipo> lista = new ArrayList<>();
        ResultSet rs;
        try {
            this.conectar();
            String query = "SELECT * FROM laboratorio.categoria_equipos ORDER BY nombre ASC";
            PreparedStatement st = this.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoriaEquipo ce = new CategoriaEquipo();
                ce.setIdCategoriaEquipo(rs.getInt("id_categoria_equipos"));
                ce.setNombre(rs.getString("nombre"));
                ce.setDescripcion(rs.getString("descripcion"));
                lista.add(ce);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
}
