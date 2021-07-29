/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.ClienteUDP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

    
public class VentanaSendImage extends JFrame {

        
    private JTextField ip;
    private final JTextField puerto;
    private JTextField rangoinicio;
    private JTextField rangofinal;
    private final JTextField Ruta;
    private final JLabel ipLabel;
    private final JLabel puertoLabel;
    private final JLabel ingresarRuta;
    private final JLabel inicioFin;
    private final JButton enviar;
    private final JButton abrir;
    private final JButton salir;
    public String ruta="";   
    public String texto = "";
    public String Sip = "";
    public int n;
    public int i=0;
    public int x=1;
    public int puertos=9875;
 
    
        public VentanaSendImage(){
  
           this.setLayout(null);
           this.setTitle("Mi ventana de entrada");
           this.setBounds(40,40,400,200);
           this.setLocationRelativeTo(null);
           
           
           
           ipLabel = new JLabel("IP SERVIDOR");
           ipLabel.setBounds(10, 10, 100, 20);
           add(ipLabel);
           
           ip = new JTextField();
           ip.setBounds(120, 10, 100, 20);
           ip.setText("127.0.0.1");
           add(ip);
           
           puertoLabel = new JLabel("PUERTO");
           puertoLabel.setBounds(10, 30, 100, 20);
           add(puertoLabel);
           
           puerto = new JTextField();
           puerto.setBounds(120, 30, 100, 20);
           puerto.setText("9875");
           add(puerto);
           
           ingresarRuta = new JLabel("INGRESAR RUTA");
           ingresarRuta.setBounds(10, 50, 100, 20);
           add(ingresarRuta);
            
           Ruta = new JTextField();
           Ruta.setBounds(120, 50, 200, 20);
           Ruta.setText("c:/...");
           add(Ruta);
           
           abrir= new JButton("Open File");
           abrir.setBounds(330, 50, 20, 20);
           add(abrir);
           
           inicioFin = new JLabel("CANTIDAD FRAMES");
           inicioFin.setBounds(10, 70, 150, 20);
           add(inicioFin);
           
           rangoinicio = new JTextField();
           rangoinicio.setBounds(120, 70, 100, 20);
           rangoinicio.setText("0");
           add(rangoinicio);
           
           rangofinal = new JTextField();
           rangofinal.setBounds(220, 70, 100, 20);
           rangofinal.setText("749");
           add(rangofinal);
           
           enviar= new JButton("ENVIAR");
           enviar.setBounds(10,100, 100, 50);
           add(enviar);
           
           salir= new JButton("SALIR");
           salir.setBounds(220,100, 100, 50);
           add(salir);
           

           salir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	
            	System.exit(EXIT_ON_CLOSE);
               
            }
           });
           
           enviar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Guardardatos();
                } catch (Exception ex) {
                    Logger.getLogger(VentanaSendImage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

               private void Guardardatos() throws Exception {
                   Sip=ip.getText();
                   System.out.println("IP SERVER = "+Sip);
                   
                   puertos=Integer.parseInt(puerto.getText());
                   System.out.println("PUERTO = "+puertos);
                   
                   i=Integer.parseInt(rangoinicio.getText());
                   System.out.println("INICIO DE IMAGENES = "+i);
                   
                   n=Integer.parseInt(rangofinal.getText());
                   System.out.println("CANTIDAD DE NUMEROS = "+n);
                   
                   System.out.println("NOMBRE ARCHIVO = "+texto);
                   System.out.println("RUTA ARCHIVO = "+ruta);
                   
                   while(i<n){
                   String sURL = ruta;
                   String algo="-"+i+".jpg";
                   String sNuevaRuta = reemplazar(sURL,"-0.jpg",algo);
                   System.out.println(sNuevaRuta);
                   UDPImageSender Enviar = new UDPImageSender(sNuevaRuta,Sip,puertos);
                   i++;
                   }
               }
           });

           abrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                abrirArchivo();
            }

           });
           
           
           
           
      }
        
      public static String reemplazar(String cadena, String busqueda, String reemplazo) {
        return cadena.replaceAll(busqueda, reemplazo);
     }
        
        
        
       private void abrirArchivo() {
        JFileChooser file=new JFileChooser();
        file.showOpenDialog(this);
        File abre=file.getSelectedFile();
        texto=abre.getName();
        ruta=abre.getPath();
        }

}
