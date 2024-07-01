package hall.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;




public class ClientHandler implements Runnable {
      // רשימה ששומרת את כל הלקוחות שהתחברו לשרת בשביל לעדכן את כל הלקוחות על פרק שנתפס על ידי הסרד האנונימי
    public static Vector<ObjectOutputStream> lstClients=new Vector<ObjectOutputStream>();
    //סוקט של לקוח
    Socket socket;
    //קריאה מהלקוח לשרת
    ObjectInputStream fromClient;
    //כתיבה מהשרת ללקוח
    ObjectOutputStream toClient;

    public ClientHandler(Socket socket) {
        this.socket = socket;

    }
    @Override
    public void run() {
        SeatPlace seatPlace=new SeatPlace(0, 0);
        int r,c;
       
        try {
            fromClient = new ObjectInputStream(socket.getInputStream());//init
            toClient = new ObjectOutputStream(socket.getOutputStream());//init
          //הוספת הלקוח שהתחבר
            lstClients.add(toClient);
            //בעת כניסת לקוח חדש יקבל את המטריצה המעודכנת
            toClient.writeObject(HallServer.hall.allPlace);
             System.out.println("update new client");
             
        }
        catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            SeatPlace place=new SeatPlace();
            while (true) {

                try {
                    place=(SeatPlace)fromClient.readObject();
                 // Boolean isCacth=(boolean)fromClient.readObject();
                  if(catchSeatPlace(place))
                  {
                      toClient.writeObject("תפיסת המקום אושרה בהצלחה");
                        //שליחה של הפרק שנתפס לכל הלקוחות המחוברים לשרת בשביל עידכון של התופס
                      UpdateClients(place);
                  }
                  else{
                      toClient.writeObject("המקום תפוס");
                      
                  }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }      
         }
    }
//synchronized
     public synchronized boolean catchSeatPlace(SeatPlace place) {

         if(!Hall.allPlace[place.getRow()][place.getCol()].isIs_cacth())
         {
           synchronized(Hall.allPlace[place.getRow()][place.getCol()]){
                        Hall.allPlace[place.getRow()][place.getCol()].setIs_cacth(true);
                        return true;
                        }
                    } else {
                        return false;
                    }
            }
     public void UpdateClients(SeatPlace current){
     
         for (ObjectOutputStream lstClient : lstClients) {
             try {
                 lstClient.writeObject(current.toString());
             } catch (IOException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         }
     
     }

}
