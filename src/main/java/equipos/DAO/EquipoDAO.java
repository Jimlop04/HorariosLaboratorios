package equipos.DAO;

import equipos.model.Equipo;
import global.Conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO extends Conexion {
    public void resgistrar(Equipo equipo) throws SQLException {
        try {
            String sql_registrar = "INSERT INTO equipos (descripcion, marca, modelo, numero_serie, estado, imagen, id_categoria_equipos, fecha_adquisicion, id_area_aula, codigo)VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.conectar();
            PreparedStatement st = this.getConnection().prepareStatement(sql_registrar);
            st.setString(1, equipo.getDescripcion());
            st.setString(2, equipo.getMarca());
            st.setString(3, equipo.getModelo());
            st.setString(4, equipo.getNumeroSerie());
            st.setString(5, equipo.getEstado());
            st.setString(6, equipo.getEstado());
            st.setInt(7, equipo.getCategoriaEquipo().getIdCategoriaEquipo());
            st.setDate(8, (Date) equipo.getFechaAdquisicion());
            st.setInt(9, equipo.getArea_aula());
            st.setString(10, equipo.getCodigo());
            st.executeUpdate();
        } catch(Exception e){
            throw  e;
        }
        finally {
            this.desconectar();
        }
    }


    public List<Equipo> listar() throws SQLException {
        List<Equipo> lista;
        ResultSet rs;
        try {
            String sql = "SELECT id_categoria_equipos, descripcion, marca, modelo, numero_Serie, fecha_adquisicion, codigo, estado, imagen FROM equipos";
            this.conectar();
            PreparedStatement st  =  this.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                Equipo equipo = new Equipo();
//                equipo.setCategoriaEquipo(rs.getInt("id_categoria_equipos"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setModelo(rs.getString("modelo"));
                equipo.setNumeroSerie(rs.getString("codigo"));

                lista.add(equipo);
            }
        } catch(Exception e){
            throw  e;
        }
        finally {
            this.desconectar();
        }
        return lista;
    }
}
