/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clase05.conexionBD_MySQL.modularizarOperacionesBD;

/**
 * Ejemplo de uso de JComboBox.
 *
 * @author chuidiang
 */
import clase05.conexionBD_MySQL.modularizarOperacionesBD.ComboBox;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ComboBox {

    private JComboBox combo1;
    private JComboBox combo2;

    public static void main(String[] args) {
        new ComboBox();
    }

    public ComboBox() {
        JFrame v = new JFrame();
        v.getContentPane().setLayout(new FlowLayout());
        combo1 = new JComboBox();
        rellenaCombo1();
        combo1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                rellenaCombo2((String) combo1.getSelectedItem());
            }
        });

        combo2 = new JComboBox();
        rellenaCombo2((String) combo1.getSelectedItem());

        v.getContentPane().add(combo1);
        v.getContentPane().add(combo2);
        v.pack();
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void rellenaCombo1() {
        ArrayList listaTitulosCombo = new ArrayList<String>();
        OperacionesBD bd = new OperacionesBD();
        bd.EstablecerConexionBD();

        if (bd.isConexionExitosa()) {

            System.out.println("\n\n******* CONSULTANDO CONTENIDO DE LA BASE DE DATOS ");
            bd.consultarContenidoBD();

            
            listaTitulosCombo=bd.obtenerListaTitulos();
            
            System.out.println("TAMAÑO LISTA: "+listaTitulosCombo.size());
            for (int i = 0; i < listaTitulosCombo.size(); i++) {

             combo1.addItem(listaTitulosCombo.get(i).toString());
            }

            //combo1.addItem("letras");
            //combo1.addItem("numeros");
        }
    }

    private void rellenaCombo2(String seleccionEnCombo1) {
        combo2.removeAllItems();
        if (seleccionEnCombo1.equals("Programacion 7D")) {
            combo2.addItem("A");
            combo2.addItem("B");
            combo2.addItem("C");
        } else if (seleccionEnCombo1.equals("Programacion 8D")) {
            combo2.addItem("1");
            combo2.addItem("2");
            combo2.addItem("3");
        }

    }
}
