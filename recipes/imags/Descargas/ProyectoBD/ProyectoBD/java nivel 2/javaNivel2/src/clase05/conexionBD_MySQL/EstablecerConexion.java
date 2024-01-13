/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clase05.conexionBD_MySQL;

/**
 *
 * @author Win7
 */
import java.sql.*;

public class EstablecerConexion {

    public String bd = "prueba";
    public String login = "root";
    public String password = "123";
    public String url = "jdbc:mysql://localhost/" + bd;
    public boolean conexionExitosa=false;

    
    public EstablecerConexion(){
        conectar();
    }

    public void conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            if (conn != null) {
                System.out.println("Conexión a base de datos " + url + " ... Ok");
                conn.close();
                conexionExitosa=true;
            }
        } catch (SQLException ex) {
            System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
    
    public boolean isConexionExitosa() {
        return conexionExitosa;
    }

    public void setConexionExitosa(boolean conexionExitosa) {
        this.conexionExitosa = conexionExitosa;
    }
    

    public static void main(String argv[]){
       EstablecerConexion ec=new EstablecerConexion();

    }
}
