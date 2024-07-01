/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall.server;
import java.net.Socket;


/**
 *
 * @author s-sys
 */
public class HallServer {
    
    public static Hall hall;
    
    public static void main(String[] args) {
         //יצירת אוביקט מסוג סרוור 
        Server server=new Server();
         //הצהרה על סוקט של לקוח
        Socket socket;
        hall=new Hall();
        hall.initHall();
        //המשך לקבל חיבורים תמיד
         while (true) {
            //במקרה והתקבל לקוח שומר את הסוקט שלו
            socket = server.Accept();
           //הדפסה של כתובת הצינור של הלקוח שהתקבל
            System.out.println("Client: " + socket.getInetAddress().getHostName() + " arrived");
            //יצירת thread  ללקוח שהתקבל
            Thread thread = new Thread(new ClientHandler(socket));
            thread.start();
        }
        
    }

}
