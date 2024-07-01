/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall.client;

/**
 *
 * @author s-sys
 */
public class HallClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ClientGui gui=new ClientGui();
        gui.setVisible(true);
        gui.setSize(500, 700);
    }
    
}
