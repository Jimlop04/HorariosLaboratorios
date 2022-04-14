
package global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author ebert
 */
public class Conexion {

    public Connection connection;
    private Statement statement;
    private ResultSet result;
    private boolean estado;

    //Credenciales para la conexion
    private String url = "jdbc:postgresql://ec2-34-194-73-236.compute-1.amazonaws.com:5432/d90f44f58p7329";
    private String usuario = "rvypcdvgpfbhwr";
    private String clave = "63256d6b01a5e8e9b6a19b80424148551283aa4fce0cd105fe242b3cc1e530d5";
    private String classForName = "org.postgresql.Driver";

    public Conexion() {
        estado = true;
    }

    public Conexion(String user, String pass, String url) {
        usuario = user;
        clave = pass;
        this.url = url;
        estado = true;
    }
    //Métodos get y set

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    

    //  MÉTODO CONECTAR PARA INICIAR UNA CONEXIÓN A LA BASE DE DATOS
    public boolean conectar() {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, usuario, clave);
                statement = connection.createStatement();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("No hay conexion a la base de datos: " + e.getMessage());
        }
        return false;
    }

    //  MÉTODO DESCONECTAR PARA CERRAR UNA CONEXIÓN A LA BASE DE DATOS
    public boolean desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                statement.close();
                return true;
            } else {
                System.out.println("No hay una conexion para desconectar");
            }
        } catch (SQLException ex) {
            System.out.println("Hubo un problema al desconectar la conexion");
        }
        return false;
    }

    //  EJECUTAR CONSULTAS SQL
    public ResultSet ejecutarSql(String sql) {
        try {
            conectar();
            result = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Error: No se ejecuto la consulta: " + ex.getMessage());
        }
        return result;
    }

}
