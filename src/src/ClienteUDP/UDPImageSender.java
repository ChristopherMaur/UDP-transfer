package src.ClienteUDP;

import java.io.ByteArrayOutputStream; 
import java.io.File; 
import java.net.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


  
class UDPImageSender { 
  public UDPImageSender(String Ruta,String Ip,int puerto) throws Exception 
    { 
        
       BufferedImage img = ImageIO.read(new File(Ruta));
       ByteArrayOutputStream baos = new ByteArrayOutputStream();        
       ImageIO.write(img, "jpg", baos);
       baos.flush();
       byte[] sendData = baos.toByteArray();

       DatagramSocket senderSocket = new DatagramSocket();       
       InetAddress IPReceptor = InetAddress.getByName(Ip);
       System.out.println("UDP has sent an image of size:"+sendData.length);

       DatagramPacket packet = new DatagramPacket(sendData, sendData.length, IPReceptor, puerto);

       senderSocket.send(packet);  

       senderSocket.close();
    }
}
