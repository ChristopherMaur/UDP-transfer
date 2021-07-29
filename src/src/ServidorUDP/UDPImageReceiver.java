package src.ServidorUDP;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.ByteArrayInputStream; 
import java.net.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

class UDPImageReceiver extends JFrame{ 
  final static int MAX_SIZE = 2662700;
  public static BufferedImage receivedImage;
  
  
    public UDPImageReceiver(){
        super("ABRIR IMAGEN");
    }

    public void paint(Graphics g){
        Graphics2D g2 =(Graphics2D)g;
        try {
            BufferedImage image=receivedImage;
            g2.drawImage(image,0,20,this);
        } catch (Exception e) {
        }
    }

    
    
  public static void main(String args[]) throws Exception 
    { 
        UDPImageReceiver v = new UDPImageReceiver();
        v.setDefaultCloseOperation(EXIT_ON_CLOSE);
        v.setSize(600,400);
        v.setVisible(true);

       
        while(true){
           DatagramSocket receiverSocket = new DatagramSocket(9875);
           System.out.println("Waiting for an Image at port:"+receiverSocket.getLocalPort());       
           byte[] receiverData = new byte[MAX_SIZE]; 
           DatagramPacket packet = new DatagramPacket(receiverData, receiverData.length);
           receiverSocket.receive(packet);  
           // Transformando imagen en byte en una Imagen
           receivedImage = ImageIO.read(new ByteArrayInputStream(packet.getData()));
           v.repaint();
           receiverSocket.close();
        }   
    }
}