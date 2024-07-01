/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall.client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import hall.server.SeatPlace;

/**
 *
 * @author s-sys
 */
public class Client {
      //ערוץ תקשורת

    private Socket server;
    //קריאה  של אוביקט- מהקובץ לשרת
    private ObjectOutputStream toServer;
    //כתיבה של אוביקט מהשרת לקובץ
    private ObjectInputStream fromserver;
    public Client(){
     
     try {
            //אתחול הסוקט של הקלינט:
            //ipconfig -כתובת המחשב
            //port -הפורט עליו ירוץ
           // "192.168.0.233"
            server = new Socket("localhost", 7000);
            //היכן לכתוב או לקרא
            toServer = new ObjectOutputStream(server.getOutputStream());
            fromserver = new ObjectInputStream(server.getInputStream());
            System.out.println("success...");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
   public void writeToServer(SeatPlace object) {
        try {
            toServer.writeObject(object);
            //reset
            toServer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromServer() {
        try {
            return (String)fromserver.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public SeatPlace[][] readFromServer2(){
          try {
            return (SeatPlace[][])fromserver.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
      }
//     public SeatPlace readFromServer3(){
//          try {
//            return (SeatPlace)fromserver.readObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//      }
}

