/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perceptron;

import java.util.ArrayList;

public class Lector {
    int inicio=0;    
    int n=0;
    int[][] matriz;
    int[] next={0,0};
    int[][] recorrido= new int[50][2];
    
    public Lector(int[][] a){
        this.matriz=a;
        
    }
    /**retornael numero rastreado dentro de la matriz
     *@return Retorna el resultado del numero en entero del numero hallado en la matriz
     */
    public int numero(){
        int resultado=1;
        ArrayList<Integer> numeros= new ArrayList<Integer>();
        int h=0;
        while(h<matriz.length){
            int k=rastreo(h);
            
            inicio++;
            numeros.add(k);
            System.out.println("arreglo de nueros" + " inicio "+ inicio + " numeros "+numeros);
            h=inicio;
        }
        
        for(int i=0;i<numeros.size();i++){
            resultado=resultado*numeros.get(i);
        }       
        return resultado;
    }
    /**
     * 
     * @param x
     * @return 
     */
    
    public int rastreo(int x){       
        
        int p=0;
        int p1=0;
        int p2=0;
        
        int k=3;
        for(int i=x;i<matriz[0].length;i++){
            for(int j=0;j<matriz.length;j++){
                if(matriz[j][i]==1){
                    p1=j;p2=i;
                    p=1;
                    break;                    
                }
                if(p==1){break;}
            }
            inicio=i;
            if(p==1){break;}
        }
        k=identificar(p1,p2,matriz)[0];
        System.out.println(k);
        return k;
    }
    
    /**Metodo que identifica el numero dibujado
     * 
     * @param y
     * @param x
     * @param matriz
     * @return 
     */
    public int[] identificar(int y, int x, int[][] matriz){
        
        //pimer numer: numero, segundo: posicion donde se queda en x
        int[] resultado={1,0};
        int b=y;
        int a=x;
        boolean f=true;
        char[] vertices= {' ',' ',' ',' '};
        
        
        char[] dos={'a','b','d','c'};
        char[] cinco={'d','c','a','b'};
        char[] cero={'d','c','b',' '};
        char[] uno={' ',' ',' ',' '};
        int n1=0;
        while (f){
            
            Object[] rvert=vertice(b,a,matriz);
            
            if((char)rvert[0]!=' '){
                vertices[n1]=(char)rvert[0];n1++;
                b=(int)rvert[1];
                a=(int)rvert[2];
                
                if(a>inicio){inicio=a;}
            }
            else{          
                Object[] rmov=mover(b,a,matriz);
                
                f=(Boolean)rmov[0];
                b=(int)rmov[1];
                a=(int)rmov[2];
                //if(a>inicio){inicio=a;}
            }
            
        }
        
        if(vertices[0]==dos[0]&& vertices[1]==dos[1]&& vertices[2]==dos[2]&&vertices[3]==dos[3]){
            resultado[0]=2;}
        if(vertices[0]==cinco[0]&& vertices[1]==cinco[1]&& vertices[2]==cinco[2]&&vertices[3]==cinco[3]){
            resultado[0]=5;}
        if(vertices[0]==cero[0]&& vertices[1]==cero[1]&& vertices[2]==cero[2]&&vertices[3]==cero[3]){
            resultado[0]=10;}
        if(vertices[0]==uno[0]&& vertices[1]==uno[1]&& vertices[2]==uno[2]&&vertices[3]==uno[3]){
            resultado[0]=1;}
        return resultado;
    }
    
    /**metodo que lee se desplaza sobre la cadena de cuadros pintados
     * 
     * @param y
     * @param x
     * @param matriz
     * @return 
     */
    public Object[] mover(int y, int x, int[][] matriz){
        
        recorrido[n][0]=y;
        recorrido[n][1]=x;n++;
        Object[] resultado ={false,100,100};
        try{
        if(!isIn(y+1,x,recorrido)&&matriz[y+1][x]==1){
            resultado[0]=true;
            resultado[1]=y+1;
            resultado[2]=x;
        }
        else{if(!isIn(y,x+1,recorrido)&&matriz[y][x+1]==1){
            resultado[0]=true;
            resultado[1]=y;
            resultado[2]=x+1;
        }else{if(!isIn(y-1,x,recorrido)&&matriz[y-1][x]==1){
            resultado[0]=true;
            resultado[1]=y-1;
            resultado[2]=x;
        }else{if(!isIn(y,x-1,recorrido)&&matriz[y][x-1]==1){
            resultado[0]=true;
            resultado[1]=y;
            resultado[2]=x-1;
        }else{
            resultado[0]=false;
            resultado[1]=100;
            resultado[2]=100;
        }}}
        }}catch(Exception e){}
        
        return resultado;
    }
    
    
    /** Metodo que identifica el tipo de vertice encontrado
     * 
     * @param y
     * @param x
     * @param matriz
     * @return 
     */
    public Object[] vertice(int y, int x, int[][] matriz){
        
        Object[] vert={' ',0,0,0,0};     
        
        if(matriz[y][x+1]==1 && matriz[y+1][x]==0 && matriz[y+1][x+1]==1){
            vert[0]='a';
            vert[1]=y+1;
            vert[2]=x+1;
            recorrido[n][0]=y;
            recorrido[n][1]=x;n++;
            recorrido[n][0]=y;
            recorrido[n][1]=x+1;n++;            
        }
        
        try{
        if(matriz[y][x-1]==0 && matriz[y+1][x-1]==1 && matriz[y+1][x]==1){
            vert[0]='b';
            vert[1]=y+1;
            vert[2]=x-1;
            recorrido[n][0]=y;
            recorrido[n][1]=x;n++;
            recorrido[n][0]=y+1;
            recorrido[n][1]=x;n++;
        }}catch(Exception e){} 
        
        if(matriz[y][x+1]==0 && matriz[y+1][x]==1 && matriz[y+1][x+1]==1){
            vert[0]='c';
            vert[1]=y+1;
            vert[2]=x+1;
            recorrido[n][0]=y;
            recorrido[n][1]=x;n++;
            recorrido[n][0]=y+1;
            recorrido[n][1]=x;
        }        
        if(matriz[y][x+1]==1 && matriz[y+1][x]==1 && matriz[y+1][x+1]==0){
            vert[0]='d';
            vert[1]=y+1;
            vert[2]=x;
            vert[3]=y;
            vert[4]=x+1;
            recorrido[n][0]=y;
            recorrido[n][1]=x;n++;
        }
        
        return vert;
    }    
    /**
     * 
     * @param y
     * @param x
     * @param recorrido
     * @return 
     */
    public boolean isIn(int y, int x,int[][] recorrido){
        Boolean flag = null;
        int[] k={y,x};
        for(int i=0;i<recorrido.length;i++){
            
            if(recorrido[i][0]==k[0]&& recorrido[i][1]==k[1]){flag=true;break;}
            else{flag=false;}
                                   
        }
        
        return flag;
    }
}
