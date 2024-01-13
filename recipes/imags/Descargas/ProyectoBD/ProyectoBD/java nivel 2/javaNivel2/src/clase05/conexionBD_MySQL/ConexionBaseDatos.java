package clase05.conexionBD_MySQL;

/**
 * @file ConexionBaseDatos.java
 * @version 1.1
 * @author Linea de Codigo (http://lineadecodigo.com)
 * @date 8-agosto-2007
 * @url
 * http://lineadecodigo.com/2007/08/10/conectarnos-a-una-base-de-datos-con-jdbc/
 * @description Realizar una conexi�n JDBC con drivers tipo 1 a una base de
 * datos
 */
import java.sql.*;

public class ConexionBaseDatos {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sDriver = "com.mysql.jdbc.Driver";
        try {

            Class.forName(sDriver).newInstance();
            //con = DriverManager.getConnection(sURL);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba22", "root", "");

            try {

                // La Query
                stmt = con.prepareStatement("SELECT titulo FROM libros");
                rs = stmt.executeQuery();

                // Recorremos el resultado
                while (rs.next()) {
                    System.out.println(rs.getString("titulo"));
                }

            } catch (SQLException sqle) {
                System.out.println("Error en la ejecuci�n:"
                        + sqle.getErrorCode() + " " + sqle.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error en la conexi�n:" + e.toString());
        } finally {
            try {
                // Cerramos posibles conexiones abiertas
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: "
                        + e.toString());
            }
        }
        System.out.println("Conexión a la base de datos establecida exitosamente");
    }

}
