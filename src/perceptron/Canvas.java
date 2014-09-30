/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perceptron;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Snipercat
 */
class Canvas extends JPanel implements ActionListener{

    private int columns; 
    private int rows;
    private int squareSize;
    private int [][]matrix;
    private String binaryNumber="0";
    
    
    JButton resetButton;
    JButton calculateButton;
    
    public Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    /**
     * 
     * @param columns
     * @param rows
     * @param squareSize 
     */
    public Canvas(int columns, int rows, int squareSize) {
        this.columns = columns;
        this.rows = rows;
        this.squareSize = squareSize;
        this.matrix = new int[rows][columns];

        //inicializa la matriz con 0
        for(int n = 0; n< rows; n++){
            for(int m = 0; m< columns; m++){
                matrix[n][m] = 0;
        }
        } 
         initUI();
    }
    /**Metodo que inicializa la interfaz grafica
     * 
     */
    private void initUI() {
        setLayout(null);
        setDoubleBuffered(true);        
        
      //Se define el manejador de eventos del mouse
        MovingAdapter ma = new MovingAdapter();
        addMouseMotionListener(ma);
        addMouseListener(ma);
        
      //se ingresan los Botones
        resetButton=new JButton("Borrar");
        resetButton.setBounds(30, rows*squareSize+10, 100, 30);
        resetButton.addActionListener(this);
        add(resetButton);    

        calculateButton=new JButton("calcular");
        calculateButton.setBounds(140, rows*squareSize+10, 100, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);        
    }
    
    /**
     * Encargado de realizar el dibujo en el "canvas"
     * @param g 
     */
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        //Definir fuente usada
        Font font = new Font("Serif", Font.BOLD, 40);
        g2d.setFont(font);
        
        
      //Define color para pintar las linead de la cuadrícula
        g2d.setColor(new Color(214,214,215));
        //Pinta lineas horizontales
        for(int n = 1; n<= rows; n++){
            g2d.drawLine(0, n*squareSize, columns*squareSize, n*squareSize);
            
        }
        
        //Pinta lineas verticales
        for(int m = 1; m<= columns; m++){
            g2d.drawLine(m*squareSize, 0, m*squareSize, rows*squareSize);
            
        }
        
      //Pinta cuadros negros en donde matrix[n][m] == 1
      //cada cuadro es de tamaño squareSize
        g2d.setColor(new Color(0,0,0));
        for(int n = 0; n< rows; n++){
            for(int m = 0; m< columns; m++){
                if(matrix[n][m] == 1)
                    g2d.fillRect(m*squareSize, n*squareSize, squareSize, squareSize);
                    
            }
        }
        
      //Pintar Area de Menu para diferenciarlo de la cuadrícula
        g2d.setColor(new Color(255,255,255));
        //g2d.fillRect(0, rows*squareSize, columns*squareSize, rows*squareSize+100);
      
      //Escribe Número Binario
        g2d.setColor(new Color(0,0,0));
        g2d.drawString(binaryNumber, 250, rows*squareSize+40);
    }
    
    @Override
    /**
     * 
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);        
    }
    
    /**
     * Función encargada del manejo de eventos del botones
     * @param e 
     */
    @Override
     public void actionPerformed(ActionEvent e) {
         //Si se da click en el botón resetButton se reinicia la matriz
        if (e.getSource()==resetButton) {
            for(int n = 0; n< rows; n++){
                for(int m = 0; m< columns; m++){
                    matrix[n][m] = 0;
                }
            }
            binaryNumber = "0";
            repaint();
        }
        //Si se da click en el botón calculateButton se reinicia la matriz
        if(e.getSource()==calculateButton){
            //Obtiene el número binario deacuerdo a la información de la matriz
            Lector l= new Lector(matrix);
            int c=l.numero();
            
            binaryNumber = getNumber(c);
            repaint();
        }
     }
    
     /**
      * Clase para el manejo de eventos del mouse
      */
    class MovingAdapter extends MouseAdapter {

      //Posición en la que se dio click
        private int x;
        private int y;
        private int paint; //Color con el que se deben pintar los cuadros cuando se arrastre el botón

        /**
         * Pinta el cuadro en el que se da click
         * @param e 
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            x = (int) Math.floor(e.getX()/squareSize);
            y = (int) Math.floor(e.getY()/squareSize);

            try{
                matrix[y][x] = (matrix[y][x] +1) %2;
            }
            catch(ArrayIndexOutOfBoundsException exept){
                
            }
            repaint();
            
        }
        
        /**
         * cuando se oprime el botón por un tiempo, se guardan datos de 
         * posición y color a pintar para ser usaron cuando se arrastre el mouse
         * @param e 
         */
        @Override
        public void mousePressed(MouseEvent e) {    
            x = (int) Math.floor(e.getX()/squareSize);
            y = (int) Math.floor(e.getY()/squareSize);
            
            try{
                paint = (matrix[y][x]+1) %2;
            }
            catch(ArrayIndexOutOfBoundsException exept){
                
            }
        }
        
        /**
         * Cuando se arrastra el mouse mientras se da click, se van pintando
         * los cuadros por los que va pasando el cursor, el color dependerá del
         * color que tenía el cuadro en el que se inicio a arrastrar el mouse
         * @param e 
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            x = (int) Math.floor(e.getX()/squareSize);
            y = (int) Math.floor(e.getY()/squareSize);
            try{
                matrix[y][x] = paint;
            }
            catch(ArrayIndexOutOfBoundsException exept){
                
            }
            repaint();
        
        }
        

        
    }
    
    
    /**
     * Función temporal para cambiar el valor de la variable binaryNumber
     * retorna en formato binario el número de cuadros negros que hay en la matriz
     * @param mat
     * @return 
     */
    public String getNumber( int c ){
        
        return Integer.toBinaryString(c);
    }
}
