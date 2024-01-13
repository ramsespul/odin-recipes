/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clase05.conexionBD_MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Win7
 */
public class modificarDatos {

    public static void main(String argv[]) {
        Connection con = null;
        PreparedStatement stmt = null;

        //Definimos el driver y la url
        String sDriver = "com.mysql.jdbc.Driver";
        String sURL = "jdbc:mysql://localhost:3306/prueba";

        // Cargamos el driver y realizamos la conexi�n
        try {
            Class.forName(sDriver).newInstance();
            con = DriverManager.getConnection(sURL, "root", "123");

            stmt = con.prepareStatement("UPDATE libros SET titulo='Programacion3D' WHERE titulo='Programacion 8D'");
            int retorno = stmt.executeUpdate();




        } catch (SQLException sqle) {
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
            sqle.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Registro actualizado exitosamente");
    }
}
