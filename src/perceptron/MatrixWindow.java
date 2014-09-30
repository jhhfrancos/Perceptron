/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perceptron;

import javax.swing.JFrame;

/**
 *
 * @author Snipercat
 */
public class MatrixWindow  extends JFrame{
    
    private final int columns;
    private final int rows;
    private final int squareSize;
    /** Se inicializa la ventana de matriz 
     *@param columns numero de columnas de la matriz
     *@param rows numero de filas de la matriz
     *@param squareSize tamaño de los cuadros de la matriz
     *
     */
    public MatrixWindow(int columns, int rows, int squareSize) {
        this.columns = columns;
        this.rows = rows;
        this.squareSize = squareSize;
        initUI();
    }
    /**Inicializacion de la interfaz grafica*/
    private void initUI() {
        
        //Atributos de la ventana
        setTitle("Matriz");
        setSize( columns*squareSize+10, rows*squareSize + 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
        
        //Agrega un objeto de tipo JPanel pasandole los parámetros
        add(new Canvas(columns, rows, squareSize));
        
        setVisible(true);
    }
}


