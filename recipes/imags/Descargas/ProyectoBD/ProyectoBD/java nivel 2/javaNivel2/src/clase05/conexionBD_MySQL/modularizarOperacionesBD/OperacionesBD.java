/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clase05.conexionBD_MySQL.modularizarOperacionesBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Win7
 */
public class OperacionesBD {

    public String bd;
    public String login;
    public String password;
    public String url;
    public boolean conexionExitosa;
    public Connection conexion;
    public PreparedStatement stmt;
    public ResultSet rs;
    public String sDriver;
    public String valorBuscar;
    public String sURL;
    public ArrayList<String> listaLibros;

    public OperacionesBD() {
        this.bd = "prueba23";
        this.login = "root";
        this.password = "";
        this.url = "jdbc:mysql://localhost/" + bd;
        this.conexionExitosa = true;
        this.conexion = null;
        this.stmt = null;
        this.rs = null;
        this.sDriver = "com.mysql.jdbc.Driver";
        this.valorBuscar = null;
        this.sURL = "jdbc:odbc:prueba";
        this.listaLibros = new ArrayList<String>();

    }

    public void instanciarConexiones() {
        try {
            java.lang.Class.forName(this.sDriver).newInstance();
            this.conexion = DriverManager.getConnection(this.url, this.login, this.password);
        } catch (Exception e) {
            System.out.println("Error en la conexi�n:" + e.toString());
            this.conexionExitosa = false;
        }
    }

    public void cerrarConexiones() throws SQLException {
        // Cerramos posibles conexiones abiertas
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (this.conexion != null) {
            this.conexion.close();
        }
    }

    public void cerrarConexionesInsertarModificarEliminar() {

        //insertar o modificar
        if (conexion != null) {
            try {
                stmt.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void mostrarMensajesExcepcionSQL(SQLException sqle) {
        System.out.println("SQLState: " + sqle.getSQLState());
        System.out.println("SQLErrorCode: " + sqle.getErrorCode());
        System.out.println("MENSAJE ASOCIADO AL ERROR: " + sqle.getMessage());
        sqle.printStackTrace();
    }

    public void EstablecerConexionBD() {
        try {
            instanciarConexiones();
            // La Query
            System.out.println("*******ESTABLECIENDO CONEXIÓN CON LA BASE DE DATOS:" + bd);
            System.out.println("*******CONEXIÓN EXITOSA*******");
        } catch (Exception e) {
            System.out.println("Error en la conexi�n:" + e.toString());
            this.conexionExitosa = false;
        } finally {
            try {
                cerrarConexiones();
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: "
                        + e.toString());
            }
        }

    }

    public boolean isConexionExitosa() {
        return conexionExitosa;
    }

    public void setConexionExitosa(boolean conexionExitosa) {
        this.conexionExitosa = conexionExitosa;
    }

    public ArrayList<String> obtenerListaTitulos() {
        System.out.println("LISTANDO LISTA");
        for (int i = 0; i < listaLibros.size(); i++) {
            System.out.println("ELEMENTO LISTA: "+listaLibros.get(i).toString());
        }
        return this.listaLibros;
    }

    public void consultarContenidoBD() {
        System.out.println("***CONTENIDO DE LA BASE DE DATOS***");
        try {

            instanciarConexiones();
            try {
                // La Query
                stmt = conexion.prepareStatement("SELECT titulo FROM libros");
                rs = stmt.executeQuery();

                // Recorremos el resultado
                listaLibros.clear();
                while (rs.next()) {
                    System.out.println(rs.getString("titulo"));
                    listaLibros.add(rs.getString("titulo"));
                }
                
                //System.out.println("ELEMENTOS CARGADOS EN LA LISTA DE TÍTULOS");
                //obtenerListaTitulos();
            } catch (SQLException sqle) {
                mostrarMensajesExcepcionSQL(sqle);
            }

        } catch (Exception e) {
            System.out.println("Error en la conexi�n:" + e.toString());
            this.conexionExitosa = false;
        } finally {
            try {
                // Cerramos posibles conexiones abiertas
                cerrarConexiones();
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: "
                        + e.toString());
            }
        }

    }

    public void insertarDatos(String elementoInsertar) {
        System.out.println("******* INSERTANDO ELEMENTOS EN LA BASE DE DATOS*******");
        try {
            instanciarConexiones();

            stmt = conexion.prepareStatement("INSERT INTO libros VALUES (?)");
            stmt.setString(1, elementoInsertar);
            int retorno = stmt.executeUpdate();

            System.out.println("RETORNO ES: " + retorno);
            System.out.println("REGISTRO INSERTADO EXITOSAMENTE");
            System.out.println("Elemento " + elementoInsertar + "fue insertado ");

        } catch (SQLException sqle) {
            mostrarMensajesExcepcionSQL(sqle);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarConexionesInsertarModificarEliminar();
        }
        System.out.println("Registro insertado exitosamente");
    }

    public void modificarContenidoBD(String elementoBuscarBD, String elementoModificarBD) {

        // Cargamos el driver y realizamos la conexi�n
        try {
            instanciarConexiones();
            //stmt = conexion.prepareStatement("UPDATE libros SET titulo='777' WHERE titulo=" + "'" + elementoBuscarBD + "'" + "");

            stmt = conexion.prepareStatement("UPDATE libros SET titulo=" + "'" + elementoModificarBD + "'" + ""
                    + "WHERE titulo=" + "'" + elementoBuscarBD + "'" + "");

            int retorno = stmt.executeUpdate();

        } catch (SQLException sqle) {
            mostrarMensajesExcepcionSQL(sqle);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarConexionesInsertarModificarEliminar();

        }
        System.out.println("REGISTRO ACTUALIZADO EXITOSAMENTE");
    }

    public void eliminarContenidoBD(String elementoEliminarBD) {

        // Cargamos el driver y realizamos la conexi�n
        try {
            instanciarConexiones();

            //stmt = conexion.prepareStatement("DELETE FROM libros WHERE titulo='88888888'");
            stmt = conexion.prepareStatement("DELETE FROM libros WHERE titulo=" + "'" + elementoEliminarBD + "'" + "");

            int retorno = stmt.executeUpdate();
            System.out.println("ELEMENTO " + elementoEliminarBD + " HA SIDO ELIMINADO");

        } catch (SQLException sqle) {
            mostrarMensajesExcepcionSQL(sqle);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarConexionesInsertarModificarEliminar();
        }
        System.out.println("Registro eliminado exitosamente");
    }
}
