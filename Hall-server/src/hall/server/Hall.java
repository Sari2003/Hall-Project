/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall.server;


import java.io.Serializable;

/**
 *
 * @author s-sys
 */
public class Hall implements Serializable{
   
    
    public static SeatPlace [][] allPlace=new SeatPlace[10][10];
    
    public SeatPlace[][] getPlace() {
        return allPlace;
    }
    

    public void setPlace(SeatPlace[][] place) {
        this.allPlace = place;
    }

    public Hall() {
        
    }
    public static void initHall() {
        //int block=0;
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                
                 allPlace[i][j]=new SeatPlace(i,j);
                
            }
           
        }
    }

  
   
    
    
    
}
