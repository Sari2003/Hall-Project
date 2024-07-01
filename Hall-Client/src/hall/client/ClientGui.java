/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall.client;

import java.awt.Color;
import hall.server.SeatPlace;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author s-sys
 */
public class ClientGui extends JFrame{
    
    Client c1=new Client();
    JPanel p1=new JPanel();
    JPanel p=new JPanel();
    JPanel p2=new JPanel();
    SeatPlace [][] allSeats;
    Thread myThread;
    public void init(){
        
        myThread.start();
        
    }
    
    public ClientGui() {
        this.myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
                //כותבים לקובץ את מטריצת המקומים מהשרת
                allSeats=c1.readFromServer2();
                System.out.println(allSeats.length);
                int row=allSeats.length;
                int col=allSeats[0].length;
                JLabel label=new JLabel("Event and Conference Hall");
                label.setFont(new Font("Verdana", Font.PLAIN, 40));
                label.setForeground(Color.blue);
                JLabel label1=new JLabel("The prices of all places are 50 NIS");
                label1.setFont(new Font("Verdana", Font.PLAIN, 30));
                label1.setForeground(Color.blue);
                p1.add(label);//,BorderLayout.PAGE_START
                p2.add(label1);//,BorderLayout.PAGE_END
                p.setLayout(new GridLayout(row,col));
                for (int i = 0; i < allSeats.length; i++) {
                    for (int j = 0; j < allSeats[i].length; j++) {                                         
                        JButton b=new JButton((i*allSeats.length+j)+1+"" );
                        if(allSeats[i][j].isIs_cacth())
                            b.setBackground(Color.CYAN);
                        b.addActionListener((ActionEvent e) -> {
                            int place=Integer.parseInt(((JButton)e.getSource()).getText())-1;
                            SeatPlace myPlace=new SeatPlace(place/allSeats.length,place%allSeats.length);
                            System.out.println("  row=   "+myPlace.getRow() +"  col = "+myPlace.getCol());
                            //כתיבת כל המקומות במטריצה לשרת
                            c1.writeToServer(myPlace);
                        });
                        b.setFont(new Font("Verdana", Font.PLAIN, 15));
                        p.add(b);
                    }
                    
                }
                while(true){
                    //קריאת נתונים מהשרת- מהסוקט
                    String s=c1.readFromServer();
                    if(!s.contains("_"))
                        JOptionPane.showMessageDialog(null,s);
                    else
                    {                        
                        SeatPlace ss=new SeatPlace(Integer.parseInt(s.charAt(0)+""),Integer.parseInt(s.charAt(2)+""));                       
                        int place=ss.getRow()*allSeats.length+ ss.getCol();
                        System.out.println("    place : "+place);
                        p.getComponents()[place].setBackground(Color.CYAN);
                    }             
             
                }
            }
        });
        init();
        this.add(p,BorderLayout.CENTER);
        this.add(p1,BorderLayout.PAGE_START);
        this.add(p2,BorderLayout.PAGE_END);     
    }
    
    
}
