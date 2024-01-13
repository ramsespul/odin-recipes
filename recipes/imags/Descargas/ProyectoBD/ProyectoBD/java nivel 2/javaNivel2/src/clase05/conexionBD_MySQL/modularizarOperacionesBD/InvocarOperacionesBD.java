/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase05.conexionBD_MySQL.modularizarOperacionesBD;

/**
 *
 * @author PROFESOR
 */
public class InvocarOperacionesBD {

    public static String valorInsertar;
    public static String elementoBuscarBD;
    public static String elementoModificarBD;
    public static String elementoEliminarBD;

    public static void main(String[] args) {

        OperacionesBD bd = new OperacionesBD();
        bd.EstablecerConexionBD();

        if (bd.isConexionExitosa()) {

            System.out.println("\n\n******* CONSULTANDO CONTENIDO DE LA BASE DE DATOS ");
            bd.consultarContenidoBD();

            bd.EstablecerConexionBD();

            //insertmaos un valor en base de datos
            valorInsertar = "Programacion 50D";
            bd.insertarDatos(valorInsertar);

            //Consultamos el contenido de la base de datos
            System.out.println("\n\n*******CONSULTANDO LA BASE DE DATOS, LUEGO DE INSERTAR EL REGISTRO*******");
            System.out.println("*******CONTENIDO DE LA BASE DE DATOS*******");
            bd.consultarContenidoBD();

            System.out.println("\n\n*******VAMOS A MODIFICAR LA BASE DE DATOS******* ");

            elementoBuscarBD = "Programacion 50D";
            elementoModificarBD = "8888888";

            bd.modificarContenidoBD(elementoBuscarBD, elementoModificarBD);
            //ec.modificarContenidoBD(valorInsertar, valorInsertar);
            System.out.println("Valor " + elementoBuscarBD + "fue modificado ");

            System.out.println("****CONTENIDO DE LA BASE DE DATOS LUEGO DE MODIFICAR****");
            bd.consultarContenidoBD();

            valorInsertar = "Programacion 7D";
            bd.insertarDatos(valorInsertar);

            valorInsertar = "Programacion 8D";
            bd.insertarDatos(valorInsertar);
            valorInsertar = "Programacion 9D";
            bd.insertarDatos(valorInsertar);
            System.out.println("****CONTENIDO DE LA BASE DE DATOS LUEGO DE MODIFICAR****");
            bd.consultarContenidoBD();

            //Eliminamos registroa de la base de datos
            elementoEliminarBD = "Programacion 9D";
            System.out.println("VAMOS A ELIMINAR EL ELEMENTO " + elementoEliminarBD);
            bd.eliminarContenidoBD(elementoEliminarBD);

            //Verificamos si el elemento fue eliminado
            bd.consultarContenidoBD();

        } else {
            System.out.println("NO SE PUEDE ESTABLECER LA CONEXIÓN A LA BASE DE DATOS");
        }

    }
}
