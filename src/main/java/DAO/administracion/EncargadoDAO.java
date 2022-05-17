package DAO.administracion;

import Model.administracion.Encargado;
import Model.laboratorios.Laboratorio;
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

    public List<Encargado> getEncargados() {
        List<Encargado> Encargados = new ArrayList<>();

        String sql = String.format("SELECT * from laboratorio.listar_tabla_encargados()");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);
            while (resultSet.next()) {
                Encargados.add(new Encargado(
                        resultSet.getInt("idusu"),
                        resultSet.getString("nomusu"),
                        resultSet.getString("passwusu"),
                        resultSet.getDate("fcreacionusu"),
                        resultSet.getBoolean("estadusu"),
                        resultSet.getInt("idpersona"),
                        resultSet.getString("nompersona"),
                        resultSet.getString("apepersona"),
                        resultSet.getString("dnipersona"),
                        resultSet.getDate("fdnpersona"),
                        resultSet.getString("genpersona"),
                        resultSet.getString("correopersona"),
                        resultSet.getString("celupersona"),
                        resultSet.getInt("idusuroles"),
                        resultSet.getInt("idroles"),
                        resultSet.getString("nomrol"),
                        resultSet.getInt("idencarg"),
                        resultSet.getInt("idencarglabo"),
                        resultSet.getDate("finicio"),
                        resultSet.getDate("ffin"),
                        resultSet.getBoolean("estadocarglabo"),
                        resultSet.getInt("idlabo"),
                        resultSet.getString("nomlab"),
                        resultSet.getString("codlab")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return Encargados;
    }

    public List<Encargado> getListaLaboratoriosXencargado(String dni) {
        List<Encargado> Encargados = new ArrayList<>();

        String sql = "SELECT * from laboratorio.listar_tabla_encargado_laboratorios('" + dni + "')";
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);
            while (resultSet.next()) {
                Encargados.add(new Encargado(
                        resultSet.getInt("idusu"),
                        resultSet.getString("nomusu"),
                        resultSet.getString("passwusu"),
                        resultSet.getDate("fcreacionusu"),
                        resultSet.getBoolean("estadusu"),
                        resultSet.getInt("idpersona"),
                        resultSet.getString("nompersona"),
                        resultSet.getString("apepersona"),
                        resultSet.getString("dnipersona"),
                        resultSet.getDate("fdnpersona"),
                        resultSet.getString("genpersona"),
                        resultSet.getString("correopersona"),
                        resultSet.getString("celupersona"),
                        resultSet.getInt("idusuroles"),
                        resultSet.getInt("idroles"),
                        resultSet.getString("nomrol"),
                        resultSet.getInt("idencarg"),
                        resultSet.getInt("idencarglabo"),
                        resultSet.getDate("finicio"),
                        resultSet.getDate("ffin"),
                        resultSet.getBoolean("estadocarglabo"),
                        resultSet.getInt("idlabo"),
                        resultSet.getString("nomlab"),
                        resultSet.getString("codlab")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return Encargados;
    }

    public List<Encargado> getListRoles() {
        List<Encargado> roles = new ArrayList<>();

        String sql = String.format("SELECT * from laboratorio.listar_roles()");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                roles.add(new Encargado(
                        resultSet.getInt("idrol"),
                        resultSet.getString("nomrol"),
                        resultSet.getString("descriprol"),
                        resultSet.getBoolean("estadrol")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return roles;
    }
      public List<Encargado> getListRolesTRUE() {
        List<Encargado> roles = new ArrayList<>();

        String sql = String.format("SELECT * from laboratorio.listar_roles_true()");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);

            while (resultSet.next()) {
                roles.add(new Encargado(
                        resultSet.getInt("idrol"),
                        resultSet.getString("nomrol"),
                        resultSet.getString("descriprol"),
                        resultSet.getBoolean("estadrol")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return roles;
    }

    public void modificarRol(Encargado encargado) {
        try {
            String sentencia = "SELECT laboratorio.editar_rol("
                    + "'" + encargado.getIdRoles() + "',"
                    + "'" + encargado.getNombre_rol() + "',"
                    + "'" + encargado.getDescripcion_rol() + "',"
                    + "'" + encargado.getEstado_rol() + "')";
            conexion.ejecutarSql(sentencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    public int getultimoIdRol() {
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql("SELECT laboratorio.obtenerultimoidroles()");
            if (resultSet.next()) {
                return resultSet.getInt("obtenerultimoidroles");
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return -1;
    }

    public Encargado registrarRol(Encargado encargado) {
        try {
            String sentencia = "SELECT laboratorio.registrar_rol("
                    + "'" + encargado.getNombre_rol() + "',"
                    + "'" + encargado.getDescripcion_rol()+ "',"
                    + "'" + encargado.getEstado_rol()+ "')";
            conexion.ejecutarSql(sentencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return encargado;
    }

    public void updateEncargado(Encargado encargado) throws SQLException {
        try {
            String sentencia = "SELECT laboratorio.editar_encargado("
                    + "'" + encargado.getIdEncargado() + "',"
                    + "'" + encargado.getFecha_inicio() + "',"
                    + "'" + encargado.getFecha_fin() + "',"
                    + "'" + encargado.getIdLaboratorio() + "',"
                    + "'" + encargado.getIdEncargado() + "',"
                    + "'" + encargado.getEstado_EncargadoLaboratorio() + "')";
            conexion.ejecutarSql(sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.desconectar();
        }
    }

    public Laboratorio ObtenerCodigo(int id) {
        Laboratorio cod = null;
        try {
            conexion.conectar();
            ResultSet result = conexion.ejecutarSql("SELECT laboratorio.obtenercodigolaboratorio(" + id + ")");

        } catch (Exception ex) {
            System.out.println("Error No ahi Codigo" + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
        return cod;
    }

    public void cargarFacturasPendientesPorCliente() {
        encargado = new Encargado();
    }

    public Encargado verificarExisteEncargado(String dniencargado) {
        encargado = new Encargado();
        String sentencia = "SELECT* from laboratorio.verificar_dni('" + dniencargado + "')";
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sentencia);
            while (resultSet.next()) {
                encargado = new Encargado(
                        resultSet.getInt("idusu"),
                        resultSet.getString("nomusu"),
                        resultSet.getString("passwusu"),
                        resultSet.getDate("fcreacionusu"),
                        resultSet.getBoolean("estadusu"),
                        resultSet.getInt("idpersona"),
                        resultSet.getString("nompersona"),
                        resultSet.getString("apepersona"),
                        resultSet.getString("dnipersona"),
                        resultSet.getDate("fdnpersona"),
                        resultSet.getString("genpersona"),
                        resultSet.getString("correopersona"),
                        resultSet.getString("celupersona"),
                        resultSet.getInt("idusuroles"),
                        resultSet.getInt("idroles"),
                        resultSet.getString("nomrol"),
                        resultSet.getInt("idencarg"),
                        resultSet.getInt("idencarglabo"),
                        resultSet.getDate("finicio"),
                        resultSet.getDate("ffin"),
                        resultSet.getBoolean("estadocarglabo"),
                        resultSet.getInt("idlabo"),
                        resultSet.getString("nomlab"),
                        resultSet.getString("codlab"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            conexion.desconectar();
        }

        return encargado;
    }

    public void deleteAsignacionLaboratorio(int aux) throws SQLException {

        try {
            String sql = ("DELETE FROM laboratorio.encargado_laboratorio WHERE id_encargado_laboratorio = '" + aux + "'");
            conexion.ejecutarSql(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.desconectar();
        }
    }

    public void insertEncargadolaboratorio(Encargado encargado) {
        try {
            String sql = "SELECT laboratorio.registrar_encargado_laboratorio("
                    + "'" + encargado.getFecha_inicio_registro() + "',"
                    + "'" + encargado.getFecha_fin_registro() + "',"
                    + "'" + encargado.getIdLaboratorio() + "',"
                    + "'" + encargado.getIdEncargado() + "',"
                    + "'" + encargado.getEstado_rel() + "')";

            conexion.ejecutarSql(sql);
        } catch (Exception e) {
        } finally {
            conexion.desconectar();
        }
    }

    public List<Encargado> getUsuarios() {
        List<Encargado> Encargados = new ArrayList<>();

        String sql = String.format("SELECT * from laboratorio.listar_tabla_usuarios()");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);
            while (resultSet.next()) {
                Encargados.add(new Encargado(
                        resultSet.getInt("idusu"),
                        resultSet.getString("nomusu"),
                        resultSet.getString("passwusu"),
                        resultSet.getDate("fcreacionusu"),
                        resultSet.getBoolean("estadusu"),
                        resultSet.getInt("idpersona"),
                        resultSet.getString("nompersona"),
                        resultSet.getString("apepersona"),
                        resultSet.getString("dnipersona"),
                        resultSet.getDate("fdnpersona"),
                        resultSet.getString("genpersona"),
                        resultSet.getString("correopersona"),
                        resultSet.getString("celupersona"),
                        resultSet.getInt("idusuroles"),
                        resultSet.getInt("idroles"),
                        resultSet.getString("nomrol"),
                        resultSet.getBoolean("estadrol")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return Encargados;
    }

}
