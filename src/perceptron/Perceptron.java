/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perceptron;

import javax.swing.SwingUtilities;

/**
 *
 * @author Snipercat
 */
public class Perceptron {
    /**Metodo principal que llama el menu emergente principal*/
    public static void main(String[] args) {
        
        
         SwingUtilities.invokeLater(new Runnable() {
             //inicializa la ventana del menu
            @Override
            public void run() {
                MenuWindow sk = new MenuWindow();
                sk.setVisible(true);
            }
        });
    }
        
}
