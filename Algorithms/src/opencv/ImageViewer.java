/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

// Importamos las librerias
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import org.opencv.core.Mat;

/**
 *
 * @author Paulo Andrade
 */
public class ImageViewer
{
    JFrame v;
    JLabel lblImg, lblImgExit, lblTextImg, lblTextImgExit;
    JPanel mainPanel, algorithmsPanel;
    JScrollPane ispOrigin, ispTarget;
    JRadioButton rWhitePatch, rChromaticCoordinates;
    ButtonGroup btnGroup;
    int width, height;
    String title;
    Mat m;
    
    // Constructor
    public ImageViewer(String text)
    {   
        // titulo de la ventana
        title = text;
        // valores de ancho y alto por defecto
        width = 1000;
        height = 600;
        // Inicializamos los componentes de swing
        initComponents();
    }
    
    // Inicializamos componentes Swing
    public void initComponents()
    {
        v = new JFrame(title);
        // configuracion para windows
        v.setSize(width, height + 25);
        // configuracion para linux
        // v.setSize(width, height);
        v.setResizable(false);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setLayout(null);
        
        // Componentes
        mainPanel = new JPanel();
        mainPanel.setBounds(10, 10, width - 20, height - 20);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(null);
        
        lblImg = new JLabel();
        ispOrigin = new JScrollPane(lblImg);
        ispOrigin.setPreferredSize(new Dimension(480, 400));
        ispOrigin.setBounds(0, 0, 480, 400);
        
        lblImgExit = new JLabel();
        ispTarget = new JScrollPane(lblImgExit);
        ispTarget.setPreferredSize(new Dimension(480, 400));
        ispTarget.setBounds(500, 0, 480, 400);
        
        lblTextImg = new JLabel("Imagen original");
        lblTextImg.setBounds(500, 410, 480, 20);
        lblTextImg.setHorizontalAlignment(JLabel.CENTER);
        
        lblTextImgExit = new JLabel("Imagen procesada");
        lblTextImgExit.setBounds(0, 410, 480, 20);
        lblTextImgExit.setHorizontalAlignment(JLabel.CENTER);
        
        algorithmsPanel = new JPanel();
        algorithmsPanel.setBounds(0, 440, width - 20, 150);
        algorithmsPanel.setBorder(BorderFactory.createTitledBorder("Filtros disponibles"));
        algorithmsPanel.setBackground(Color.WHITE);
        algorithmsPanel.setLayout(null);
        
        rWhitePatch = new JRadioButton("White Patch");
        rWhitePatch.setBounds(10, 30, 300, 20);
        rWhitePatch.setBackground(Color.WHITE);
        rWhitePatch.setSelected(true);
        
        rChromaticCoordinates = new JRadioButton("Chromatic Coordinates");
        rChromaticCoordinates.setBounds(10, 60, 300, 20);
        rChromaticCoordinates.setBackground(Color.WHITE);
        
        // Button group
        btnGroup.add(rWhitePatch);
        btnGroup.add(rChromaticCoordinates);
        
        // Añadimos componentes
        v.add(mainPanel);
        mainPanel.add(ispOrigin);
        mainPanel.add(ispTarget);
        mainPanel.add(lblTextImg);
        mainPanel.add(lblTextImgExit);
        mainPanel.add(algorithmsPanel);
        algorithmsPanel.add(rWhitePatch);
        algorithmsPanel.add(rChromaticCoordinates);
        
        // Mostramos ventana
        v.setVisible(true);
    }
    
    // Inicializamos componentes
    public void show(Mat m)
    {
        // Convertimos la matriz en imagen y la mostramos
        lblImg.setIcon(new ImageIcon(ImageProcessor.toBufferedImage(m)));
        
        // verificamos el algoritmo seleccionado
        if(rWhitePatch.isSelected()){
            m = Algorithms.whitePatch(m);
        } else if(rChromaticCoordinates.isSelected()){
            m = Algorithms.chromaticCoordinates(m);
        }
        lblImgExit.setIcon(new ImageIcon(ImageProcessor.toBufferedImage(m)));
    }
}
