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
                        resultSet.getInt("idlabo"),
                        resultSet.getString("nomlab"),
                        resultSet.getString("codlab")));
            }
            System.out.println(Encargados);
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
                        resultSet.getInt("idlabo"),
                        resultSet.getString("nomlab"),
                        resultSet.getString("codlab")));
            }
            System.out.println(Encargados);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return Encargados;
    }

    public List<Encargado> getRoles() {
        List<Encargado> roles = new ArrayList<>();

        String sql = String.format("SELECT * from laboratorio.listar_rol_t_d()");
        try {
            conexion.conectar();
            resultSet = conexion.ejecutarSql(sql);
            while (resultSet.next()) {
                roles.add(new Encargado(
                        resultSet.getInt("idrol"),
                        resultSet.getString("nomrol")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return roles;
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

    public void modificarRol(Encargado encargado) throws SQLException {
        try {
            String sentencia = String.format("SELECT laboratorio.editar_rol("
                    + "'" + encargado.getIdRoles() + "',"
                    + "'" + encargado.getNombre_rol() + "',"
                    + "'" + encargado.getDescripcion_rol() + "',"
                    + "'" + encargado.getEstado_rol() + "')");
            conexion.ejecutarSql(sentencia);
        } catch (Exception e) {
            throw e;
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

    public void editarEncargado(Encargado encargado) throws SQLException {
        try {

            String sentencia = String.format("SELECT laboratorio.editar_encargado("
                    + "'" + encargado.getIdUsuario() + "',"
                    + "'" + encargado.getNombre_usuario() + "',"
                    + "'" + encargado.getPassword_usuario() + "',"
                    + "'" + encargado.getFechacreacion_usuario() + "',"
                    + "'" + encargado.getEstado_usuario() + "',"
                    + "'" + encargado.getIdPersona() + "',"
                    + "'" + encargado.getNombre_persona() + "',"
                    + "'" + encargado.getApellido_persona() + "',"
                    + "'" + encargado.getDni_persona() + "',"
                    + "'" + encargado.getFechanacimiento_persona() + "',"
                    + "'" + encargado.getGenero_persona() + "',"
                    + "'" + encargado.getCorreo_persona() + "',"
                    + "'" + encargado.getCelular_persona() + "',"
                    + "'" + encargado.getIdUsuaRoles() + "',"
                    + "'" + encargado.getIdRoles() + "',"
                    + "'" + encargado.getNombre_rol() + "',"
                    + "'" + encargado.getIdEncargado() + "',"
                    + "'" + encargado.getIdEncargadoLaboratorio() + "',"
                    + "'" + encargado.getFecha_inicio() + "',"
                    + "'" + encargado.getFecha_fin() + "',"
                    + "'" + encargado.getIdLaboratorio() + "',"
                    + "'" + encargado.getNombre_laboratorio() + "',"
                    + "'" + encargado.getCodigo_laboratorio() + "')");

            System.out.println(sentencia);
            conexion.ejecutarSql(sentencia);
            System.out.println(sentencia);
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

    /**
     * public Encargado registrarEncargado(Encargado encargado) throws
     * SQLException { try { conexion.conectar(); String sentencia = "Select
     * public.registrarencargados('" + encargado.getNombre_encargado() + "',\n"
     * + "'" + encargado.getApellidos_encargado() + "',\n" + "'" +
     * encargado.getFecha_inicio() + "',\n" + "'" + encargado.getFecha_fin() +
     * "',\n" + "'" + encargado.isEstado() + "',\n" + "'" +
     * encargado.getNombre_rol() + "');"; conexion.ejecutarSql(sentencia);
     *
     * String sentencia2 = "INSERT INTO public.encargado_rol(\n" + "
     * \"rol_idRol\", \"encargado_idEncargado\")\n" + "	VALUES ((select
     * MAX(\"idRoles\") from public.roles),\n" + "	(select MAX(\"idEncargado\")
     * from public.encargado));"; conexion.ejecutarSql(sentencia2);
     *
     * } catch (Exception e) { System.out.println(e.getMessage()); } finally {
     * conexion.desconectar(); } return encargado; } *
     */
}
