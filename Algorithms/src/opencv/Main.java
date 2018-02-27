/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

// Importamos las librerias
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 *
 * @author Paulo Andrade
 */
public class Main
{   
    // Cargamos las librerias dinamicas de OpenCV
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // creamos la matriz para los frame
        Mat frame = new Mat();
        // accedemos al dispositivo
        VideoCapture cam = new VideoCapture(0);
        // configuramos el ancho y alto (doble video)
        cam.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
        cam.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);
        // Creamos la ventana
        ImageViewer view = new ImageViewer("Mostramos video con OpenCV");
        
        // verificamos que accedemos al dispositivo
        if(cam.isOpened()){
            // accedemos a los frames de la camara
            while(true){
                // Obtenemos el frame de la camara
                cam.read(frame);
                // Mostramos el frame
                view.show(frame);
            }
        } else {
            // mostramos error de carga
            System.out.println("Error al inicializar el dispositivo");
        }
    }
}
