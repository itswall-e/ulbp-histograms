/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import org.opencv.core.Mat;

/**
 *
 * @author Paulo Andrade
 */
public final class Algorithms
{
    // Obtenemos imagen con coordenadas chromaticas
    public static Mat chromaticCoordinates(Mat m)
    {
        int index = 0;
        byte r, g, b;
        
        // Obtenemos el tamaño total de la imagen
        int size = (int) (m.total() * m.elemSize());
        // Creamos una matriz de bytes del tamaño de la matriz original
        byte buffer[] = new byte[size];
        // volcamos la imagen de OpenCV a la de Java
        m.get(0, 0, buffer);
        // Recorremos cada uno de los datos de la imagen
        for(int i = 0; i < size; i++){
            // manipulamos la imagen
            switch(index){
                case 0:
                    // aumentamos el index
                    index++;
                    break;
                case 1:
                    // aumentamos el index
                    index++;
                    break;
                case 2:
                    // Obtenemos el valor de los canales
                    r = buffer[i-2];
                    g = buffer[i-1];
                    b = buffer[i];
                    int sum = r + g + b;
                    // verificamos que no se divida entre 0
                    if((sum) != 0){
                        // asignamos nuevos valores
                        buffer[i-2] = (byte) (r / sum); // channel red
                        buffer[i-1] = (byte) (g / sum); // channel green
                        buffer[i] = (byte) (b / sum); // channel blue
                    }
                    // reiniciamos el indice y suma
                    index = 0;
                    break;
            }
        }
        // volcamos la imagen de Java a la de OpenCV
        m.put(0, 0, buffer);

        // retornamos la matriz
        return m;
    }
    
    // 
    public static Mat whitePatch(Mat m)
    {
        int index = 0;
        byte rMax = 0, gMax = 0, bMax = 0;
        
        // Obtenemos el tamaño total de la imagen
        int size = (int) (m.total() * m.elemSize());
        // Creamos una matriz de bytes del tamaño de la matriz original
        byte buffer[] = new byte[size];
        // volcamos la imagen de OpenCV a la de Java
        m.get(0, 0, buffer);
        
        // Obtenemos los valores maximos de (R,G,B)
        for(int i = 0; i < size; i++){
            // manipulamos la imagen
            switch(index){
                case 0:
                    // aumentamos el index
                    index++;
                    break;
                case 1:
                    // aumentamos el index
                    index++;
                    break;
                case 2:
                    // Obtenemos los valores maximos de cada canal
                    if(buffer[i-2] > rMax) rMax = buffer[i-2];
                    if(buffer[i-1] > gMax) gMax = buffer[i-1];
                    if(buffer[i] > bMax) bMax = buffer[i];
                    // reiniciamos el indice y suma
                    index = 0;
                    break;
            }
        }
        
        // Recorremos cada uno de los datos de la imagen
        for(int i = 0; i < size; i++){
            // manipulamos la imagen
            switch(index){
                case 0:
                    // aumentamos el index
                    index++;
                    break;
                case 1:
                    // aumentamos el index
                    index++;
                    break;
                case 2:
                    // Obtenemos el valor de los canales
                    if(rMax != 0) buffer[i-2] *= (byte)(225 / rMax);
                    if(gMax != 0) buffer[i-1] *= (byte)(255 / gMax);
                    if(bMax != 0) buffer[i] *= (byte)(255 / bMax);
                    // reiniciamos el indice y suma
                    index = 0;
                    break;
            }
        }
        // volcamos la imagen de Java a la de OpenCV
        m.put(0, 0, buffer);

        // retornamos la matriz
        return m;
    }
}
