/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perceptron;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Snipercat
 */
public class MenuWindow extends JFrame implements ActionListener{
    
    
    private JLabel tittleLabel;
    
    private JLabel nLabel;
    private JTextField ntextfield;
    
    private JLabel mLabel;
    private JTextField mtextfield;
    
    private JLabel     pLabel;
    private JTextField ptextfield;
    
    private JButton boton1;
    
    public MenuWindow() {

        initUI();
    }

    private void initUI() {
//parámetros de la ventana
        setTitle("Parámetros de Matriz");
        setLayout(null);
        setSize(250, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
/**
 * Agregar Items
 */        
        setLayout(null);
        tittleLabel=new JLabel("Seleccionar Tamaño de la Matriz");
        tittleLabel.setBounds(0,10,this.getWidth(),20);
        tittleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(tittleLabel);
     //----   N
        nLabel=new JLabel("Filas");
        nLabel.setBounds(40,50,100,20);
        add(nLabel);
        
        ntextfield=new JTextField();
        ntextfield.setText("50");
        ntextfield.setBounds(nLabel.getX() + nLabel.getWidth() +10,  nLabel.getY(),  30, nLabel.getHeight());
        ntextfield.setHorizontalAlignment(SwingConstants.RIGHT);
        add(ntextfield);
        
     //----   M
        mLabel=new JLabel("Columnas");
        mLabel.setBounds(nLabel.getX(),    nLabel.getY() + nLabel.getHeight() + 10 , 100 , nLabel.getHeight());
        add(mLabel);
        
        mtextfield=new JTextField();
        mtextfield.setText("100");
        mtextfield.setBounds(mLabel.getX()+mLabel.getWidth() +10,  mLabel.getY(),  30, mLabel.getHeight());
        mtextfield.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mtextfield);
     
    //----   P
        pLabel=new JLabel("Tamaño Cuadros");
        pLabel.setBounds(mLabel.getX(),    mLabel.getY() + mLabel.getHeight() + 10 , 100 , mLabel.getHeight());
        add(pLabel);
        
        ptextfield=new JTextField();
        ptextfield.setText("10");
        ptextfield.setBounds(pLabel.getX()+pLabel.getWidth() +10,  pLabel.getY(),  30, pLabel.getHeight());
        ptextfield.setHorizontalAlignment(SwingConstants.RIGHT);
        add(ptextfield);
        
        
    //----   BUTTON       
              
        boton1=new JButton("Iniciar");
        boton1.setBounds(pLabel.getX(),pLabel.getY() + pLabel.getHeight() + 10 ,100,30);
        boton1.addActionListener(this);        
        add(boton1);
        
    }
    
    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource()==boton1) {
            //try/catch usado para manejar el error ocurrido cuando se ingresa letras
            try{
            int n = Integer.parseInt(ntextfield.getText());
            int m = Integer.parseInt(mtextfield.getText());
            int p = Integer.parseInt(ptextfield.getText());
            
            //Cierra la ventana actual y abre una nueva pasandole los parámetros ingresados
            dispose();
            new MatrixWindow(m, n, p);
            
            }catch (NumberFormatException nfe) {
                //en caso de error muestra una alerta
               JOptionPane.showMessageDialog(null,"Todos los datos son obligatorios y deben ser numericos");
            } 
        }
    }
    
}