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
public class SeatPlace  implements Serializable{
    private boolean isCacth=false;
    private int row;
    private int col;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public SeatPlace(int row, int col) {
        this.row = row;
        this.col = col;

        this.isCacth=false;
    }

    public SeatPlace() {
    }

    
    
    public SeatPlace(boolean is_cacth) {

        this.isCacth = is_cacth;
    }


    public boolean isIs_cacth() {
        return isCacth;
    }
    
   
    
    public void setIs_cacth(boolean is_cacth) {
        this.isCacth = is_cacth;
    }

    @Override
    public String toString() {
        return row + "_" + col;
    }
    
}
